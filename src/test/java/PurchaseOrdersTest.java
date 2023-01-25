import constants.Credentials;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import pageObject.productionSet.PurchaseOrdersPage;

import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurchaseOrdersTest extends BaseTest {
    public String id = RandomStringUtils.randomAlphanumeric(7);
    public String name = RandomStringUtils.randomAlphabetic(7);
    public String phoneNumber = RandomStringUtils.randomNumeric(7);
    public String email = RandomStringUtils.randomAlphanumeric(7) + "@gmail.com";
    public String customCode = RandomStringUtils.randomAlphanumeric(3);
    public String numbersQuantity = "10";
    public String sequenceStartNumber = "1";
    public String totalSealsQuantity = RandomStringUtils.randomNumeric(5);
    PurchaseOrdersPage purchaseOrdersPage = page(PurchaseOrdersPage.class);

    @Test
    public void createPurchaseOrderWithSpecifiedBuyer() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSpecifiedBuyer(id, name, phoneNumber, email);

        purchaseOrdersPage.waitForLoadPurchaseOrdersPage(id);
        assertTrue(purchaseOrdersPage.createdPurchaseOrderIdIsDisplayed(id));
    }

    @Test
    public void checkPurchaseOrderState() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSpecifiedBuyer(id, name, phoneNumber, email);

        purchaseOrdersPage.waitForLoadPurchaseOrdersPage(id);
        assertEquals("ENTERED", purchaseOrdersPage.getPurchaseOrderState());
    }

    @Test
    public void checkPurchaseOrderCompany() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSpecifiedBuyer(id, name, phoneNumber, email);

        purchaseOrdersPage.waitForLoadPurchaseOrdersPage(id);
        assertEquals("TestCompanyForAutoTests", purchaseOrdersPage.getPurchaseOrderCompany());
    }

    @Test
    public void checkPurchaseOrderBuyer() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSpecifiedBuyer(id, name, phoneNumber, email);

        purchaseOrdersPage.waitForLoadPurchaseOrdersPage(id);
        assertEquals(name, purchaseOrdersPage.getPurchaseOrderBuyer());
    }

    //Change test user
    @Test
    public void checkPurchaseOrderOwner() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSpecifiedBuyer(id, name, phoneNumber, email);

        purchaseOrdersPage.waitForLoadPurchaseOrdersPage(id);
        assertEquals("Dima", purchaseOrdersPage.getPurchaseOrderOwner());
    }

    @Test
    public void createPurchaseOrderWithSelectedBuyer() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id);

        purchaseOrdersPage.waitForLoadPurchaseOrdersPage(id);
        assertTrue(purchaseOrdersPage.createdPurchaseOrderIdIsDisplayed(id));
    }

    @Test
    public void createPurchaseOrderWithCustomCode() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithCustomCode(id, customCode, numbersQuantity, sequenceStartNumber, totalSealsQuantity);

        purchaseOrdersPage.waitForLoadPurchaseOrdersPage(id);
        assertTrue(purchaseOrdersPage.createdPurchaseOrderIdIsDisplayed(id));
    }
}

