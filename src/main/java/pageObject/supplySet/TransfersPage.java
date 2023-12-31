package pageObject.supplySet;

import com.codeborne.selenide.SelenideElement;
import constants.Entities;
import constants.TransferStates;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.page;

public class TransfersPage {
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]/div[1]/div[2]//span")
    private SelenideElement containerNumber;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]/div[1]/div[4]//span")
    private SelenideElement containerSealType;
    @FindBy(how = How.CSS, using = "div.ui-table-row-wrapper:nth-child(1) .ui-color-sign")
    private SelenideElement containerSealColor;
    @FindBy(how = How.CSS, using = "div.ui-table-row-wrapper:nth-child(1) .ui-table-number-cell-value")
    private SelenideElement containersQuantity;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]/div[1]/div[7]//span")
    private SelenideElement containerLogo;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]/div[1]/div[8]//span")
    private SelenideElement containersStartNumber;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]/div[1]/div[9]//span")
    private SelenideElement containersEndNumber;
    @FindBy(how = How.CSS, using = "div.ui-table-row:nth-child(1)>div:nth-child(10) .ui-btn-label")
    private SelenideElement containersStateInTheTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]/div[1]/div[11]//span")
    private SelenideElement containersCommentInTheTable;
    @FindBy(how = How.CSS, using = "div.ui-page-header .ui-btn-label")
    private SelenideElement containersStateInTheTopRightCorner;
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
    private SelenideElement sealTypeInReceiveTransferModal;
    @FindBy(how = How.CSS, using = ".ui-modal-content .ui-color-sign")
    private SelenideElement sealColorInReceiveTransferModal;
    @FindBy(how = How.CSS, using = "div.ui-modal-content .ui-table-number-cell-value")
    private SelenideElement containersQuantityInReceiveTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-content']//div[@class='ui-table-row']/div[6]/span")
    private SelenideElement logoInReceiveTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-content']//div[@class='ui-table-row']/div[7]/span")
    private SelenideElement containersStartNumberInReceiveTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-content']//div[@class='ui-table-row']/div[8]/span")
    private SelenideElement containersEndNumberInReceiveTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//span[text()='Test User']")
    protected SelenideElement userName;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Warehouse & Inventory Management')]")
    protected SelenideElement warehouseAndInventoryManagement;

    public void checkContainerNumber(String containerNumber) {
        this.containerNumber.shouldHave(exactText(containerNumber));
    }

    public void checkContainerSealType() {
        containerSealType.shouldHave(exactText(Entities.SEAL_TYPE));
    }

    public void checkContainerColor() {
        containerSealColor.shouldHave(attribute("style", Entities.GRAY_COLOR_OF_THE_SEAL));
    }

    public void checkContainersQuantity(String containersQuantity) {
        this.containersQuantity.shouldHave(exactText(containersQuantity));
    }

    public void checkContainerLogo() {
        containerLogo.shouldHave(exactText(Entities.LOGO));
    }

    public void checkContainersStartNumber(String containersStartNumber) {
        this.containersStartNumber.shouldHave(exactText(containersStartNumber));
    }

    public void checkContainersEndNumber(String containersEndNumber) {
        this.containersEndNumber.shouldHave(exactText(containersEndNumber));
    }

    public void checkContainersTransitStateInTheTable() {
        containersStateInTheTable.shouldHave(exactText(TransferStates.TRANSIT.getState()));
    }

    public void checkContainersProblemStateInTheTable() {
        containersStateInTheTable.shouldHave(exactText(TransferStates.PROBLEM.getState()));
    }

    public void checkContainersLostStateInTheTable() {
        containersStateInTheTable.shouldHave(exactText(TransferStates.LOST.getState()));
    }

    public void checkContainersCommentInTheTable(String comment) {
        containersCommentInTheTable.shouldHave(exactText(comment));
    }

    public void checkTransitStateInTheTopRightCorner() {
        containersStateInTheTopRightCorner.shouldHave((exactText(TransferStates.TRANSIT.getState())));
    }

    public void checkReceivedStateInTheTopRightCorner() {
        containersStateInTheTopRightCorner.shouldHave((exactText(TransferStates.RECEIVED.getState())));
    }

    public void checkReceivedWithAProblemStateInTheTopRightCorner() {
        containersStateInTheTopRightCorner.shouldHave((exactText(TransferStates.RECEIVED_WITH_A_PROBLEM.getState())));
    }

    private void clickStateLabelInTheTopRightCorner() {
        containersStateInTheTopRightCorner.click();
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

    public void checkContainerNumberInReceiveTransferModal(String containerNumber) {
        clickStateLabelInTheTopRightCorner();
        containerNumberInReceiveTransferModal.shouldHave(exactText(containerNumber));
    }

    public void checkSealTypeInReceiveTransferModal() {
        clickStateLabelInTheTopRightCorner();
        sealTypeInReceiveTransferModal.shouldHave(exactText(Entities.SEAL_TYPE));
    }

    public void checkSealColorInReceiveTransferModal() {
        clickStateLabelInTheTopRightCorner();
        sealColorInReceiveTransferModal.shouldHave(attribute("style", Entities.GRAY_COLOR_OF_THE_SEAL));
    }

    public void checkContainersQuantityInReceiveTransferModal(String containersQuantity) {
        clickStateLabelInTheTopRightCorner();
        containersQuantityInReceiveTransferModal.shouldHave(exactText(containersQuantity));
    }

    public void checkContainerLogoInReceiveTransferModal() {
        clickStateLabelInTheTopRightCorner();
        logoInReceiveTransferModal.shouldHave(exactText(Entities.LOGO));
    }

    public void checkContainersStartNumberInReceiveTransferModal(String containersStartNumber) {
        clickStateLabelInTheTopRightCorner();
        containersStartNumberInReceiveTransferModal.shouldHave(exactText(containersStartNumber));
    }

    public void checkContainersEndNumberInReceiveTransferModal(String containersEndNumber) {
        clickStateLabelInTheTopRightCorner();
        containersEndNumberInReceiveTransferModal.shouldHave(exactText(containersEndNumber));
    }

    public void checkReceivedStateOnLabelInTheTopRightCorner() {
        clickStateLabelInTheTopRightCorner();
        clickReceivedStateButtonInReceiveTransferModal();
        clickReceiveButtonInReceivedTransferModal();
        checkReceivedStateInTheTopRightCorner();
    }

    public void checkReceivedWithAProblemStateOnLabelByLostButtonInReceiveTransferModal() {
        clickStateLabelInTheTopRightCorner();
        clickLostStateButtonInReceiveTransferModal();
        clickReceiveButtonInReceivedTransferModal();
        checkReceivedWithAProblemStateInTheTopRightCorner();
    }

    public void checkReceivedWithAProblemStateOnLabelByProblemButtonInReceiveTransferModal(String comment) {
        clickStateLabelInTheTopRightCorner();
        clickProblemStateButtonInReceiveTransferModal();
        setCommentInCommentModal(comment);
        clickOkButtonInCommentModal();
        clickReceiveButtonInReceivedTransferModal();
        checkReceivedWithAProblemStateInTheTopRightCorner();
    }

    public TransfersPage switchStateToReceive() {
        clickStateLabelInTheTopRightCorner();
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
