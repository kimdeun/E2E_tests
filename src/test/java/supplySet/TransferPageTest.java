package supplySet;

import baseTests.BaseTest;
import constants.Credentials;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import pageObject.productionSet.WarehousePage;

import static com.codeborne.selenide.Selenide.page;

public class TransferPageTest extends BaseTest {
    WarehousePage warehousePage = page(WarehousePage.class);

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
                .openTransfersPage()
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
                .openTransfersPage()
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
                .openTransfersPage()
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
                .openTransfersPage()
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
//                .openTransfersPage()
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
                .openTransfersPage()
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
                .openTransfersPage()
                .checkContainersEndNumber(containersEndNumber);
    }

    @Test
    public void checkReceivedStateOnLabelInTheTopRightCorner() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .openTransfersPage()
                .checkReceivedStateOnLabelInTheTopRightCorner();
    }

    @Test
    public void checkReceivedWithAProblemStateOnLabelInTheTopRightCornerByLostButton() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .openTransfersPage()
                .checkReceivedWithAProblemStateOnLabelByLostButtonInReceiveTransferModal();
    }

    @Test
    public void checkReceivedWithAProblemStateOnLabelInTheTopRightCornerByProblemButton() {
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
                .openTransfersPage()
                .checkReceivedWithAProblemStateOnLabelByProblemButtonInReceiveTransferModal(comment);
    }
}
