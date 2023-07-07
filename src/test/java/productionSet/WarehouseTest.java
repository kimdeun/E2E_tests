package productionSet;

import api.purchaseOrder.*;
import api.transfer.DeleteTransferRequest;
import api.transfer.GetAllTransfersRequest;
import api.workOrder.ChangeWorkOrderStatusRequest;
import api.workOrder.CreateTransferRequest;
import api.workOrder.CreateWorkOrderRequest;
import api.workOrder.GetWorkOrderContainerTableRequest;
import baseTests.BaseTest;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import constants.Credentials;
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
import pageObject.supplySet.TransfersListPage;
import pageObject.supplySet.TransfersPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class WarehouseTest extends BaseTest {
    WarehousePage warehousePage = page(WarehousePage.class);
    //объекты для создания PO
    public Buyer buyer = new Buyer("test@test.test", "", Credentials.USER_ID, null);
    public Sequence sequence = new Sequence(10, 10, 1);
    public Type type = new Type("system");
    public Code code = new Code(sequence, type, "value");
    public Company company = new Company(Credentials.TEST_COMPANY_ID);
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
    Production production = new Production(Credentials.PRODUCTION_ID);
    SealEnumerationMode sealEnumerationMode = new SealEnumerationMode(Credentials.SEAL_ENUM_MODE_SEQUENTIAL);
    jsonObjects.workOrder.createWorkOrder.Company company1 = new jsonObjects.workOrder.createWorkOrder.Company(Credentials.TEST_COMPANY_ID);
    Location location = new Location(Credentials.TEST_COMPANY_LOCATION_ID);
    TargetCompanyLocation targetCompanyLocation = new TargetCompanyLocation(company1, location);
    Integer workOrderId;
    List<String> skidNumbers;
    List<Integer> skidIds;
    List<String> boxNumbers;
    List<Integer> boxIds;
    List<String> bagNumbers;
    List<Integer> transferIds;

    @Override
    @BeforeEach
    public void setUp() {
        Configuration.browserSize = Credentials.BROWSER_SIZE_1920_1080;
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

        //получаем номера скидов
        GetWorkOrderContainerTableRequest getWorkOrderContainerTableRequest = new GetWorkOrderContainerTableRequest();
        skidNumbers = getWorkOrderContainerTableRequest.getWorkOrderContainerTableWithSkids(token, workOrderId)
                .extract()
                .body()
                .path("content.printNumber");

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

        //получаем номера бэгов
        bagNumbers = getWorkOrderContainerTableRequest.getWorkOrderContainerTableWithUnfoldedContainer(token, workOrderId, boxIds.get(0))
                .extract()
                .body()
                .path("content.printNumber");
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
        GetAllTransfersRequest getAllTransfersRequest = new GetAllTransfersRequest();
        transferIds = getAllTransfersRequest.getResponseWithAllTransfer(token)
                .extract()
                .body()
                .path("id");
        transferIds = transferIds.stream()
                .sorted()
                .collect(Collectors.toList());
        DeleteTransferRequest deleteTransferRequest = new DeleteTransferRequest();
        deleteTransferRequest.getResponseForDeletingTransfer(token, transferIds.get(transferIds.size() - 1).toString());
        Selenide.closeWindow();
    }

    @Test
    public void createTransferWithSkid() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        warehousePage.searchContainer(skidNumbers.get(0))
                .createTransfer(0)
                .openSupplySet()
                .openTransfersListPage()
                .openTransfersPage()
                .checkContainerNumber(skidNumbers.get(0));
    }

    @Test
    public void createTransferWithBox() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        warehousePage.searchContainer(skidNumbers.get(0))
                .unfoldSkid()
                .createTransfer(0)
                .openSupplySet()
                .openTransfersListPage()
                .openTransfersPage()
                .checkContainerNumber(boxNumbers.get(0));
    }

    @Test
    public void createTransferWithBag() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        warehousePage.searchContainer(skidNumbers.get(0))
                .unfoldSkid()
                .unfoldBox()
                .createTransfer(0)
                .openSupplySet()
                .openTransfersListPage()
                .openTransfersPage()
                .checkContainerNumber(bagNumbers.get(0));
    }
}
