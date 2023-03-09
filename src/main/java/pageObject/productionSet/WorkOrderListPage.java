package pageObject.productionSet;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import constants.Credentials;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class WorkOrderListPage {
    @FindBy(how = How.XPATH, using = ".//button[contains(text(), 'Create')]")
    private SelenideElement createWorkOrderButton;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select purchase order']")
    private SelenideElement purchaseOrderField;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-content']//input[@placeholder='Select production']")
    private SelenideElement productionField;
    @FindBy(how = How.XPATH, using = ".//span[contains(text(), 'Select etching format')]")
    private SelenideElement etchingFormatSpan;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select etching format']")
    private SelenideElement etchingFormatField;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-form']/div[5]/div[2]//li[2]")
    private SelenideElement firstEtchingFormat;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Enter quantity']")
    private SelenideElement quantityField;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select company']")
    private SelenideElement companyField;
    @FindBy(how = How.XPATH, using = ".//span[contains(text(), 'Select location')]")
    private SelenideElement locationSpan;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select location']")
    private SelenideElement locationField;
    @FindBy(how = How.XPATH, using = ".//textarea[@placeholder='Enter notes']")
    private SelenideElement notesField;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select Skid type']")
    private SelenideElement skidField;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select Box type']")
    private SelenideElement boxField;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select Bag type']")
    private SelenideElement bagField;
    @FindBy(how = How.XPATH, using = ".//div[@class='col-md-6']/div[2]/input[@placeholder='Enter quantity']")
    private SelenideElement boxesInSkidField;
    @FindBy(how = How.XPATH, using = ".//div[@class='col-md-6']/div[3]/input[@placeholder='Enter quantity']")
    private SelenideElement sealsInBoxField;
    @FindBy(how = How.XPATH, using = ".//div[@class='col-md-6']/div[5]/input[@placeholder='Enter quantity']")
    private SelenideElement sealsInBagField;
    @FindBy(how = How.XPATH, using = ".//span[contains(text(), 'Select enumeration mode')]")
    private SelenideElement enumerationFieldSpan;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select enumeration mode']")
    private SelenideElement enumerationField;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Ok')]")
    private SelenideElement okButtonInCreateWorkOrderModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]//span[contains(text(), 'Entered')]")
    private SelenideElement workOrderState;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div[1]/div[5]//a")
    private SelenideElement purchaseOrderId;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div[1]/div[6]//a")
    private SelenideElement workOrderCompany;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div[1]/div[7]//a")
    private SelenideElement workOrderOwner;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div[1]/div[8]//span")
    private SelenideElement workOrderSealType;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div[1]/div[9]//span")
    private SelenideElement workOrderSealColor;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div[1]/div[12]//span")
    private SelenideElement workOrderEtchingFormat;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div[1]/div[13]//span")
    private SelenideElement workOrderLogo;
    @FindBy(how = How.CSS, using = ".ui-app-hint.container.ui-error")
    private SelenideElement noTemplateHint;
    @FindBy(how = How.CSS, using = ".no-options")
    private SelenideElement emptyListOfDataInTheCreateWorkOrderModal;
    @FindBy(how = How.XPATH, using = ".//span[text()='Work orders']")
    private SelenideElement pageTitle;

    public WorkOrderListPage createWorkOrder(String id, String quantity, String notes) {
        pageTitle.shouldBe(Condition.visible);
        createWorkOrderButton.click();
        purchaseOrderField.shouldBe(Condition.visible).click();
        emptyListOfDataInTheCreateWorkOrderModal.shouldNot(Condition.exist);
        purchaseOrderField.setValue(id).pressEnter();
        noTemplateHint.shouldBe(Condition.visible, Duration.ofSeconds(120));
        productionField.click();
        emptyListOfDataInTheCreateWorkOrderModal.shouldNot(Condition.exist);
        productionField.setValue(Credentials.USA_PRODUCTION).pressEnter();
        etchingFormatSpan.click();
        etchingFormatField.pressEnter();
        quantityField.setValue(quantity);
        companyField.click();
        companyField.setValue(Credentials.USERS_COMPANY).pressEnter();
        locationSpan.click();
        locationField.click();
        locationField.setValue(Credentials.COMPANY_LOCATION).pressEnter();
        notesField.setValue(notes);
        skidField.click();
        skidField.pressEnter();
        boxField.click();
        boxField.pressEnter();
        bagField.click();
        bagField.pressEnter();
        boxesInSkidField.setValue(Credentials.BOXES_IN_SKID_FOR_CREATING_WORK_ORDER);
        sealsInBoxField.setValue(Credentials.SEALS_IN_BOX_FOR_CREATING_WORK_ORDER);
        sealsInBagField.setValue(Credentials.SEALS_IN_BAG_FOR_CREATING_WORK_ORDER).pressEnter();
        enumerationFieldSpan.click();
        enumerationField.pressEnter();
        okButtonInCreateWorkOrderModal.click();
        return page(this);
    }

    public boolean createdWorkOrderIsDisplayed(String id) {
        return $(byLinkText(id)).isDisplayed();
    }

    public void waitForLoadWorkOrdersPage(String id) {
        $(byLinkText(id)).should(Condition.exist);
    }

    public String getWorkOrderState() {
        return workOrderState.getText();
    }

    public String getPurchaseOrderId() {
        return purchaseOrderId.getText();
    }

    public String getWorkOrderCompany() {
        return workOrderCompany.getText();
    }

    public String getWorkOrderOwner() {
        return workOrderOwner.getText();
    }

    public String getWorkOrderSealType() {
        return workOrderSealType.getText();
    }

    public String getWorkOrderSealColor() {
        return workOrderSealColor.getAttribute("style");
    }

    public String getWorkOrderEtchingFormat() {
        return workOrderEtchingFormat.getText();
    }

    public String getWorkOrderLogo() {
        return workOrderLogo.getText();
    }
}
