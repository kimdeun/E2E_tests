package productionSet;

import api.auth.AuthRequest;
import api.purchaseOrder.CreatePurchaseOrderRequest;
import api.purchaseOrder.DeletePurchaseOrderRequest;
import api.purchaseOrder.GetAllPurchaseOrdersRequest;
import baseTests.BaseTest;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import constants.Credentials;
import constants.URLs;
import io.restassured.RestAssured;
import jsonObjects.purchaseOrder.createPurchaseOrder.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageObject.LoginPage;
import pageObject.productionSet.PurchaseOrderListPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PurchaseOrderListTest extends BaseTest {
    //объекты для создания PO
    public Buyer buyer = new Buyer("test@test.test", "", Credentials.USER_ID, null);
    public Sequence sequence = new Sequence(10, 10, 1);
    public Type type = new Type("system");
    public Code code = new Code(sequence, type, "value");
    public Company company = new Company(Credentials.TEST_COMPANY_ID);
    public List<ExcludedSymbols> excludedSimbolsList = new ArrayList<>();
    public String token;
    public String purchaseOrderName;
    public List<Integer> purchaseOrderId;
    PurchaseOrderListPage purchaseOrderListPage = page(PurchaseOrderListPage.class);

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

        //вытаскиваем PO name
        CreatePurchaseOrderJsonObject createPurchaseOrderJsonObject = new CreatePurchaseOrderJsonObject(buyer, code, company, excludedSimbolsList, faker.onePiece().character());
        CreatePurchaseOrderRequest createPurchaseOrderRequest = new CreatePurchaseOrderRequest();
        purchaseOrderName = createPurchaseOrderRequest.getResponseForCreatingPurchaseOrder(token, createPurchaseOrderJsonObject)
                .extract()
                .body()
                .path("name");

        //вытаскиваем PO id
        GetAllPurchaseOrdersRequest getAllPurchaseOrdersRequest = new GetAllPurchaseOrdersRequest();
        purchaseOrderId = getAllPurchaseOrdersRequest.getResponseWithAllPurchaseOrders(token)
                .extract()
                .body()
                .jsonPath().getList("id");
    }

    @Override
    @AfterEach
    public void tearDown() {
        //удаляем PO
        DeletePurchaseOrderRequest deletePurchaseOrderRequest = new DeletePurchaseOrderRequest();
        deletePurchaseOrderRequest.getResponseForDeletingPurchaseOrder(token, purchaseOrderId.get(purchaseOrderId.size() - 1));
        Selenide.closeWindow();
    }

    @Test
    public void checkPurchaseOrderState() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.PURCHASE_ORDER_LIST_PAGE);
        purchaseOrderListPage.waitForLoadPurchaseOrdersPage(purchaseOrderName);

        assertEquals(Credentials.STATE_ENTERED, purchaseOrderListPage.getPurchaseOrderState());
    }

    @Test
    public void checkPurchaseOrderCompany() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.PURCHASE_ORDER_LIST_PAGE);
        purchaseOrderListPage.waitForLoadPurchaseOrdersPage(purchaseOrderName);

        assertEquals(Credentials.USERS_COMPANY, purchaseOrderListPage.getPurchaseOrderCompany());
    }

    @Test
    public void checkPurchaseOrderBuyer() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.PURCHASE_ORDER_LIST_PAGE);
        purchaseOrderListPage.waitForLoadPurchaseOrdersPage(purchaseOrderName);

        assertEquals(Credentials.USER_NAME_BUYER_NAME, purchaseOrderListPage.getPurchaseOrderBuyer());
    }

    @Test
    public void checkPurchaseOrderOwner() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.PURCHASE_ORDER_LIST_PAGE);
        purchaseOrderListPage.waitForLoadPurchaseOrdersPage(purchaseOrderName);

        assertEquals(Credentials.USER_NAME, purchaseOrderListPage.getPurchaseOrderOwner());
    }
}

