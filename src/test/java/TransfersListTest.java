import constants.Credentials;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import pageObject.productionSet.WarehousePage;

import static com.codeborne.selenide.Selenide.page;

public class TransfersListTest extends BaseTest{
    WarehousePage warehousePage = page(WarehousePage.class);

    @Test
    public void checkReceivedStateOfContainerInTheTransfersTable() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .checkReceivedStateOfAContainer();
    }

    @Test
    public void checkReceivedWithAProblemStateOfContainerInTheTransfersTableByLostButtonInReceiveTransferModal() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .checkReceivedWithAProblemStateByLostButtonInReceiveTransferModal();
    }

    @Test
    public void checkReceivedWithAProblemStateOfContainerInTheTransfersTableByProblemButtonInReceiveTransferModal() {
        String comment = RandomStringUtils.randomAlphanumeric(7);
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .checkReceivedWithAProblemStateByProblemButtonInReceiveTransferModal(comment);
    }

    @Test
    public void checkContainerNumberInReceiveTransferModal() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();
        String containerNumber = warehousePage.getContainerNumber(containerIndex);

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .checkContainerNumberInReceiveTransferModal(containerNumber);
    }

    @Test
    public void checkSealTypeInReceiveTransferModal() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .checkSealTypeInReceiveTransferModal();
    }

    @Test
    public void checkSealColorInReceiveTransferModal() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .checkSealColorInReceiveTransferModal();
    }

    @Test
    public void checkContainersQuantityInReceiveTransferModal() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();
        String containerQuantity = warehousePage.getContainersQuantity(containerIndex);

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .checkContainersQuantityInReceiveTransferModal(containerQuantity);
    }

//    @Test
//    public void checkContainersLogoInReceiveTransferModal() {
//        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
//                .openProductionSetPage()
//                .openWarehousePage()
//                .openDetailsTable()
//                .waitForInventoryTableContent();
//
//        int containerIndex = warehousePage.getContainerIndexInTheList();
//
//        warehousePage.createTransfer(containerIndex)
//                .openSupplySet()
//                .openTransfersListPage()
//                .checkContainerLogoInReceiveTransferModal();
//    }

    @Test
    public void checkContainersStartNumberInReceiveTransferModal() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();
        String containersStartNumber = warehousePage.getContainersStartNumber(containerIndex);

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .checkContainersStartNumber(containersStartNumber);
    }

    @Test
    public void checkContainersEndNumberInReceiveTransferModal() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();
        String containersEndNumber = warehousePage.getContainersEndNumber(containerIndex);

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .checkContainersEndNumber(containersEndNumber);
    }

    @Test
    public void checkContainersLostStateInTheTableOnTheTransferPage() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .switchStateToLost()
                .openTransfersPage()
                .checkContainersLostStateInTheTable();
    }

    @Test
    public void checkContainersReceivedWithAProblemStateByLostButtonInTheTopRightCornerOnTheTransferPage() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .switchStateToLost()
                .openTransfersPage()
                .checkReceivedWithAProblemStateInTheTopRightCorner();
    }

    @Test
    public void checkContainersProblemStateInTheTableOnTheTransferPage() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .switchStateToProblem()
                .openTransfersPage()
                .checkContainersProblemStateInTheTable();
    }

    @Test
    public void checkContainersReceivedWithAProblemStateByProblemButtonInTheTopRightCornerOnTheTransferPage() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .switchStateToProblem()
                .openTransfersPage()
                .checkReceivedWithAProblemStateInTheTopRightCorner();
    }

    @Test
    public void checkCommentInTheTableOnTheTransferPage() {
        String comment = RandomStringUtils.randomAlphanumeric(7);
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .switchStateToProblemWithComment(comment)
                .openTransfersPage()
                .checkContainersCommentInTheTable(comment);
    }
}
