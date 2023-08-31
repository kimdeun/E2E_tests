package productionSet;

import api.purchaseOrder.DeletePurchaseOrderRequest;
import api.purchaseOrder.GetAllPurchaseOrdersRequest;
import baseTests.BaseTest;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import constants.Entities;
import constants.URLs;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageObject.LoginPage;
import pageObject.productionSet.PurchaseOrderListPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurchaseOrderCreatingTest extends BaseTest {
    public String id = faker.idNumber().invalid();
    public String name = faker.name().name();
    public String phoneNumber = faker.phoneNumber().phoneNumber();
    public String email = faker.internet().emailAddress();
    public String customCode = faker.number().digits(3);
    public String numbersQuantity = "10";
    public String sequenceStartNumber = "1";
    public String totalSealsQuantity = faker.number().digits(5);
    PurchaseOrderListPage purchaseOrderListPage = page(PurchaseOrderListPage.class);
    public List<Integer> purchaseOrderId;

    @Override
    @AfterEach
    public void tearDown() {
        //get PO id
        GetAllPurchaseOrdersRequest getAllPurchaseOrdersRequest = new GetAllPurchaseOrdersRequest();
        purchaseOrderId = getAllPurchaseOrdersRequest.getResponseWithAllPurchaseOrders(token)
                .extract()
                .body()
                .jsonPath().getList("id");
        //delete PO
        DeletePurchaseOrderRequest deletePurchaseOrderRequest = new DeletePurchaseOrderRequest();
        deletePurchaseOrderRequest.getResponseForDeletingPurchaseOrder(token, purchaseOrderId.get(purchaseOrderId.size() - 1));
        Selenide.closeWindow();
    }

    @Test
    public void createPurchaseOrderWithSpecifiedBuyer() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSpecifiedBuyer(id, name, phoneNumber, email)
                .waitForLoadPurchaseOrdersPage(id);

        assertTrue(purchaseOrderListPage.createdPurchaseOrderIdIsDisplayed(id));
    }

    @Test
    public void createPurchaseOrderWithSelectedBuyer() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .waitForLoadPurchaseOrdersPage(id);

        assertTrue(purchaseOrderListPage.createdPurchaseOrderIdIsDisplayed(id));
    }

    @Test
    public void createPurchaseOrderWithCustomCode() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithCustomCode(id, customCode, numbersQuantity, sequenceStartNumber, totalSealsQuantity)
                .waitForLoadPurchaseOrdersPage(id);

        assertTrue(purchaseOrderListPage.createdPurchaseOrderIdIsDisplayed(id));
    }

}
