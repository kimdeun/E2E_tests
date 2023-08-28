package pageObject.supplySet;

import com.codeborne.selenide.SelenideElement;
import constants.Entities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.page;

public class TransfersListPage {
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Transfers')]")
    private SelenideElement transfersButtonInTheNavigationMenu;
    @FindBy(how = How.CSS, using = "div.ui-table-row-wrapper:nth-child(1) a.ui-link[href^='#/supply/transfer/']")
    private SelenideElement transfersId;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]/div[1]/div[3]//a")
    private SelenideElement transfersSender;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]/div/div[4]//a[@href='javascript:void(0)']")
    private SelenideElement transfersSource;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]/div/div[5]//a[@href='javascript:void(0)']")
    private SelenideElement transfersDestination;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]/div[1]/div[6]//a")
    private SelenideElement transfersReceiver;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]/div[1]/div[7]//a")
    private SelenideElement transfersOwner;
    @FindBy(how = How.CSS, using = "div.ui-table-row-wrapper:nth-child(1) .ui-table-number-cell-value")
    private SelenideElement transfersContainersQuantity;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]/div[1]/div[9]//button")
    private SelenideElement transfersState;
    @FindBy(how = How.CSS, using = ".ui-table-cell.ui-table-btn-group-cell>button:nth-of-type(1)")
    private SelenideElement lostStateButtonInReceiveTransferModal;
    @FindBy(how = How.CSS, using = ".ui-table-cell.ui-table-btn-group-cell>button:nth-of-type(2)")
    private SelenideElement problemStateButtonInReceiveTransferModal;
    @FindBy(how = How.CSS, using = ".ui-table-cell.ui-table-btn-group-cell>button:nth-of-type(3)")
    private SelenideElement receivedStateButtonInReceiveTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-footer clearfix']/div[contains(text(), 'Receive')]")
    private SelenideElement receiveButtonInReceiveTransferModal;
    @FindBy(how = How.CSS, using = "textarea[placeholder='Enter a description of the problem']")
    private SelenideElement textAreaInCommentModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-footer clearfix']/div[contains(text(), 'Ok')]")
    private SelenideElement okButtonInCommentModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-content']//div[@class='ui-table-content']/div/div[1]/div[2]/span")
    private SelenideElement containerNumberInReceiveTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-content']//div[@class='ui-table-content']/div/div[1]/div[3]/span")
    private SelenideElement containerSealTypeInReceiveTransferModal;
    @FindBy(how = How.CSS, using = ".ui-color-sign")
    private SelenideElement containerSealColorInReceiveTransferModal;
    @FindBy(how = How.CSS, using = "div.ui-modal-content .ui-table-number-cell-value")
    private SelenideElement containersQuantityInReceiveTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-content']//div[@class='ui-table-row']/div[6]/span")
    private SelenideElement containerLogoInReceiveTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-content']//div[@class='ui-table-row']/div[7]/span")
    private SelenideElement containersStartNumberInReceiveTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-content']//div[@class='ui-table-row']/div[8]/span")
    private SelenideElement containersEndNumberInReceiveTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//span[text()='Test User']")
    protected SelenideElement userName;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Warehouse & Inventory Management')]")
    protected SelenideElement warehouseAndInventoryManagement;

    public TransfersPage openTransfersPage() {
        transfersId.click();
        return page(TransfersPage.class);
    }

    public void checkTransfersSender() {
        transfersSender.shouldHave(exactText(Entities.RED_FLAG_COMPANY));
    }

    public void checkTransfersDestination() {
        transfersDestination.shouldHave(exactText(Entities.COMPANY_LOCATION));
    }

    public void checkTransfersSource() {
        transfersSource.shouldHave(exactText(Entities.MILWAKEE));
    }

    public void checkTransfersReceiver() {
        transfersReceiver.shouldHave(exactText(Entities.USERS_COMPANY));
    }

    public void checkTransfersOwner() {
        transfersOwner.shouldHave(exactText(Entities.USER_NAME));
    }

    public void checkTransfersContainersQuantity(String containersQuantity) {
        transfersContainersQuantity.shouldHave(exactText(containersQuantity));
    }

    public void checkTransfersTransitState() {
        transfersState.shouldHave(exactText(Entities.TRANSIT_STATE));
    }
    private void checkTransfersReceivedState() {
        transfersState.shouldHave(exactText(Entities.RECEIVED_STATE));
    }
    private void checkTransfersReceivedWithAProblemState() {
        transfersState.shouldHave(exactText(Entities.RECEIVED_WITH_A_PROBLEM_STATE));
    }

    private void clickTransfersState() {
        transfersState.click();
    }

    private void clickLostStateButtonInReceiveTransferModal() {
        lostStateButtonInReceiveTransferModal.click();
    }

    private void clickProblemStateButtonInReceiveTransferModal() {
        problemStateButtonInReceiveTransferModal.click();
    }

    public void clickReceivedStateButtonInReceiveTransferModal() {
        receivedStateButtonInReceiveTransferModal.click();
    }

    private void clickReceiveButtonInReceivedTransferModal() {
        receiveButtonInReceiveTransferModal.click();
    }

    private void setCommentInCommentModal(String comment) {
        textAreaInCommentModal.setValue(comment);
    }

    private void clickOkButtonInCommentModal() {
        okButtonInCommentModal.click();
    }

    public void checkReceivedStateOfAContainer() {
        clickTransfersState();
        clickReceivedStateButtonInReceiveTransferModal();
        clickReceiveButtonInReceivedTransferModal();
        checkTransfersReceivedState();
    }

    public void checkReceivedWithAProblemStateByLostButtonInReceiveTransferModal() {
        clickTransfersState();
        clickLostStateButtonInReceiveTransferModal();
        clickReceiveButtonInReceivedTransferModal();
        checkTransfersReceivedWithAProblemState();
    }

    public void checkReceivedWithAProblemStateByProblemButtonInReceiveTransferModal(String comment) {
        clickTransfersState();
        clickProblemStateButtonInReceiveTransferModal();
        setCommentInCommentModal(comment);
        clickOkButtonInCommentModal();
        clickReceiveButtonInReceivedTransferModal();
        checkTransfersReceivedWithAProblemState();
    }

    public void checkContainerNumberInReceiveTransferModal(String containerNumber) {
        clickTransfersState();
        containerNumberInReceiveTransferModal.shouldHave(exactText(containerNumber));
    }

    public void checkSealTypeInReceiveTransferModal() {
        clickTransfersState();
        containerSealTypeInReceiveTransferModal.shouldHave(exactText(Entities.SEAL_TYPE));
    }

    public void checkSealColorInReceiveTransferModal() {
        clickTransfersState();
        containerSealColorInReceiveTransferModal.shouldHave(attribute("style", Entities.GRAY_COLOR_OF_THE_SEAL));
    }

    public void checkContainersQuantityInReceiveTransferModal(String containersQuantity) {
        clickTransfersState();
        containersQuantityInReceiveTransferModal.shouldHave(exactText(containersQuantity));
    }

    public void checkContainerLogoInReceiveTransferModal() {
        clickTransfersState();
        containerLogoInReceiveTransferModal.shouldHave(exactText(Entities.LOGO));
    }

    public void checkContainersStartNumber(String containersStartNumber) {
        clickTransfersState();
        containersStartNumberInReceiveTransferModal.shouldHave(exactText(containersStartNumber));
    }

    public void checkContainersEndNumber(String containersEndNumber) {
        clickTransfersState();
        containersEndNumberInReceiveTransferModal.shouldHave(exactText(containersEndNumber));
    }

    public TransfersListPage switchStateToLost() {
        clickTransfersState();
        clickLostStateButtonInReceiveTransferModal();
        clickReceiveButtonInReceivedTransferModal();
        return page(this);
    }

    public TransfersListPage switchStateToProblem() {
        clickTransfersState();
        clickProblemStateButtonInReceiveTransferModal();
        clickOkButtonInCommentModal();
        clickReceiveButtonInReceivedTransferModal();
        return page(this);
    }

    public TransfersListPage switchStateToProblemWithComment(String comment) {
        clickTransfersState();
        clickProblemStateButtonInReceiveTransferModal();
        setCommentInCommentModal(comment);
        clickOkButtonInCommentModal();
        clickReceiveButtonInReceivedTransferModal();
        return page(this);
    }

    public TransfersListPage switchStateToReceive() {
        clickTransfersState();
        clickReceivedStateButtonInReceiveTransferModal();
        clickReceiveButtonInReceivedTransferModal();
        return page(this);
    }

    public BaseSupplySetPage openSupplySet() {
        userName.click();
        warehouseAndInventoryManagement.click();
        return page(BaseSupplySetPage.class);
    }
}
