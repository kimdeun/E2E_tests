package supplySet;

import api.purchaseOrder.*;
import api.transfer.DeleteTransferRequest;
import api.workOrder.ChangeWorkOrderStatusRequest;
import api.transfer.CreateTransferRequest;
import api.workOrder.CreateWorkOrderRequest;
import api.workOrder.GetWorkOrderContainerTableRequest;
import baseTests.BaseTest;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import constants.Entities;
import constants.URLs;
import io.restassured.RestAssured;
import jsonObjects.purchaseOrder.addSeals.AddSealsJsonObject;
import jsonObjects.purchaseOrder.addSeals.Quantity;
import jsonObjects.purchaseOrder.addSeals.SealColor;
import jsonObjects.purchaseOrder.addSeals.SealType;
import jsonObjects.purchaseOrder.createPurchaseOrder.*;
import jsonObjects.purchaseOrder.createPurchaseOrder.Company;
import jsonObjects.warehouse.Content;
import jsonObjects.warehouse.CreateTransferJsonObject;
import jsonObjects.warehouse.Receiver;
import jsonObjects.workOrder.createWorkOrder.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageObject.LoginPage;
import pageObject.supplySet.TransfersListPage;
import pageObject.supplySet.TransfersPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class TransfersListTest extends BaseTest {
    TransfersListPage transfersListPage = page(TransfersListPage.class);
    public Buyer buyer = new Buyer("test@test.test", "", Entities.BUYER_ID, null);
    public Sequence sequence = new Sequence(10, 10, 1);
    public Type type = new Type("system");
    public Code code = new Code(sequence, type, "value");
    public Company company = new Company(Entities.TEST_COMPANY_ID);
    public List<ExcludedSymbols> excludedSimbolsList = new ArrayList<>();
    public String token;
    public String purchaseOrderName;
    public List<Integer> purchaseOrderIdList;

    //objects for adding seals
    Quantity quantity = new Quantity(50);
    SealColor sealColor = new SealColor(1);
    SealType sealType = new SealType(8);
    AddSealsJsonObject addSealsJsonObject = new AddSealsJsonObject(quantity, sealColor, sealType);

    //objects for WO creation
    jsonObjects.workOrder.createWorkOrder.Quantity quantity1 = new jsonObjects.workOrder.createWorkOrder.Quantity(2);
    jsonObjects.workOrder.createWorkOrder.Quantity quantity2 = new jsonObjects.workOrder.createWorkOrder.Quantity(4);
    EntityType skidEntityType = new EntityType(1);
    EntityClass skidEntityClass = new EntityClass("skid");
    EntityType boxEntityType = new EntityType(2);
    EntityClass boxEntityClass = new EntityClass("box");
    EntityType bagEntityType = new EntityType(3);
    EntityClass bagEntityClass = new EntityClass("bag");
    EntityType sealEntityType = new EntityType(8);
    EntityClass sealEntityClass = new EntityClass("seal");
    Source skidSource = new Source(skidEntityType, skidEntityClass);
    Source boxSource = new Source(boxEntityType, boxEntityClass);
    Source bagSource = new Source(bagEntityType, bagEntityClass);
    Target boxTarget = new Target(boxEntityType, boxEntityClass);
    Target sealTarget = new Target(sealEntityType, sealEntityClass);
    Capacity skidCapacity = new Capacity(1, quantity1, skidSource, boxTarget);
    Capacity boxCapacity = new Capacity(12, quantity2, boxSource, sealTarget);
    Capacity bagCapacity = new Capacity(11, quantity1, bagSource, sealTarget);
    List<Capacity> capacity = new ArrayList<>(Arrays.asList(skidCapacity, boxCapacity, bagCapacity));
    EtchingFormat etchingFormat = new EtchingFormat(2);
    Packing packing = new Packing(1);
    Production production = new Production(Entities.PRODUCTION_ID);
    SealEnumerationMode sealEnumerationMode = new SealEnumerationMode(Entities.SEAL_ENUM_MODE_SEQUENTIAL);
    jsonObjects.workOrder.createWorkOrder.Company company1 = new jsonObjects.workOrder.createWorkOrder.Company(Entities.TEST_COMPANY_ID);
    Location location = new Location(Entities.TEST_COMPANY_LOCATION_ID);
    TargetCompanyLocation targetCompanyLocation = new TargetCompanyLocation(company1, location);
    Integer workOrderId;
    List<Integer> skidIds;
    List<Integer> boxIds;
    List<Integer> transfersId;
    TransfersPage transferPage = page(TransfersPage.class);
    List<String> sealStartNumber;
    List<String> sealEndNumber;
    List<Integer> sealQuantity;
    List<String> boxNumbers;

    @Override
    @BeforeEach
    public void setUp() {
        Configuration.browserSize = Entities.BROWSER_SIZE_1920_1080;
        loginPage = open(URLs.STAGE_URL, LoginPage.class);
        RestAssured.baseURI = URLs.BASE_API_URI;

        //get token
        token = authRequest.getToken();

        //create PO and get PO name
        CreatePurchaseOrderJsonObject createPurchaseOrderJsonObject = new CreatePurchaseOrderJsonObject(buyer, code, company, excludedSimbolsList, faker.onePiece().character());
        CreatePurchaseOrderRequest createPurchaseOrderRequest = new CreatePurchaseOrderRequest();
        purchaseOrderName = createPurchaseOrderRequest.getResponseForCreatingPurchaseOrder(token, createPurchaseOrderJsonObject)
                .extract()
                .body()
                .path("name");

        //get PO id
        GetAllPurchaseOrdersRequest getAllPurchaseOrdersRequest = new GetAllPurchaseOrdersRequest();
        purchaseOrderIdList = getAllPurchaseOrdersRequest.getResponseWithAllPurchaseOrders(token)
                .extract()
                .body()
                .jsonPath().getList("id");


        //add seals in PO
        purchaseOrderIdList = purchaseOrderIdList.stream()
                .sorted()
                .collect(Collectors.toList());
        AddSealGroupRequest addSealGroupRequest = new AddSealGroupRequest();
        List<Integer> sealGroupIdList = addSealGroupRequest.getResponseForAddSealRequest(token, addSealsJsonObject, purchaseOrderIdList.get(purchaseOrderIdList.size() - 1))
                .extract()
                .body()
                .jsonPath().getList("id");
        int sealGroupId = sealGroupIdList.get(0);

        //create Work Order
        PurchaseOrder purchaseOrder = new PurchaseOrder(purchaseOrderIdList.get(purchaseOrderIdList.size() - 1));
        SealGroup sealGroup = new SealGroup(sealGroupId);

        CreateWorkOrderJsonObject createWorkOrderJsonObject = new CreateWorkOrderJsonObject(capacity, 16000, etchingFormat,
                false, null, packing, production, purchaseOrder,
                50, sealEnumerationMode, sealGroup, targetCompanyLocation);
        CreateWorkOrderRequest createWorkOrderRequest = new CreateWorkOrderRequest();
        workOrderId = createWorkOrderRequest.getResponseForCreatingWorkOrder(token, createWorkOrderJsonObject)
                .extract()
                .body()
                .path("id");

        //change WO state to confirmed, produced
        ChangeWorkOrderStatusRequest changeWorkOrderStatusRequest = new ChangeWorkOrderStatusRequest();
        changeWorkOrderStatusRequest.getResponseForChangingWOStatusToConfirmed(token, workOrderId)
                .getResponseForChangingWOStatusToProduced(token, workOrderId);

        GetWorkOrderContainerTableRequest getWorkOrderContainerTableRequest = new GetWorkOrderContainerTableRequest();

        //get skid ids
        skidIds = getWorkOrderContainerTableRequest.getWorkOrderContainerTableWithSkids(token, workOrderId)
                .extract()
                .body()
                .path("content.id");

        //get box ids
        boxIds = getWorkOrderContainerTableRequest.getWorkOrderContainerTableWithUnfoldedContainer(token, workOrderId, skidIds.get(0))
                .extract()
                .body()
                .path("content.id");

        //get box numbers
        boxNumbers = getWorkOrderContainerTableRequest.getWorkOrderContainerTableWithUnfoldedContainer(token, workOrderId, skidIds.get(0))
                .extract()
                .body()
                .path("content.printNumber");

        //objects for transfer creation
        Content boxId = new Content(boxIds.get(0));
        List<Content> content = new ArrayList<>(Arrays.asList(boxId));
        Receiver receiver = new Receiver(null, false, null);
        jsonObjects.warehouse.Company company2 = new jsonObjects.warehouse.Company(Entities.TEST_COMPANY_ID);
        jsonObjects.warehouse.Location location2 = new jsonObjects.warehouse.Location(Entities.TEST_COMPANY_LOCATION_ID);
        jsonObjects.warehouse.Target target = new jsonObjects.warehouse.Target(company2, location2);
        CreateTransferJsonObject createTransferJsonObject = new CreateTransferJsonObject(content, null, null, false, receiver, target);

        //get seal start number
        sealStartNumber = getWorkOrderContainerTableRequest.getWorkOrderContainerTableWithUnfoldedContainer(token, workOrderId, skidIds.get(0))
                .extract()
                .body()
                .path("content.sealGroup.sequence.from");

        //get seal end number
        sealEndNumber = getWorkOrderContainerTableRequest.getWorkOrderContainerTableWithUnfoldedContainer(token, workOrderId, skidIds.get(0))
                .extract()
                .body()
                .path("content.sealGroup.sequence.to");

        //get seal quantity
        sealQuantity = getWorkOrderContainerTableRequest.getWorkOrderContainerTableWithUnfoldedContainer(token, workOrderId, skidIds.get(0))
                .extract()
                .body()
                .path("content.quantity");

        //create transfer
        CreateTransferRequest createTransferRequest = new CreateTransferRequest();
        transfersId = createTransferRequest.getResponseForCreatingTransfer(token, createTransferJsonObject)
                .extract()
                .body()
                .jsonPath().getList("id");
    }

    @AfterEach
    public void tearDown() {
        //delete WO
        DeleteWorkOrderRequest deleteWorkOrderRequest = new DeleteWorkOrderRequest();
        deleteWorkOrderRequest.getResponseForDeletingWorkOrder(token, workOrderId);
        //delete PO
        DeletePurchaseOrderRequest deletePurchaseOrderRequest = new DeletePurchaseOrderRequest();
        deletePurchaseOrderRequest.getResponseForDeletingPurchaseOrder(token, purchaseOrderIdList.get(purchaseOrderIdList.size() - 1));
        //delete transfer
        DeleteTransferRequest deleteTransferRequest = new DeleteTransferRequest();
        deleteTransferRequest.getResponseForDeletingTransfer(token, transfersId.get(0).toString());
    }

    @Test
    public void checkContainersSenderInTheTransfersListTable() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.checkTransfersSender();
    }

    @Test
    public void checkContainersDestinationInTheTransfersListTable() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.checkTransfersDestination();
    }

    @Test
    public void checkContainersSourceInTheTransfersListTable() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.checkTransfersSource();
    }

    @Test
    public void checkContainersReceiverInTheTransfersListTable() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.checkTransfersReceiver();
    }

    @Test
    public void checkContainersOwnerInTheTransfersListTable() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.checkTransfersOwner();
    }

    @Test
    public void checkContainersQuantityInTheTransfersListTable() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.checkTransfersContainersQuantity(sealQuantity.get(0).toString());
    }

    @Test
    public void checkContainersStateInTheTransfersListTable() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.checkTransfersTransitState();
    }

    @Test
    public void checkReceivedStateOfContainerInTheTransfersTable() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.checkReceivedStateOfAContainer();
    }

    @Test
    public void checkReceivedWithAProblemStateOfContainerInTheTransfersTableByLostButtonInReceiveTransferModal() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.checkReceivedWithAProblemStateByLostButtonInReceiveTransferModal();
    }

    @Test
    public void checkReceivedWithAProblemStateOfContainerInTheTransfersTableByProblemButtonInReceiveTransferModal() {
        String comment = faker.text().text(10, 20);
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.checkReceivedWithAProblemStateByProblemButtonInReceiveTransferModal(comment);
    }

    @Test
    public void checkContainerNumberInReceiveTransferModal() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.checkContainerNumberInReceiveTransferModal(boxNumbers.get(0));
    }

    @Test
    public void checkSealTypeInReceiveTransferModal() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.checkSealTypeInReceiveTransferModal();
    }

    @Test
    public void checkSealColorInReceiveTransferModal() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.checkSealColorInReceiveTransferModal();
    }

    @Test
    public void checkContainersQuantityInReceiveTransferModal() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.checkContainersQuantityInReceiveTransferModal(sealQuantity.get(0).toString());
    }

    @Test
    public void checkContainersLogoInReceiveTransferModal() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.checkContainerLogoInReceiveTransferModal();
    }

    @Test
    public void checkContainersStartNumberInReceiveTransferModal() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.checkContainersStartNumber(sealStartNumber.get(0));
    }

    @Test
    public void checkContainersEndNumberInReceiveTransferModal() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.checkContainersEndNumber(sealEndNumber.get(0));
    }

    @Test
    public void checkContainersLostStateInTheTableOnTheTransferPage() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.switchStateToLost()
                .openTransfersPage()
                .checkContainersLostStateInTheTable();
    }

    @Test
    public void checkContainersReceivedWithAProblemStateByLostButtonInTheTopRightCornerOnTheTransferPage() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.switchStateToLost()
                .openTransfersPage()
                .checkReceivedWithAProblemStateInTheTopRightCorner();
    }

    @Test
    public void checkContainersProblemStateInTheTableOnTheTransferPage() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.switchStateToProblem()
                .openTransfersPage()
                .checkContainersProblemStateInTheTable();
    }

    @Test
    public void checkContainersReceivedWithAProblemStateByProblemButtonInTheTopRightCornerOnTheTransferPage() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.switchStateToProblem()
                .openTransfersPage()
                .checkReceivedWithAProblemStateInTheTopRightCorner();
    }

    @Test
    public void checkCommentInTheTableOnTheTransferPage() {
        String comment = faker.text().text(10, 20);
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.switchStateToProblemWithComment(comment)
                .openTransfersPage()
                .checkContainersCommentInTheTable(comment);
    }

    @Test
    public void checkReceivedContainerInTheWarehouse() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.TRANSFERS_LIST_PAGE);

        transfersListPage.switchStateToReceive()
                .openSupplySet()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent()
                .searchContainer(boxNumbers.get(0));
    }
}
