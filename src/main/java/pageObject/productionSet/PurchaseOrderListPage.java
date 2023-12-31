package pageObject.productionSet;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import constants.Companies;
import constants.Entities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pageObject.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class PurchaseOrderListPage extends BasePage {
    @FindBy(how = How.XPATH, using = ".//button[contains(text(),'Create')]")
    private SelenideElement createButton;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Enter purchase order ID']")
    private SelenideElement idField;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select company']")
    private SelenideElement companyField;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Code type')]//following-sibling::div[@dir='auto']//input")
    private SelenideElement codeTypeField;
    @FindBy(how = How.XPATH, using = ".//a[contains(text(), 'Custom')]")
    private SelenideElement customCode;
    @FindBy(how = How.XPATH, using = ".//a[contains(text(), 'System')]")
    private SelenideElement systemCode;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Excluded signs')]//following-sibling::div[@dir='auto']//input")
    private SelenideElement excludedSigns;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Enter code']")
    private SelenideElement codeField;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Enter numbers quantity (excluding code)']")
    private SelenideElement numberQuantityField;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Enter sequence start number']")
    private SelenideElement sequenceStartNumberField;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Enter total seals quantity']")
    private SelenideElement totalSealsQuantityField;
    @FindBy(how = How.XPATH, using = ".//input[@type='checkbox']")
    private WebElement setBuyerManuallyCheckbox;
    @FindBy(how = How.XPATH, using = ".//input[contains(@placeholder, 'full name')]")
    private SelenideElement fullNameField;
    @FindBy(how = How.XPATH, using = ".//input[contains(@placeholder, 'phone number')]")
    private SelenideElement phoneNumberField;
    @FindBy(how = How.XPATH, using = ".//input[contains(@placeholder, 'email address')]")
    private SelenideElement emailField;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select a buyer']")
    private SelenideElement buyerField;
    @FindBy(how = How.CSS, using = "ul[role='listbox'] > li:nth-child(1)")
    private SelenideElement firstBuyer;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Ok')]")
    private SelenideElement okButton;
    @FindBy(how = How.XPATH, using = ".//span[text()='Purchase orders']")
    private SelenideElement pageTitle;
    @FindBy(how = How.CSS, using = ".ui-table-row-wrapper:nth-child(1) span[title='Entered']")
    private SelenideElement purchaseOrderState;
    @FindBy(how = How.CSS, using = ".ui-table-row-wrapper:nth-child(1) a[title='TestCompanyForAutoTests']")
    private SelenideElement purchaseOrderCompany;
    @FindBy(how = How.CSS, using = ".ui-table-row-wrapper:nth-child(1) > .ui-table-row > div:nth-child(4) span")
    private SelenideElement purchaseOrderBuyer;
    @FindBy(how = How.CSS, using = ".ui-table-row-wrapper:nth-child(1) a[title='Test User']")
    private SelenideElement purchaseOrderOwner;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Search']")
    private SelenideElement searchInput;
    @FindBy(how = How.XPATH, using = ".//button[text()='Search ']")
    private SelenideElement searchButton;
    @FindBy(how = How.XPATH, using = ".//span[text()='Create a purchase order']")
    private SelenideElement createPurchaseOrderModalTitle;

    public void clickSetBuyerManuallyCheckbox() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        WebElement element = setBuyerManuallyCheckbox;
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public PurchaseOrderListPage createPurchaseOrderWithSpecifiedBuyer(String id, String name, String phoneNumber, String email) {
        createButton.click();
        idField.setValue(id);
        companyField.setValue(Companies.TEST_COMPANY_FOR_AUTO_TESTS.getCompany()).pressEnter();
        clickSetBuyerManuallyCheckbox();
        fullNameField.setValue(name);
        phoneNumberField.setValue(phoneNumber);
        emailField.setValue(email);
        okButton.click();
        return page(this);
    }

    public PurchaseOrderListPage createPurchaseOrderWithSelectedBuyer(String id) {
        pageTitle.should(Condition.exist);
        createButton.click();
        idField.shouldBe(Condition.visible).setValue(id);
        companyField.setValue(Companies.TEST_COMPANY_FOR_AUTO_TESTS.getCompany()).pressEnter();
        buyerField.click();
        firstBuyer.click();
        okButton.click();
        createPurchaseOrderModalTitle.shouldNotBe(Condition.visible, Duration.ofSeconds(10));
        return page(this);
    }

    public PurchaseOrderListPage createPurchaseOrderWithCustomCode(String id, String code, String numbersQuantity, String sequenceStartNumber, String totalSealsQuantity) {
        createButton.click();
        idField.setValue(id);
        companyField.setValue(Companies.TEST_COMPANY_FOR_AUTO_TESTS.getCompany()).pressEnter();
        codeTypeField.click();
        customCode.click();
        codeField.setValue(code);
        numberQuantityField.setValue(numbersQuantity);
        sequenceStartNumberField.setValue(sequenceStartNumber);
        totalSealsQuantityField.setValue(totalSealsQuantity);
        buyerField.click();
        firstBuyer.click();
        okButton.click();
        return page(this);
    }

    public PurchaseOrderPage openPurchaseOrderPage(String id) {
        searchInput.setValue(id);
        searchButton.click();
        $(byLinkText(id)).click();
        return page(PurchaseOrderPage.class);
    }

    public boolean createdPurchaseOrderIdIsDisplayed(String id) {
        return $(byLinkText(id)).isDisplayed();
    }

    public PurchaseOrderListPage waitForLoadPurchaseOrdersPage(String id) {
        $(byLinkText(id)).should(Condition.exist, Duration.ofSeconds(10));
        return page(this);
    }

    public String getPurchaseOrderState() {
        return purchaseOrderState.getText();
    }

    public String getPurchaseOrderCompany() {
        return purchaseOrderCompany.getText();
    }

    public String getPurchaseOrderBuyer() {
        return purchaseOrderBuyer.getText();
    }

    public String getPurchaseOrderOwner() {
        return purchaseOrderOwner.getText();
    }
}
