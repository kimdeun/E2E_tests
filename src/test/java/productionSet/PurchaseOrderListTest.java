package productionSet;

import baseTests.BaseTest;
import constants.Credentials;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import pageObject.productionSet.PurchaseOrderListPage;

import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurchaseOrderListTest extends BaseTest {
    public String id = RandomStringUtils.randomAlphanumeric(7);
    public String name = RandomStringUtils.randomAlphabetic(7);
    public String phoneNumber = RandomStringUtils.randomNumeric(7);
    public String email = RandomStringUtils.randomAlphanumeric(7) + "@gmail.com";
    public String customCode = RandomStringUtils.randomAlphanumeric(3);
    public String numbersQuantity = "10";
    public String sequenceStartNumber = "1";
    public String totalSealsQuantity = RandomStringUtils.randomNumeric(5);
    PurchaseOrderListPage purchaseOrderListPage = page(PurchaseOrderListPage.class);

    @Test
    public void createPurchaseOrderWithSpecifiedBuyer() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSpecifiedBuyer(id, name, phoneNumber, email)
                .waitForLoadPurchaseOrdersPage(id);

        assertTrue(purchaseOrderListPage.createdPurchaseOrderIdIsDisplayed(id));
    }

    @Test
    public void checkPurchaseOrderState() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSpecifiedBuyer(id, name, phoneNumber, email)
                .waitForLoadPurchaseOrdersPage(id);

        assertEquals(Credentials.STATE_ENTERED, purchaseOrderListPage.getPurchaseOrderState());
    }

    @Test
    public void checkPurchaseOrderCompany() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSpecifiedBuyer(id, name, phoneNumber, email)
                .waitForLoadPurchaseOrdersPage(id);

        assertEquals(Credentials.USERS_COMPANY, purchaseOrderListPage.getPurchaseOrderCompany());
    }

    @Test
    public void checkPurchaseOrderBuyer() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSpecifiedBuyer(id, name, phoneNumber, email)
                .waitForLoadPurchaseOrdersPage(id);

        assertEquals(name, purchaseOrderListPage.getPurchaseOrderBuyer());
    }

    @Test
    public void checkPurchaseOrderOwner() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSpecifiedBuyer(id, name, phoneNumber, email)
                .waitForLoadPurchaseOrdersPage(id);

        assertEquals(Credentials.USER_NAME, purchaseOrderListPage.getPurchaseOrderOwner());
    }

    @Test
    public void createPurchaseOrderWithSelectedBuyer() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .waitForLoadPurchaseOrdersPage(id);

        assertTrue(purchaseOrderListPage.createdPurchaseOrderIdIsDisplayed(id));
    }

    @Test
    public void createPurchaseOrderWithCustomCode() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithCustomCode(id, customCode, numbersQuantity, sequenceStartNumber, totalSealsQuantity)
                .waitForLoadPurchaseOrdersPage(id);

        assertTrue(purchaseOrderListPage.createdPurchaseOrderIdIsDisplayed(id));
    }
}

