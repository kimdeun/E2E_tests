package supplySet;

import api.purchaseOrder.*;
import api.transfer.DeleteTransferRequest;
import api.workOrder.ChangeWorkOrderStatusRequest;
import api.workOrder.CreateTransferRequest;
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
import pageObject.productionSet.WarehousePage;
import pageObject.supplySet.TransfersPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class TransferPageTest extends BaseTest {
    WarehousePage warehousePage = page(WarehousePage.class);
    public Buyer buyer = new Buyer("test@test.test", "", Entities.USER_ID, null);
    public Sequence sequence = new Sequence(10, 10, 1);
    public Type type = new Type("system");
    public Code code = new Code(sequence, type, "value");
    public Company company = new Company(Entities.TEST_COMPANY_ID);
    public List<ExcludedSymbols> excludedSimbolsList = new ArrayList<>();
    public String token;
    public String purchaseOrderName;
    public List<Integer> purchaseOrderIdList;

    //объекты для добавления пломб
    Quantity quantity = new Quantity(50);
    SealColor sealColor = new SealColor(1);
    SealType sealType = new SealType(8);
    AddSealsJsonObject addSealsJsonObject = new AddSealsJsonObject(quantity, sealColor, sealType);

    //объекты для создания WO
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

        //вытаскиваем токен
        token = authRequest.getResponseForUserAuthorization()
                .extract()
                .body()
                .path("content.token");

        //создаём PO и вытаскиваем PO name
        CreatePurchaseOrderJsonObject createPurchaseOrderJsonObject = new CreatePurchaseOrderJsonObject(buyer, code, company, excludedSimbolsList, faker.onePiece().character());
        CreatePurchaseOrderRequest createPurchaseOrderRequest = new CreatePurchaseOrderRequest();
        purchaseOrderName = createPurchaseOrderRequest.getResponseForCreatingPurchaseOrder(token, createPurchaseOrderJsonObject)
                .extract()
                .body()
                .path("name");

        //вытаскиваем PO id
        GetAllPurchaseOrdersRequest getAllPurchaseOrdersRequest = new GetAllPurchaseOrdersRequest();
        purchaseOrderIdList = getAllPurchaseOrdersRequest.getResponseWithAllPurchaseOrders(token)
                .extract()
                .body()
                .jsonPath().getList("id");


        //добавляем пломбы в PO
        purchaseOrderIdList = purchaseOrderIdList.stream()
                .sorted()
                .collect(Collectors.toList());
        AddSealGroupRequest addSealGroupRequest = new AddSealGroupRequest();
        List<Integer> sealGroupIdList = addSealGroupRequest.getResponseForAddSealRequest(token, addSealsJsonObject, purchaseOrderIdList.get(purchaseOrderIdList.size() - 1))
                .extract()
                .body()
                .jsonPath().getList("id");
        int sealGroupId = sealGroupIdList.get(0);

        //создаем Work Order
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

        //меняем статус WO на confirmed, produced
        ChangeWorkOrderStatusRequest changeWorkOrderStatusRequest = new ChangeWorkOrderStatusRequest();
        changeWorkOrderStatusRequest.getResponseForChangingWOStatusToConfirmed(token, workOrderId)
                .getResponseForChangingWOStatusToProduced(token, workOrderId);

        GetWorkOrderContainerTableRequest getWorkOrderContainerTableRequest = new GetWorkOrderContainerTableRequest();

        //получаем id скидов
        skidIds = getWorkOrderContainerTableRequest.getWorkOrderContainerTableWithSkids(token, workOrderId)
                .extract()
                .body()
                .path("content.id");

        //получаем номера коробок
        boxNumbers = getWorkOrderContainerTableRequest.getWorkOrderContainerTableWithUnfoldedContainer(token, workOrderId, skidIds.get(0))
                .extract()
                .body()
                .path("content.printNumber");

        //получаем id коробок
        boxIds = getWorkOrderContainerTableRequest.getWorkOrderContainerTableWithUnfoldedContainer(token, workOrderId, skidIds.get(0))
                .extract()
                .body()
                .path("content.id");

        //объекты для создания трансфера
        Content boxId = new Content(boxIds.get(0));
        List<Content> content = new ArrayList<>(Arrays.asList(boxId));
        Receiver receiver = new Receiver(null, false, null);
        jsonObjects.warehouse.Company company2 = new jsonObjects.warehouse.Company(Entities.TEST_COMPANY_ID);
        jsonObjects.warehouse.Location location2 = new jsonObjects.warehouse.Location(Entities.TEST_COMPANY_LOCATION_ID);
        jsonObjects.warehouse.Target target = new jsonObjects.warehouse.Target(company2, location2);
        CreateTransferJsonObject createTransferJsonObject = new CreateTransferJsonObject(content, null, null, false, receiver, target);

        //получаем start number пломб
        sealStartNumber = getWorkOrderContainerTableRequest.getWorkOrderContainerTableWithUnfoldedContainer(token, workOrderId, skidIds.get(0))
                .extract()
                .body()
                .path("content.sealGroup.sequence.from");

        //получаем end number пломб
        sealEndNumber = getWorkOrderContainerTableRequest.getWorkOrderContainerTableWithUnfoldedContainer(token, workOrderId, skidIds.get(0))
                .extract()
                .body()
                .path("content.sealGroup.sequence.to");

        //получаем quantity пломб
        sealQuantity = getWorkOrderContainerTableRequest.getWorkOrderContainerTableWithUnfoldedContainer(token, workOrderId, skidIds.get(0))
                .extract()
                .body()
                .path("content.quantity");

        //создаем трансфер
        CreateTransferRequest createTransferRequest = new CreateTransferRequest();
        transfersId = createTransferRequest.getResponseForCreatingTransfer(token, createTransferJsonObject)
                .extract()
                .body()
                .jsonPath().getList("id");
    }

    @Override
    @AfterEach
    public void tearDown() {
        //удаляем WO
        DeleteWorkOrderRequest deleteWorkOrderRequest = new DeleteWorkOrderRequest();
        deleteWorkOrderRequest.getResponseForDeletingWorkOrder(token, workOrderId);
        //удаляем PO
        DeletePurchaseOrderRequest deletePurchaseOrderRequest = new DeletePurchaseOrderRequest();
        deletePurchaseOrderRequest.getResponseForDeletingPurchaseOrder(token, purchaseOrderIdList.get(purchaseOrderIdList.size() - 1));
        //удаляем transfer
        DeleteTransferRequest deleteTransferRequest = new DeleteTransferRequest();
        deleteTransferRequest.getResponseForDeletingTransfer(token, transfersId.get(0).toString());
        Selenide.closeWindow();
    }

    @Test
    public void checkSealTypeOfContainersInTransferOnTheTransferPage() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getTransferPage(transfersId.get(0).toString()));

        transferPage.checkContainerSealType();
    }

    @Test
    public void checkColorOfContainersInTransferOnTheTransferPage() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getTransferPage(transfersId.get(0).toString()));

        transferPage.checkContainerColor();
    }

    @Test
    public void checkQuantityOfContainersInTransferOnTheTransferPage() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getTransferPage(transfersId.get(0).toString()));

        transferPage.checkContainersQuantity(sealQuantity.get(0).toString());
    }

    @Test
    public void checkLogoOfContainersInTransferOnTheTransferPage() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getTransferPage(transfersId.get(0).toString()));

        transferPage.checkContainerLogo();
    }

    @Test
    public void checkStartNumberOfContainersInTransferOnTheTransferPage() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getTransferPage(transfersId.get(0).toString()));

        transferPage.checkContainersStartNumber(sealStartNumber.get(0));
    }

    @Test
    public void checkEndNumberOfContainersInTransferOnTheTransferPage() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getTransferPage(transfersId.get(0).toString()));

        transferPage.checkContainersEndNumber(sealEndNumber.get(0));
    }

    @Test
    public void checkContainersTransitStateInTheTableOnTheTransferPage() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getTransferPage(transfersId.get(0).toString()));

        transferPage.checkContainersTransitStateInTheTable();
    }

    @Test
    public void checkContainersTransitStateInTheTopRightCornerOnTheTransferPage() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getTransferPage(transfersId.get(0).toString()));

        transferPage.checkTransitStateInTheTopRightCorner();
    }

    @Test
    public void checkContainerNumberInReceiveTransferModal() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getTransferPage(transfersId.get(0).toString()));

        transferPage.checkContainerNumberInReceiveTransferModal(boxNumbers.get(0));
    }

    @Test
    public void checkSealTypeInReceiveTransferModal() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getTransferPage(transfersId.get(0).toString()));

        transferPage.checkSealTypeInReceiveTransferModal();
    }

    @Test
    public void checkSealColorInReceiveTransferModal() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getTransferPage(transfersId.get(0).toString()));

        transferPage.checkSealColorInReceiveTransferModal();
    }

    @Test
    public void checkContainersQuantityInReceiveTransferModal() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getTransferPage(transfersId.get(0).toString()));

        transferPage.checkContainersQuantityInReceiveTransferModal(sealQuantity.get(0).toString());
    }

    @Test
    public void checkContainersLogoInReceiveTransferModal() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getTransferPage(transfersId.get(0).toString()));

        transferPage.checkContainerLogoInReceiveTransferModal();
    }

    @Test
    public void checkContainersStartNumberInReceiveTransferModal() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getTransferPage(transfersId.get(0).toString()));

        transferPage.checkContainersStartNumber(sealStartNumber.get(0));
    }

    @Test
    public void checkContainersEndNumberInReceiveTransferModal() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getTransferPage(transfersId.get(0).toString()));

        transferPage.checkContainersEndNumber(sealEndNumber.get(0));
    }

    @Test
    public void checkReceivedStateOnLabelInTheTopRightCorner() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getTransferPage(transfersId.get(0).toString()));

        transferPage.checkReceivedStateOnLabelInTheTopRightCorner();
    }

    @Test
    public void checkReceivedWithAProblemStateOnLabelInTheTopRightCornerByLostButton() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getTransferPage(transfersId.get(0).toString()));

        transferPage.checkReceivedWithAProblemStateOnLabelByLostButtonInReceiveTransferModal();
    }

    @Test
    public void checkReceivedWithAProblemStateOnLabelInTheTopRightCornerByProblemButton() {
        String comment = faker.text().text(10, 20);
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getTransferPage(transfersId.get(0).toString()));

        transferPage.checkReceivedWithAProblemStateOnLabelByProblemButtonInReceiveTransferModal(comment);
    }
}
