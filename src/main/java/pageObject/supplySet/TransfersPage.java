package pageObject.supplySet;

import com.codeborne.selenide.SelenideElement;
import constants.Entities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.page;

public class TransfersPage {
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div/div[1]/div[2]/span")
    private SelenideElement containerNumber;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div/div[1]/div[4]/span")
    private SelenideElement containerSealType;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div/div[1]/div[5]/span")
    private SelenideElement containerSealColor;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div[6]/div[1]/div[1]")
    private SelenideElement containersQuantity;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div[7]/span")
    private SelenideElement containerLogo;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div[8]/span")
    private SelenideElement containersStartNumber;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div[9]/span")
    private SelenideElement containersEndNumber;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div[10]/span")
    private SelenideElement containersStateInTheTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div[11]/span")
    private SelenideElement containersCommentInTheTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-page-header']/div[2]//span")
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
    private SelenideElement containerSealTypeInReceiveTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-content']//div[@class='ui-table-content']/div/div[1]/div[4]/span")
    private SelenideElement containerSealColorInReceiveTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-content']//div[@class='ui-table-row']/div[5]/div[1]/div[1]")
    private SelenideElement containersQuantityInReceiveTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-content']//div[@class='ui-table-row']/div[6]/span")
    private SelenideElement containerLogoInReceiveTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-content']//div[@class='ui-table-row']/div[7]/span")
    private SelenideElement containersStartNumberInReceiveTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-content']//div[@class='ui-table-row']/div[8]/span")
    private SelenideElement containersEndNumberInReceiveTransferModal;

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
        containersStateInTheTable.shouldHave(exactText(Entities.TRANSIT_STATE));
    }

    public void checkContainersProblemStateInTheTable() {
        containersStateInTheTable.shouldHave(exactText(Entities.PROBLEM_STATE));
    }

    public void checkContainersLostStateInTheTable() {
        containersStateInTheTable.shouldHave(exactText(Entities.LOST_STATE));
    }

    public void checkContainersCommentInTheTable(String comment) {
        containersCommentInTheTable.shouldHave(exactText(comment));
    }

    public void checkTransitStateInTheTopRightCorner() {
        containersStateInTheTopRightCorner.shouldHave((exactText(Entities.TRANSIT_STATE)));
    }

    public void checkReceivedStateInTheTopRightCorner() {
        containersStateInTheTopRightCorner.shouldHave((exactText(Entities.TRANSIT_STATE)));
    }

    public void checkReceivedWithAProblemStateInTheTopRightCorner() {
        containersStateInTheTopRightCorner.shouldHave((exactText(Entities.RECEIVED_WITH_A_PROBLEM_STATE)));
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
        containerSealTypeInReceiveTransferModal.shouldHave(exactText(Entities.SEAL_TYPE));
    }

    public void checkSealColorInReceiveTransferModal() {
        clickStateLabelInTheTopRightCorner();
        containerSealColorInReceiveTransferModal.shouldHave(attribute("style", Entities.GRAY_COLOR_OF_THE_SEAL));
    }

    public void checkContainersQuantityInReceiveTransferModal(String containersQuantity) {
        clickStateLabelInTheTopRightCorner();
        containersQuantityInReceiveTransferModal.shouldHave(exactText(containersQuantity));
    }

    public void checkContainerLogoInReceiveTransferModal() {
        clickStateLabelInTheTopRightCorner();
        containerLogoInReceiveTransferModal.shouldHave(exactText(Entities.LOGO));
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
}
