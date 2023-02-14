import constants.Credentials;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import pageObject.productionSet.WorkOrderPage;

import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorkOrderPageTest extends BaseTest {
    public String id = RandomStringUtils.randomAlphanumeric(7);
    public String quantityOfSeals = RandomStringUtils.randomNumeric(5);
    public String notes = RandomStringUtils.randomAlphanumeric(20);
    WorkOrderPage workOrderPage = page(WorkOrderPage.class);

    @Test
    public void changeStateOfWorkOrderToConfirmed() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .createWorkOrder(quantityOfSeals, notes)
                .waitForNewWorkOrder()
                .openWorkOrderPage()
                .changeStateOfWorkOrder()
                .waitForContainersTable();

        assertEquals(Credentials.STATE_CONFIRMED, workOrderPage.getWorkOrderState());
    }

    @Test
    public void containersTableAppearsAfterChangingStateToConfirmed() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .createWorkOrder(quantityOfSeals, notes)
                .waitForNewWorkOrder()
                .openWorkOrderPage()
                .changeStateOfWorkOrder()
                .waitForContainersTable();

        assertTrue(workOrderPage.containersTableIsDisplayed());
    }

    @Test
    public void containersStateIsInProductionInTheContainersTableAfterChangingWorkOrderStateToConfirmed() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .createWorkOrder(quantityOfSeals, notes)
                .waitForNewWorkOrder()
                .openWorkOrderPage()
                .changeStateOfWorkOrder()
                .waitForContainersTable();

        workOrderPage.containersStateShouldBeInProduction();
    }

    @Test
    public void containersStateIsProducedInTheContainersTableAfterChangingWorkOrderStateToProduced() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .createWorkOrder(quantityOfSeals, notes)
                .waitForNewWorkOrder()
                .openWorkOrderPage()
                .changeStateOfWorkOrder()
                .waitForContainersTable()
                .changeStateOfWorkOrder()
                .clickOkButtonInTheUpdateWorOrderStateConfirmationModal();

        workOrderPage.containersStateShouldBeProduced();
    }

    @Test
    public void boxesStateIsProducedInTheContainersTableAfterChangingSkidStateToProduced() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .createWorkOrder(quantityOfSeals, notes)
                .waitForNewWorkOrder()
                .openWorkOrderPage()
                .changeStateOfWorkOrder()
                .waitForContainersTable()
                .changeStateOfTheSkid()
                .firstChildContainerIsBox();

        workOrderPage.containersStateShouldBeProduced();
    }

    @Test
    public void bagsStateIsProducedInTheContainersTableAfterChangingBoxStateToProduced() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .createWorkOrder(quantityOfSeals, notes)
                .waitForNewWorkOrder()
                .openWorkOrderPage()
                .changeStateOfWorkOrder()
                .waitForContainersTable()
                .changeStateOfTheBox()
                .firstChildContainerIsBag();

        workOrderPage.containersStateShouldBeProduced();
    }

    @Test
    public void sealsStateIsProducedInTheContainersTableAfterChangingBagStateToProduced() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openPurchaseOrdersPage()
                .createPurchaseOrderWithSelectedBuyer(id)
                .openPurchaseOrderPage(id)
                .addSealGroup(quantityOfSeals)
                .createWorkOrder(quantityOfSeals, notes)
                .waitForNewWorkOrder()
                .openWorkOrderPage()
                .changeStateOfWorkOrder()
                .waitForContainersTable()
                .changeStateOfTheBag()
                .firstChildContainerIsSeal();

        workOrderPage.containersStateShouldBeProduced();
    }
}
