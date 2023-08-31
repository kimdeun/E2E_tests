package productionSet;

import api.auth.AuthRequest;
import api.purchaseOrder.CreatePurchaseOrderRequest;
import api.purchaseOrder.DeletePurchaseOrderRequest;
import api.purchaseOrder.GetAllPurchaseOrdersRequest;
import baseTests.BaseTest;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import constants.Entities;
import constants.URLs;
import io.restassured.RestAssured;
import jsonObjects.purchaseOrder.createPurchaseOrder.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageObject.LoginPage;
import pageObject.productionSet.PurchaseOrderListPage;
import pageObject.productionSet.PurchaseOrderPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurchaseOrderPageTest extends BaseTest {
    public String quantityOfSeals = "50";
    PurchaseOrderPage purchaseOrderPage = page(PurchaseOrderPage.class);
    public AuthRequest authRequest = new AuthRequest();

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
    PurchaseOrderListPage purchaseOrderListPage = page(PurchaseOrderListPage.class);

    @Override
    @BeforeEach
    public void setUp() {
        Configuration.browserSize = Entities.BROWSER_SIZE_1920_1080;
        loginPage = open(URLs.STAGE_URL, LoginPage.class);
        RestAssured.baseURI = URLs.BASE_API_URI;

        //get a token
        token = authRequest.getToken();

        //get PO name
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
    }

    @AfterEach
    public void tearDown() {
        //delete PO
        DeletePurchaseOrderRequest deletePurchaseOrderRequest = new DeletePurchaseOrderRequest();
        deletePurchaseOrderRequest.getResponseForDeletingPurchaseOrder(token, purchaseOrderIdList.get(purchaseOrderIdList.size() - 1));
    }

    @Test
    public void changeStateOfPurchaseOrderToConfirmed() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.PURCHASE_ORDER_LIST_PAGE);
        purchaseOrderListPage.waitForLoadPurchaseOrdersPage(purchaseOrderName)
                .openPurchaseOrderPage(purchaseOrderName)
                .changeStateOfPurchaseOrder()
                .waitForConfirmedState();

        assertEquals(Entities.STATE_CONFIRMED, purchaseOrderPage.getPurchaseOrderState());
    }

    @Test
    public void changeStateOfPurchaseOrderToProduced() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.PURCHASE_ORDER_LIST_PAGE);
        purchaseOrderListPage.waitForLoadPurchaseOrdersPage(purchaseOrderName)
                .openPurchaseOrderPage(purchaseOrderName)
                .changeStateOfPurchaseOrder()
                .changeStateOfPurchaseOrder()
                .waitForProducedState();

        assertEquals(Entities.STATE_PRODUCED, purchaseOrderPage.getPurchaseOrderState());
    }

    @Test
    public void addSealGroupToPurchaseOrder() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.getPurchaseOrderPageURL(purchaseOrderIdList.get(purchaseOrderIdList.size() - 1).toString()));
        purchaseOrderPage.addSealGroup(quantityOfSeals);

        assertTrue(purchaseOrderPage.contentInTheTableIsDisplayed());
    }
}
