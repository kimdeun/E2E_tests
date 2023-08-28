package pageObject.supplySet;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import constants.Entities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pageObject.BasePage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class WarehousePage extends BasePage {
    @FindBy(how = How.XPATH, using = ".//a[contains(text(), 'TestCompanyLocation')]/parent::div/parent::div/button")
    private SelenideElement testCompanyDetailsButton;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Search']")
    private SelenideElement searchInput;
    @FindBy(how = How.XPATH, using = ".//button[text()='Search ']")
    private SelenideElement searchButton;
    @FindBy(how = How.CSS, using = ".ui-table-row-wrapper div.ui-table-checkbox-cell .fa-circle")
    private SelenideElement containerCheckBox;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Create Transfer')]")
    private SelenideElement createTransferButton;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Company']")
    private SelenideElement companyInputInTheCreateTransferModal;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Location']")
    private SelenideElement locationInputInTheCreateTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-footer clearfix']/div[contains(text(), 'Create')]")
    private SelenideElement createButtonInTheCreateTransferModal;
    @FindBy(how = How.CSS, using = ".ui-table-row-wrapper:nth-child(1) .ui-link-incorrect-url")
    private SelenideElement firstContainerNameInTheTable;
    @FindBy(how = How.CSS, using = ".no-options")
    private SelenideElement emptyListOfCompaniesAndLocationsInTheCreateTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']//i[@class='fa fa-circle']")
    private ElementsCollection checkboxCollectionInTheInventoryTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Transfers')]")
    private SelenideElement transfersButtonInTheNavigationMenu;

    public WarehousePage openDetailsTable() {
        testCompanyDetailsButton.click();
        waitForInventoryTableContent();
        return page(this);
    }

    public pageObject.productionSet.WarehousePage waitForInventoryTableContent() {
        checkboxCollectionInTheInventoryTable.shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(180));
        return page(pageObject.productionSet.WarehousePage.class);
    }

    public WarehousePage searchContainer(String containerNumber) {
        searchInput.setValue(containerNumber);
        searchButton.click();
        firstContainerNameInTheTable.shouldHave(Condition.exactText(containerNumber));
        return page(this);
    }

    public WarehousePage createTransfer() {
        containerCheckBox.click();
        createTransferButton.click();
        sleep(1000);
        companyInputInTheCreateTransferModal.click();
        emptyListOfCompaniesAndLocationsInTheCreateTransferModal.shouldNot(Condition.exist);
        companyInputInTheCreateTransferModal.setValue(Entities.ALPACA_COMPANY).pressEnter();
        locationInputInTheCreateTransferModal.click();
        emptyListOfCompaniesAndLocationsInTheCreateTransferModal.shouldNot(Condition.exist);;
        locationInputInTheCreateTransferModal.setValue(Entities.ALPACA_PAVLOVSK_LOCATION).pressEnter();
        createButtonInTheCreateTransferModal.click();
        return page(this);
    }

    public TransfersListPage openTransfersListPage() {
        transfersButtonInTheNavigationMenu.click();
        return page(TransfersListPage.class);
    }
}
