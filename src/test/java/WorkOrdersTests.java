import constants.Credentials;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import pageObject.productionSet.WorkOrdersPage;

import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorkOrdersTests extends BaseTest {
    public String id = RandomStringUtils.randomAlphanumeric(7);
    public String quantityOfSeals = RandomStringUtils.randomNumeric(5);
    public String notes = RandomStringUtils.randomAlphanumeric(20);
    WorkOrdersPage workOrdersPage = page(WorkOrdersPage.class);

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

        assertTrue(workOrdersPage.createdWorkOrderIsDisplayed(id));
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

        assertEquals(Credentials.STATE_ENTERED, workOrdersPage.getWorkOrderState());
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

        assertEquals(id, workOrdersPage.getPurchaseOrderId());
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

        assertEquals(Credentials.USERS_COMPANY, workOrdersPage.getWorkOrderCompany());
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

        assertEquals(Credentials.USER_NAME, workOrdersPage.getWorkOrderOwner());
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

        assertEquals(Credentials.SEAL_TYPE, workOrdersPage.getWorkOrderSealType());
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

        assertEquals(Credentials.GRAY_COLOR_OF_THE_SEAL, workOrdersPage.getWorkOrderSealColor());
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

        assertEquals(Credentials.ETCHING_FORMAT, workOrdersPage.getWorkOrderEtchingFormat());
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

        assertEquals(Credentials.LOGO, workOrdersPage.getWorkOrderLogo());
    }
}
