import constants.Credentials;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import pageObject.productionSet.PurchaseOrderPage;

import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurchaseOrderPageTest extends BaseTest {
    public String id = RandomStringUtils.randomAlphanumeric(7);
    public String quantityOfSeals = "50";
    PurchaseOrderPage purchaseOrderPage = page(PurchaseOrderPage.class);

    @Test
    public void changeStateOfPurchaseOrderToConfirmed() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .waitForLoadPurchaseOrdersPage(id)
                .openPurchaseOrderPage(id)
                .changeStateOfPurchaseOrder()
                .waitForConfirmedState();

        assertEquals(Credentials.STATE_CONFIRMED, purchaseOrderPage.getPurchaseOrderState());
    }

    @Test
    public void changeStateOfPurchaseOrderToProduced() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .waitForLoadPurchaseOrdersPage(id)
                .openPurchaseOrderPage(id)
                .changeStateOfPurchaseOrder()
                .changeStateOfPurchaseOrder()
                .waitForProducedState();

        assertEquals(Credentials.STATE_PRODUCED, purchaseOrderPage.getPurchaseOrderState());
    }

    @Test
    public void addSealGroupToPurchaseOrder() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .waitForLoadPurchaseOrdersPage(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals);

        assertTrue(purchaseOrderPage.contentInTheTableIsDisplayed());
    }
}
