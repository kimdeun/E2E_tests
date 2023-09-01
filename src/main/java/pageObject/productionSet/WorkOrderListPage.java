package pageObject.productionSet;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import constants.Entities;
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
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Boxes in') and contains(text(), 'Skid')]//following-sibling::input")
    private SelenideElement boxesInSkidField;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Seals in') and contains(text(), 'Box')]//following-sibling::input")
    private SelenideElement sealsInBoxField;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Seals in') and contains(text(), 'Bag')]//following-sibling::input")
    private SelenideElement sealsInBagField;
    @FindBy(how = How.XPATH, using = ".//span[contains(text(), 'Select enumeration mode')]")
    private SelenideElement enumerationFieldSpan;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select enumeration mode']")
    private SelenideElement enumerationField;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Ok')]")
    private SelenideElement okButtonInCreateWorkOrderModal;
    @FindBy(how = How.CSS, using = "div.ui-table-row-wrapper:nth-child(1) span.ui-btn-label")
    private SelenideElement workOrderState;
    @FindBy(how = How.CSS, using = "div.ui-table-row-wrapper:nth-child(1) a.ui-link[href^='#/production/purchaseOrder/']")
    private SelenideElement purchaseOrderName;
    @FindBy(how = How.CSS, using = "div.ui-table-row-wrapper:nth-child(1) a.ui-link[href^='#/production/company/']")
    private SelenideElement workOrderCompany;
    @FindBy(how = How.CSS, using = "div.ui-table-row-wrapper:nth-child(1) a.ui-link[href^='#/production/user/']")
    private SelenideElement workOrderOwner;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]/div[1]/div[8]//span")
    private SelenideElement workOrderSealType;
    @FindBy(how = How.CSS, using = "div.ui-table-row-wrapper:nth-child(1) span.ui-color-sign")
    private SelenideElement workOrderSealColor;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]/div[1]/div[12]//span")
    private SelenideElement workOrderEtchingFormat;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]/div[1]/div[13]//span")
    private SelenideElement workOrderLogo;
    @FindBy(how = How.CSS, using = ".ui-app-hint.container.ui-error")
    private SelenideElement noTemplateHint;
    @FindBy(how = How.CSS, using = ".no-options")
    private SelenideElement emptyListOfDataInTheCreateWOModal;
    @FindBy(how = How.XPATH, using = ".//span[text()='Work orders']")
    private SelenideElement pageTitle;

    public WorkOrderListPage createWorkOrder(String id, String quantity, String notes) {
        pageTitle.shouldBe(Condition.visible, Duration.ofSeconds(15));
        createWorkOrderButton.click();
        purchaseOrderField.shouldBe(Condition.visible).click();
        emptyListOfDataInTheCreateWOModal.shouldNot(Condition.exist);
        purchaseOrderField.setValue(id).pressEnter();
        noTemplateHint.shouldBe(Condition.visible, Duration.ofSeconds(120));
        productionField.click();
        emptyListOfDataInTheCreateWOModal.shouldNot(Condition.exist);
        productionField.setValue(Entities.USA_PRODUCTION).pressEnter();
        etchingFormatSpan.click();
        etchingFormatField.pressEnter();
        quantityField.setValue(quantity);
        companyField.click();
        companyField.setValue(Entities.USERS_COMPANY).pressEnter();
        locationSpan.click();
        locationField.click();
        locationField.setValue(Entities.COMPANY_LOCATION).pressEnter();
        notesField.setValue(notes);
        skidField.click();
        skidField.pressEnter();
        boxField.click();
        boxField.pressEnter();
        bagField.click();
        bagField.pressEnter();
        boxesInSkidField.setValue(Entities.BOXES_IN_SKID_FOR_CREATING_WORK_ORDER);
        sealsInBoxField.setValue(Entities.SEALS_IN_BOX_FOR_CREATING_WORK_ORDER);
        sealsInBagField.setValue(Entities.SEALS_IN_BAG_FOR_CREATING_WORK_ORDER).pressEnter();
        enumerationFieldSpan.click();
        enumerationField.pressEnter();
        okButtonInCreateWorkOrderModal.click();
        return page(this);
    }

    public boolean createdWorkOrderIsDisplayed(String id) {
        return $(byLinkText(id)).isDisplayed();
    }

    public void waitForLoadWorkOrdersPage(String id) {
        $(byLinkText(id)).should(Condition.exist, Duration.ofSeconds(15));
    }

    public String getWorkOrderState() {
        return workOrderState.getText();
    }

    public String getPurchaseOrderName() {
        return purchaseOrderName.getText();
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
