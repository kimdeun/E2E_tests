package productionSet;

import api.purchaseOrder.*;
import api.workOrder.CreateWorkOrderRequest;
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
import jsonObjects.workOrder.createWorkOrder.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageObject.LoginPage;
import pageObject.productionSet.PurchaseOrderPage;
import pageObject.productionSet.WorkOrderListPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorkOrderListTest extends BaseTest {
    WorkOrderListPage workOrderListPage = page(WorkOrderListPage.class);
    PurchaseOrderPage purchaseOrderPage = page(PurchaseOrderPage.class);

    //objects for PO creation
    public Buyer buyer = new Buyer("test@test.test", "", Entities.BUYER_ID, null);
    public Sequence sequence = new Sequence(10, 10, 1);
    public Type type = new Type("system");
    public Code code = new Code(sequence, type, "value");
    public Company company = new Company(Entities.TEST_COMPANY_ID);
    public List<ExcludedSymbols> excludedSimbolsList = new ArrayList<>();
    public String token;
    public String purchaseOrderName;
    public List<Integer> purchaseOrderIdList;
    public List<Integer> workOrderIdList;

    //objects for adding seals
    Quantity quantity = new Quantity(50);
    SealColor sealColor = new SealColor(1);
    SealType sealType = new SealType(8);
    AddSealsJsonObject addSealsJsonObject = new AddSealsJsonObject(quantity, sealColor,sealType);

    //objects for WO creation
    jsonObjects.workOrder.createWorkOrder.Quantity quantity1 = new jsonObjects.workOrder.createWorkOrder.Quantity(2);
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
    Capacity boxCapacity = new Capacity(12, quantity1, boxSource, sealTarget);
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

    @Override
    @BeforeEach
    public void setUp() {
        Configuration.browserSize = Entities.BROWSER_SIZE_1920_1080;
        loginPage = open(URLs.STAGE_URL, LoginPage.class);
        RestAssured.baseURI = URLs.BASE_API_URI;

        //get a token
        token = authRequest.getResponseForUserAuthorization()
                .extract()
                .body()
                .path("content.token");

        //get PO name
        CreatePurchaseOrderJsonObject createPurchaseOrderJsonObject = new CreatePurchaseOrderJsonObject(buyer, code, company, excludedSimbolsList, faker.idNumber().valid());
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

        //create WO
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
    }

    @Override
    @AfterEach
    public void tearDown() {
        //get WO id
        GetAllWorkOrdersRequest getAllWorkOrdersRequest = new GetAllWorkOrdersRequest();
        workOrderIdList = getAllWorkOrdersRequest.getResponseWithAllPurchaseOrders(token)
                .extract()
                .body()
                .jsonPath().getList("id");

        //delete WO
        DeleteWorkOrderRequest deleteWorkOrderRequest = new DeleteWorkOrderRequest();
        deleteWorkOrderRequest.getResponseForDeletingWorkOrder(token, workOrderId);

        //delete PO
        DeletePurchaseOrderRequest deletePurchaseOrderRequest = new DeletePurchaseOrderRequest();
        deletePurchaseOrderRequest.getResponseForDeletingPurchaseOrder(token, purchaseOrderIdList.get(purchaseOrderIdList.size() - 1));

        Selenide.closeWindow();
    }

    @Test
    public void checkWorkOrderState() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.WORK_ORDERS_LIST_PAGE);
        workOrderListPage.waitForLoadWorkOrdersPage(purchaseOrderName);

        assertEquals(Entities.STATE_ENTERED, workOrderListPage.getWorkOrderState());
    }

    @Test
    public void checkPurchaseOrderNameInTheTable() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.WORK_ORDERS_LIST_PAGE);
        workOrderListPage.waitForLoadWorkOrdersPage(purchaseOrderName);

        assertEquals(purchaseOrderName, workOrderListPage.getPurchaseOrderName());
    }

    @Test
    public void checkWorkOrderCompany() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.WORK_ORDERS_LIST_PAGE);
        workOrderListPage.waitForLoadWorkOrdersPage(purchaseOrderName);

        assertEquals(Entities.USERS_COMPANY, workOrderListPage.getWorkOrderCompany());
    }

    @Test
    public void checkWorkOrderOwner() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.WORK_ORDERS_LIST_PAGE);
        workOrderListPage.waitForLoadWorkOrdersPage(purchaseOrderName);

        assertEquals(Entities.USER_NAME, workOrderListPage.getWorkOrderOwner());
    }

    @Test
    public void checkWorkOrderSealType() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.WORK_ORDERS_LIST_PAGE);
        workOrderListPage.waitForLoadWorkOrdersPage(purchaseOrderName);

        assertEquals(Entities.SEAL_TYPE, workOrderListPage.getWorkOrderSealType());
    }

    @Test
    public void checkWorkOrderSealColor() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.WORK_ORDERS_LIST_PAGE);
        workOrderListPage.waitForLoadWorkOrdersPage(purchaseOrderName);

        assertEquals(Entities.GRAY_COLOR_OF_THE_SEAL, workOrderListPage.getWorkOrderSealColor());
    }

    @Test
    public void checkWorkOrderEtchingFormat() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.WORK_ORDERS_LIST_PAGE);
        workOrderListPage.waitForLoadWorkOrdersPage(purchaseOrderName);

        assertEquals(Entities.ETCHING_FORMAT, workOrderListPage.getWorkOrderEtchingFormat());
    }

    @Test
    public void checkWorkOrderLogo() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.WORK_ORDERS_LIST_PAGE);
        workOrderListPage.waitForLoadWorkOrdersPage(purchaseOrderName);

        assertEquals(Entities.LOGO, workOrderListPage.getWorkOrderLogo());
    }

    @Test
    public void checkWorkOrderProductionOnThePurchaseOrderPage() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getPurchaseOrderPageURL(purchaseOrderIdList.get(purchaseOrderIdList.size() - 1).toString()));
        purchaseOrderPage.waitForNewWorkOrder();

        assertEquals(Entities.USA_PRODUCTION, purchaseOrderPage.getWorkOrderProduction());
    }

    @Test
    public void checkWorkOrderStateOnThePurchaseOrderPage() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getPurchaseOrderPageURL(purchaseOrderIdList.get(purchaseOrderIdList.size() - 1).toString()));
        purchaseOrderPage.waitForNewWorkOrder();

        assertEquals(Entities.STATE_ENTERED, purchaseOrderPage.getWorkOrderState());
    }

    @Test
    public void checkWorkOrderOwnerOnThePurchaseOrderPage() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getPurchaseOrderPageURL(purchaseOrderIdList.get(purchaseOrderIdList.size() - 1).toString()));
        purchaseOrderPage.waitForNewWorkOrder();

        assertEquals(Entities.USER_NAME, purchaseOrderPage.getWorkOrderOwner());
    }

    @Test
    public void checkWorkOrderSealTypeOnThePurchaseOrderPage() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getPurchaseOrderPageURL(purchaseOrderIdList.get(purchaseOrderIdList.size() - 1).toString()));
        purchaseOrderPage.waitForNewWorkOrder();

        assertEquals(Entities.SEAL_TYPE, purchaseOrderPage.getWorkOrderSealType());
    }

    @Test
    public void checkWorkOrderSealColorOnThePurchaseOrderPage() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getPurchaseOrderPageURL(purchaseOrderIdList.get(purchaseOrderIdList.size() - 1).toString()));
        purchaseOrderPage.waitForNewWorkOrder();

        assertEquals(Entities.GRAY_COLOR_OF_THE_SEAL, purchaseOrderPage.getWorkOrderSealColor());
    }

    @Test
    public void checkWorkOrderEtchingFormatOnThePurchaseOrderPage() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getPurchaseOrderPageURL(purchaseOrderIdList.get(purchaseOrderIdList.size() - 1).toString()));
        purchaseOrderPage.waitForNewWorkOrder();

        assertEquals(Entities.ETCHING_FORMAT, purchaseOrderPage.getWorkOrderEtchingFormat());
    }

    @Test
    public void checkWorkOrderLogoOnThePurchaseOrderPage() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getPurchaseOrderPageURL(purchaseOrderIdList.get(purchaseOrderIdList.size() - 1).toString()));
        purchaseOrderPage.waitForNewWorkOrder();

        assertEquals(Entities.LOGO, purchaseOrderPage.getWorkOrderLogo());
    }
}
