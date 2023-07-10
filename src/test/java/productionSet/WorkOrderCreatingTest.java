package productionSet;

import api.purchaseOrder.*;
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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageObject.LoginPage;
import pageObject.productionSet.PurchaseOrderPage;
import pageObject.productionSet.WorkOrderListPage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorkOrderCreatingTest extends BaseTest {
    public String quantityOfSeals = "50";
    public String notes = faker.text().text(20, 40);
    WorkOrderListPage workOrderListPage = page(WorkOrderListPage.class);
    PurchaseOrderPage purchaseOrderPage = page(PurchaseOrderPage.class);

    //объекты для создания PO
    public Buyer buyer = new Buyer("test@test.test", "", Entities.USER_ID, null);
    public Sequence sequence = new Sequence(10, 10, 1);
    public Type type = new Type("system");
    public Code code = new Code(sequence, type, "value");
    public Company company = new Company(Entities.TEST_COMPANY_ID);
    public List<ExcludedSymbols> excludedSimbolsList = new ArrayList<>();
    public String token;
    public String purchaseOrderName;
    public List<Integer> purchaseOrderIdList;
    public List<Integer> workOrderIdList;

    //объекты для добавления пломб
    Quantity quantity = new Quantity(50);
    SealColor sealColor = new SealColor(1);
    SealType sealType = new SealType(8);
    AddSealsJsonObject addSealsJsonObject = new AddSealsJsonObject(quantity, sealColor,sealType);

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

        //вытаскиваем PO name
        CreatePurchaseOrderJsonObject createPurchaseOrderJsonObject = new CreatePurchaseOrderJsonObject(buyer, code, company, excludedSimbolsList, faker.idNumber().valid());
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
        addSealGroupRequest.getResponseForAddSealRequest(token, addSealsJsonObject, purchaseOrderIdList.get(purchaseOrderIdList.size() - 1));
    }

    @Override
    @AfterEach
    public void tearDown() {
        //вытаскиваем WO id
        GetAllWorkOrdersRequest getAllWorkOrdersRequest = new GetAllWorkOrdersRequest();
        workOrderIdList = getAllWorkOrdersRequest.getResponseWithAllPurchaseOrders(token)
                .extract()
                .body()
                .jsonPath().getList("id");

        //удаляем WO
        workOrderIdList = workOrderIdList.stream()
                .sorted()
                .collect(Collectors.toList());
        DeleteWorkOrderRequest deleteWorkOrderRequest = new DeleteWorkOrderRequest();
        deleteWorkOrderRequest.getResponseForDeletingWorkOrder(token, workOrderIdList.get(workOrderIdList.size() - 1));

        //удаляем PO
        DeletePurchaseOrderRequest deletePurchaseOrderRequest = new DeletePurchaseOrderRequest();
        deletePurchaseOrderRequest.getResponseForDeletingPurchaseOrder(token, purchaseOrderIdList.get(purchaseOrderIdList.size() - 1));

        Selenide.closeWindow();
    }

    @Test
    public void createWorkOrder() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.WORK_ORDERS_LIST_PAGE);
        workOrderListPage.createWorkOrder(purchaseOrderName, quantityOfSeals, notes)
                .waitForLoadWorkOrdersPage(purchaseOrderName);

        assertTrue(workOrderListPage.createdWorkOrderIsDisplayed(purchaseOrderName));
    }


    @Test
    public void createWorkOrderFromPurchaseOrderPage() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getPurchaseOrderPageURL(purchaseOrderIdList.get(purchaseOrderIdList.size() - 1).toString()));
        purchaseOrderPage.createWorkOrder(quantityOfSeals, notes)
                .waitForNewWorkOrder();

        assertTrue(purchaseOrderPage.createdWorkOrderIsDisplayed());
    }
}
