import constants.Credentials;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import pageObject.productionSet.PurchaseOrderPage;
import pageObject.productionSet.WorkOrderListPage;

import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorkOrderListTest extends BaseTest {
    public String id = RandomStringUtils.randomAlphanumeric(7);
    public String quantityOfSeals = RandomStringUtils.randomNumeric(5);
    public String notes = RandomStringUtils.randomAlphanumeric(20);
    WorkOrderListPage workOrderListPage = page(WorkOrderListPage.class);
    PurchaseOrderPage purchaseOrderPage = page(PurchaseOrderPage.class);

    @Test
    public void createWorkOrder() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .openWorkOrdersPage()
                .createWorkOrder(id, quantityOfSeals, notes)
                .waitForLoadWorkOrdersPage(id);

        assertTrue(workOrderListPage.createdWorkOrderIsDisplayed(id));
    }

    @Test
    public void checkWorkOrderState() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .openWorkOrdersPage()
                .createWorkOrder(id, quantityOfSeals, notes)
                .waitForLoadWorkOrdersPage(id);

        assertEquals(Credentials.STATE_ENTERED, workOrderListPage.getWorkOrderState());
    }

    @Test
    public void checkPurchaseOrderIdInTheTable() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .openWorkOrdersPage()
                .createWorkOrder(id, quantityOfSeals, notes)
                .waitForLoadWorkOrdersPage(id);

        assertEquals(id, workOrderListPage.getPurchaseOrderId());
    }

    @Test
    public void checkWorkOrderCompany() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .openWorkOrdersPage()
                .createWorkOrder(id, quantityOfSeals, notes)
                .waitForLoadWorkOrdersPage(id);

        assertEquals(Credentials.USERS_COMPANY, workOrderListPage.getWorkOrderCompany());
    }

    @Test
    public void checkWorkOrderOwner() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .openWorkOrdersPage()
                .createWorkOrder(id, quantityOfSeals, notes)
                .waitForLoadWorkOrdersPage(id);

        assertEquals(Credentials.USER_NAME, workOrderListPage.getWorkOrderOwner());
    }

    @Test
    public void checkWorkOrderSealType() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .openWorkOrdersPage()
                .createWorkOrder(id, quantityOfSeals, notes)
                .waitForLoadWorkOrdersPage(id);

        assertEquals(Credentials.SEAL_TYPE, workOrderListPage.getWorkOrderSealType());
    }

    @Test
    public void checkWorkOrderSealColor() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .openWorkOrdersPage()
                .createWorkOrder(id, quantityOfSeals, notes)
                .waitForLoadWorkOrdersPage(id);

        assertEquals(Credentials.GRAY_COLOR_OF_THE_SEAL, workOrderListPage.getWorkOrderSealColor());
    }

    @Test
    public void checkWorkOrderEtchingFormat() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .openWorkOrdersPage()
                .createWorkOrder(id, quantityOfSeals, notes)
                .waitForLoadWorkOrdersPage(id);

        assertEquals(Credentials.ETCHING_FORMAT, workOrderListPage.getWorkOrderEtchingFormat());
    }

    @Test
    public void checkWorkOrderLogo() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .openWorkOrdersPage()
                .createWorkOrder(id, quantityOfSeals, notes)
                .waitForLoadWorkOrdersPage(id);

        assertEquals(Credentials.LOGO, workOrderListPage.getWorkOrderLogo());
    }

    @Test
    public void createWorkOrderFromPurchaseOrderPage() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .createWorkOrder(quantityOfSeals, notes)
                .waitForNewWorkOrder();

        assertTrue(purchaseOrderPage.createdWorkOrderIsDisplayed());
    }

    @Test
    public void checkWorkOrderProductionOnThePurchaseOrderPage() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .createWorkOrder(quantityOfSeals, notes)
                .waitForNewWorkOrder();

        assertEquals(Credentials.USA_PRODUCTION, purchaseOrderPage.getWorkOrderProduction());
    }

    @Test
    public void checkWorkOrderStateOnThePurchaseOrderPage() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .createWorkOrder(quantityOfSeals, notes)
                .waitForNewWorkOrder();

        assertEquals(Credentials.STATE_ENTERED, purchaseOrderPage.getWorkOrderState());
    }

    @Test
    public void checkWorkOrderOwnerOnThePurchaseOrderPage() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .createWorkOrder(quantityOfSeals, notes)
                .waitForNewWorkOrder();

        assertEquals(Credentials.USER_NAME, purchaseOrderPage.getWorkOrderOwner());
    }

    @Test
    public void checkWorkOrderSealTypeOnThePurchaseOrderPage() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .createWorkOrder(quantityOfSeals, notes)
                .waitForNewWorkOrder();

        assertEquals(Credentials.SEAL_TYPE, purchaseOrderPage.getWorkOrderSealType());
    }

    @Test
    public void checkWorkOrderSealColorOnThePurchaseOrderPage() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .createWorkOrder(quantityOfSeals, notes)
                .waitForNewWorkOrder();

        assertEquals(Credentials.GRAY_COLOR_OF_THE_SEAL, purchaseOrderPage.getWorkOrderSealColor());
    }

    @Test
    public void checkWorkOrderEtchingFormatOnThePurchaseOrderPage() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .createWorkOrder(quantityOfSeals, notes)
                .waitForNewWorkOrder();

        assertEquals(Credentials.ETCHING_FORMAT, purchaseOrderPage.getWorkOrderEtchingFormat());
    }

    @Test
    public void checkWorkOrderLogoOnThePurchaseOrderPage() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .createWorkOrder(quantityOfSeals, notes)
                .waitForNewWorkOrder();

        assertEquals(Credentials.LOGO, purchaseOrderPage.getWorkOrderLogo());
    }
}
