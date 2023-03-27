import constants.Credentials;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import pageObject.productionSet.WarehousePage;

import static com.codeborne.selenide.Selenide.page;

public class WarehouseTest extends BaseTest {
    WarehousePage warehousePage = page(WarehousePage.class);

    @Test
    public void createTransferWithSkid() {
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
                .checkContainerNumber(containerNumber);
    }

    @Test
    public void createTransferWithBox() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent()
                .unfoldSkid();

        int containerIndex = warehousePage.getContainerIndexInTheList();
        String containerNumber = warehousePage.getContainerNumber(containerIndex);

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .openTransfersPage()
                .checkContainerNumber(containerNumber);
    }

    @Test
    public void createTransferWithBag() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent()
                .unfoldSkid()
                .unfoldBox();

        int containerIndex = warehousePage.getContainerIndexInTheList();
        String containerNumber = warehousePage.getContainerNumber(containerIndex);

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .openTransfersPage()
                .checkContainerNumber(containerNumber);
    }

    @Test
    public void checkSealTypeOfContainersInTransferOnTheTransferPage() {
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
                .checkContainerSealType();
    }

    @Test
    public void checkColorOfContainersInTransferOnTheTransferPage() {
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
                .checkContainerColor();
    }

    @Test
    public void checkQuantityOfContainersInTransferOnTheTransferPage() {
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
                .checkContainersQuantity(containerQuantity);
    }

//    @Test
//    public void checkLogoOfContainersInTransferOnTheTransferPage() {
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
//                .checkContainerLogo();
//    }

    @Test
    public void checkStartNumberOfContainersInTransferOnTheTransferPage() {
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
    public void checkEndNumberOfContainersInTransferOnTheTransferPage() {
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
    public void checkContainersTransitStateInTheTableOnTheTransferPage() {
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
                .checkContainersTransitStateInTheTable();
    }

    @Test
    public void checkContainersTransitStateInTheTopRightCornerOnTheTransferPage() {
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
                .checkContainersTransitStateInTheTopRightCorner();
    }

    @Test
    public void checkContainersSenderInTheTransfersListTable() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .checkTransfersSender();
    }

    @Test
    public void checkContainersDestinationInTheTransfersListTable() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .checkTransfersDestination();
    }

    @Test
    public void checkContainersSourceInTheTransfersListTable() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .checkTransfersSource();
    }

    @Test
    public void checkContainersReceiverInTheTransfersListTable() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .checkTransfersReceiver();
    }

    @Test
    public void checkContainersOwnerInTheTransfersListTable() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .checkTransfersOwner();
    }

    @Test
    public void checkContainersQuantityInTheTransfersListTable() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();
        String containersQuantity = warehousePage.getContainersQuantity(containerIndex);

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .checkTransfersContainersQuantity(containersQuantity);
    }

    @Test
    public void checkContainersStateInTheTransfersListTable() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openWarehousePage()
                .openDetailsTable()
                .waitForInventoryTableContent();

        int containerIndex = warehousePage.getContainerIndexInTheList();

        warehousePage.createTransfer(containerIndex)
                .openSupplySet()
                .openTransfersListPage()
                .checkTransfersTransitState();
    }
}
