package pageObject.productionSet;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import constants.Credentials;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class PurchaseOrderPage {
    @FindBy(how = How.XPATH, using = ".//div[text()='+ Add seals']")
    private SelenideElement addSealsButton;

    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select type']")
    private SelenideElement typeField;

    @FindBy(how = How.XPATH, using = ".//div[@class='ui-form']/div[1]//li[1]")
    private SelenideElement firstTypeInTheList;

    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select color']")
    private SelenideElement colorField;

    @FindBy(how = How.XPATH, using = ".//div[@class='ui-form']/div[2]//li[1]")
    private SelenideElement firstColorInTheList;

    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Quantity']")
    private SelenideElement quantityField;

    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Ok')]")
    private SelenideElement okButtonInTheAddSealGroupModal;

    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']")
    private SelenideElement contentInTheTable;

    @FindBy(how = How.XPATH, using = ".//div[@class='ui-page-label ml-auto']/span")
    private SelenideElement stateLabel;

    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Mark as']")
    private SelenideElement markAsInput;

    @FindBy(how = How.XPATH, using = ".//div[@class='ui-form-group']//a")
    private SelenideElement newStateOfPurchaseOrder;

    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Ok')]")
    private SelenideElement okButtonInTheUpdateStateModal;

    @FindBy(how = How.XPATH, using = ".//button[contains(text(), 'Create work order')]")
    private SelenideElement createWorkOrderButton;

    public void addSealGroup(String quantityOfSeals) {
        addSealsButton.click();
        typeField.click();
        firstTypeInTheList.click();
        colorField.click();
        firstColorInTheList.click();
        quantityField.clear();
        quantityField.setValue(quantityOfSeals);
        okButtonInTheAddSealGroupModal.click();
    }

    public boolean contentInTheTableIsDisplayed() {
        contentInTheTable.shouldBe(Condition.visible);
        return contentInTheTable.isDisplayed();
    }

    public PurchaseOrderPage changeStateOfPurchaseOrder() {
        stateLabel.click();
        markAsInput.click();
        newStateOfPurchaseOrder.click();
        okButtonInTheUpdateStateModal.click();
        return page(this);
    }

    public PurchaseOrderPage waitForConfirmedState() {
        $(stateLabel).shouldHave(Condition.exactText(Credentials.STATE_CONFIRMED));
        return page(this);
    }

    public PurchaseOrderPage waitForProducedState() {
        $(stateLabel).shouldHave(Condition.exactText(Credentials.STATE_PRODUCED));
        return page(this);
    }

    public String getPurchaseOrderState() {
        String state = stateLabel.getText();
        return state;
    }
}
