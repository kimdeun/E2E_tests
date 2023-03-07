package pageObject.productionSet;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import constants.Credentials;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pageObject.BasePage;
import pageObject.supplySet.BaseSupplySetPage;

import java.time.Duration;
import java.util.Random;

import static com.codeborne.selenide.Selenide.page;

public class WarehousePage extends BasePage {
    @FindBy(how = How.XPATH, using = ".//div[@class='multiselect__tags']")
    private SelenideElement companySelect;
    @FindBy(how = How.XPATH, using = ".//div[@class='multiselect__tags']/input")
    private SelenideElement selectCompanyInput;
    @FindBy(how = How.XPATH, using = ".//button[contains(text(), 'Show Inventory')]")
    private SelenideElement showInventoryButton;
    @FindBy(how = How.XPATH, using = ".//button[contains(text(), 'Details')]")
    private SelenideElement detailsButton;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Search']")
    private SelenideElement searchInput;
    @FindBy(how = How.XPATH, using = ".//button[text()='Search ']")
    private SelenideElement searchButton;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div/i")
    private SelenideElement containerCheckBox;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Create transfer')]")
    private SelenideElement createTransferButton;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Company']")
    private SelenideElement companyInputInTheCreateTransferModal;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Location']/parent::div")
    private SelenideElement locationSelectInTheCreateTransferModal;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Location']")
    private SelenideElement locationInputInTheCreateTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-footer clearfix']/div[contains(text(), 'Create')]")
    private SelenideElement createButtonInTheCreateTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']//i[@class='fa fa-circle']")
    private ElementsCollection checkboxCollectionInTheInventoryTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div[3]/span")
    private ElementsCollection containerNumbersCollectionInTheInventoryTable;

    public int getContainerIndexInTheList() {
        Random random = new Random();
        return random.nextInt(checkboxCollectionInTheInventoryTable.size());
    }

    public SelenideElement getContainerCheckbox(int containerIndex) {
        return checkboxCollectionInTheInventoryTable.get(containerIndex);
    }

    public String getContainerNumber(int containerIndex) {
        return containerNumbersCollectionInTheInventoryTable.get(containerIndex).getText();
    }

    public WarehousePage openInventoryPage() {
        companySelect.click();
        selectCompanyInput.setValue(Credentials.USERS_COMPANY).pressEnter();
        showInventoryButton.click();
        detailsButton.click();
        return page(this);
    }

    public void waitForInventoryTableContent() {
        checkboxCollectionInTheInventoryTable.shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(180));
    }

    public WarehousePage createTransfer(int containerIndex) {
        getContainerCheckbox(containerIndex).click();
        createTransferButton.click();
        companyInputInTheCreateTransferModal.setValue(Credentials.USERS_COMPANY).pressEnter();
        locationSelectInTheCreateTransferModal.click();
        locationInputInTheCreateTransferModal.setValue(Credentials.COMPANY_LOCATION).pressEnter();
        createButtonInTheCreateTransferModal.click();
        return page(this);
    }

    public BaseSupplySetPage openSupplySet() {
        userName.click();
        warehouseAndInventoryManagement.click();
        return page(BaseSupplySetPage.class);
    }
}
