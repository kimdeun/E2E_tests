package pageObject.supplySet;

import com.codeborne.selenide.SelenideElement;
import constants.Credentials;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.page;

public class TransfersListPage {
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Transfers')]")
    private SelenideElement transfersButtonInTheNavigationMenu;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//ul[@class='nav ui-navigation-navs']/li[3]/a[contains(text(), 'Warehouse')]")
    private SelenideElement warehouseButtonInTheNavigationMenu;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div/div[2]/a")
    private SelenideElement transfersId;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div/div[3]/a")
    private SelenideElement transfersSender;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div/div[4]/a")
    private SelenideElement transfersSource;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div/div[5]/a")
    private SelenideElement transfersDestination;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div/div[6]/a")
    private SelenideElement transfersReceiver;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div/div[7]/a")
    private SelenideElement transfersOwner;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div/div[8]//div[@class='ui-table-number-cell-value']")
    private SelenideElement transfersContainersQuantity;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div/div[9]/button")
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
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-content']//div[@class='ui-table-content']/div/div[1]/div[4]/span")
    private SelenideElement containerSealColorInReceiveTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-content']//div[@class='ui-table-row']/div[5]/div[1]/div[1]")
    private SelenideElement containersQuantityInReceiveTransferModal;
//    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-content']//div[@class='ui-table-row']/div[6]/span")
//    private SelenideElement containerLogoInReceiveTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div[7]/span")
    private SelenideElement containersStartNumberInReceiveTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div[8]/span")
    private SelenideElement containersEndNumberInReceiveTransferModal;

    public TransfersPage openTransfersPage() {
        transfersId.click();
        return page(TransfersPage.class);
    }

    public void checkTransfersSender() {
        transfersSender.shouldHave(exactText(Credentials.RED_FLAG_COMPANY));
    }

    public void checkTransfersDestination() {
        transfersDestination.shouldHave(exactText(Credentials.COMPANY_LOCATION));
    }

    public void checkTransfersSource() {
        transfersSource.shouldHave(exactText(Credentials.MILWAKEE));
    }

    public void checkTransfersReceiver() {
        transfersReceiver.shouldHave(exactText(Credentials.USERS_COMPANY));
    }

    public void checkTransfersOwner() {
        transfersOwner.shouldHave(exactText(Credentials.USER_NAME));
    }

    public void checkTransfersContainersQuantity(String containersQuantity) {
        transfersContainersQuantity.shouldHave(exactText(containersQuantity));
    }

    public void checkTransfersTransitState() {
        transfersState.shouldHave(exactText(Credentials.TRANSIT_STATE));
    }
    private void checkTransfersReceivedState() {
        transfersState.shouldHave(exactText(Credentials.RECEIVED_STATE));
    }
    private void checkTransfersReceivedWithAProblemState() {
        transfersState.shouldHave(exactText(Credentials.RECEIVED_WITH_A_PROBLEM_STATE));
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
        containerSealTypeInReceiveTransferModal.shouldHave(exactText(Credentials.SEAL_TYPE));
    }

    public void checkSealColorInReceiveTransferModal() {
        clickTransfersState();
        containerSealColorInReceiveTransferModal.shouldHave(attribute("style", Credentials.GRAY_COLOR_OF_THE_SEAL));
    }

    public void checkContainersQuantityInReceiveTransferModal(String containersQuantity) {
        clickTransfersState();
        containersQuantityInReceiveTransferModal.shouldHave(exactText(containersQuantity));
    }

//    public void checkContainerLogoInReceiveTransferModal() {
//        clickTransfersState();
//        containerLogoInReceiveTransferModal.shouldHave(exactText(Credentials.LOGO));
//    }

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
}
