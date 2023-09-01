package pageObject.productionSet;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import constants.Entities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class PurchaseOrderPage {
    @FindBy(how = How.XPATH, using = ".//div[text()='+ Add seals']")
    private SelenideElement addSealsButton;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select type']")
    private SelenideElement typeField;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select color']")
    private SelenideElement colorField;
    @FindBy(how = How.CSS, using = "ul[role='listbox'] > li:nth-child(1)")
    private SelenideElement firstColorInTheList;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Quantity']")
    private SelenideElement quantityField;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Ok')]")
    private SelenideElement okButtonInTheAddSealGroupModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']")
    private SelenideElement contentInTheTable;
    @FindBy(how = How.CSS, using = ".ui-btn-label")
    private SelenideElement stateLabel;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Mark as']")
    private SelenideElement markAsInput;
    @FindBy(how = How.CSS, using = "ul[role='listbox'] > li:nth-child(1) a")
    private SelenideElement newStateOfPurchaseOrder;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Ok')]")
    private SelenideElement okButtonInTheUpdateStateModal;
    @FindBy(how = How.XPATH, using = ".//button[contains(text(), 'Create work order')]")
    private SelenideElement createWorkOrderButton;
    @FindBy(how = How.XPATH, using = "div.ui-header a[href='#order']")
    private SelenideElement ordersButton;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(),'Work Orders')]")
    private SelenideElement workOrdersButton;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-content']//input[@placeholder='Select production']")
    private SelenideElement productionField;
    @FindBy(how = How.XPATH, using = ".//span[contains(text(), 'Select etching format')]")
    private SelenideElement etchingFormatSpan;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select etching format']")
    private SelenideElement etchingFormatField;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Enter quantity']")
    private SelenideElement quantityInCreateWorkOrderModal;
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
    @FindBy(how = How.CSS, using = "a.ui-link[href^='#/production/workOrder/']")
    private SelenideElement workOrderId;
    @FindBy(how = How.CSS, using = "a.ui-link[href^='#/production/production/']")
    private SelenideElement workOrderProduction;
    @FindBy(how = How.CSS, using = "div.ui-table-label-cell span.ui-btn-label")
    private SelenideElement workOrderState;
    @FindBy(how = How.CSS, using = "a.ui-link[href^='#/production/user/']")
    private SelenideElement workOrderOwner;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]/div[1]/div[5]/span")
    private SelenideElement workOrderSealType;
    @FindBy(how = How.CSS, using = "div.col-md-9 > div.ui-table-wrapper span.ui-color-sign")
    private SelenideElement workOrderSealColor;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]/div[1]/div[9]/span")
    private SelenideElement workOrderEtchingFormat;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]/div[1]/div[10]/span")
    private SelenideElement workOrderLogo;
    @FindBy(how = How.CSS, using = ".ui-app-hint.container.ui-error")
    private SelenideElement noTemplateHint;
    @FindBy(how = How.CSS, using = ".no-options")
    private SelenideElement emptyListOfDataInTheCreateWOModal;

    public PurchaseOrderPage addSealGroup(String quantityOfSeals) {
        addSealsButton.click();
        typeField.click();
        emptyListOfDataInTheCreateWOModal.shouldNot(Condition.exist);
        typeField.setValue(Entities.SEAL_TYPE).pressEnter();
        colorField.click();
        emptyListOfDataInTheCreateWOModal.shouldNot(Condition.exist);
        firstColorInTheList.click();
        quantityField.clear();
        quantityField.setValue(quantityOfSeals);
        okButtonInTheAddSealGroupModal.click();
        return page(this);
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
        $(stateLabel).shouldHave(Condition.exactText(Entities.STATE_CONFIRMED));
        return page(this);
    }

    public PurchaseOrderPage waitForProducedState() {
        $(stateLabel).shouldHave(Condition.exactText(Entities.STATE_PRODUCED));
        return page(this);
    }

    public String getPurchaseOrderState() {
        String state = stateLabel.getText();
        return state;
    }

    public WorkOrderListPage openWorkOrdersPage() {
        ordersButton.click();
        workOrdersButton.click();
        return page(WorkOrderListPage.class);
    }

    public PurchaseOrderPage createWorkOrder(String quantity, String notes) {
        createWorkOrderButton.click();
        noTemplateHint.shouldBe(Condition.visible, Duration.ofSeconds(120));
        productionField.click();
        emptyListOfDataInTheCreateWOModal.shouldNot(Condition.exist);
        productionField.setValue(Entities.USA_PRODUCTION).pressEnter();
        etchingFormatSpan.click();
        etchingFormatField.pressEnter();
        quantityInCreateWorkOrderModal.setValue(quantity);
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

    public boolean createdWorkOrderIsDisplayed() {
        return $(workOrderId).isDisplayed();
    }

    public void waitForNewWorkOrder() {
        workOrderId.should(Condition.exist, Duration.ofSeconds(15));
    }

    public String getWorkOrderProduction() {
        return workOrderProduction.getText();
    }

    public String getWorkOrderState() {
        return workOrderState.getText();
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

    public WorkOrderPage openWorkOrderPage() {
        workOrderId.click();
        return page(WorkOrderPage.class);
    }
}
