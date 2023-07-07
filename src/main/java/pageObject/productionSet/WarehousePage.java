package pageObject.productionSet;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
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
import static com.codeborne.selenide.Selenide.sleep;

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
    @FindBy(how = How.CSS, using = "div[title='TestCompanyLocation (2222+22 Сейджпер, Мадгиа Прадеш, Индия)']")
    private SelenideElement testCompanyLocationInTheCreateTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-modal-footer clearfix']/div[contains(text(), 'Create')]")
    private SelenideElement createButtonInTheCreateTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']//i[@class='fa fa-circle']")
    private ElementsCollection checkboxCollectionInTheInventoryTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div[3]/span")
    private ElementsCollection containerNumbersCollectionInTheInventoryTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div[8]/div[1]/div[1]")
    private ElementsCollection containersQuantityCollectionInTheInventoryTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div[10]/span")
    private ElementsCollection containersStartNumberCollectionInTheInventoryTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div[11]/span")
    private ElementsCollection containersEndNumberCollectionInTheInventoryTable;
    @FindBy(how = How.CSS, using = ".no-options")
    private SelenideElement emptyListOfCompaniesInTheCreateTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-form']/div[6]//span[text()='No elements found. Consider changing the search query.']")
    private SelenideElement emptyListOfLocationsInTheTransferModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]//button[contains(text(), 'Skid')]")
    private SelenideElement firstSkidInTheContainersTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[2]//button[contains(text(), 'Box')]")
    private SelenideElement firstBoxInTheSkidInTheContainersTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[3]//button[contains(text(), 'Bag')]")
    private SelenideElement firstBagInTheSkidInTheContainersTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div[1]/div[3]")
    private SelenideElement firstContainerNameInTheTable;

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

    public String getContainersQuantity(int containerIndex) {
        return containersQuantityCollectionInTheInventoryTable.get(containerIndex).getText();
    }

    public String getContainersStartNumber(int containerIndex) {
        return containersStartNumberCollectionInTheInventoryTable.get(containerIndex).getText();
    }

    public String getContainersEndNumber(int containerIndex) {
        return containersEndNumberCollectionInTheInventoryTable.get(containerIndex).getText();
    }

    public WarehousePage openDetailsTable() {
        companySelect.click();
        selectCompanyInput.setValue(Credentials.USERS_COMPANY).pressEnter();
        showInventoryButton.click();
        detailsButton.click();
        return page(this);
    }

    public WarehousePage waitForInventoryTableContent() {
        checkboxCollectionInTheInventoryTable.shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(180));
        return page(WarehousePage.class);
    }

    public WarehousePage unfoldSkid() {
        firstSkidInTheContainersTable.click();
        firstBoxInTheSkidInTheContainersTable.shouldHave(Condition.text(Credentials.BOX));
        return page(WarehousePage.class);
    }

    public WarehousePage unfoldBox() {
        firstBoxInTheSkidInTheContainersTable.click();
        firstBagInTheSkidInTheContainersTable.shouldHave(Condition.text(Credentials.BAG));
        return page(this);
    }

    public WarehousePage createTransfer(int containerIndex) {
        getContainerCheckbox(containerIndex).click();
        createTransferButton.click();
        sleep(1000);
        companyInputInTheCreateTransferModal.click();
        emptyListOfCompaniesInTheCreateTransferModal.shouldNot(Condition.exist);
        companyInputInTheCreateTransferModal.setValue(Credentials.USERS_COMPANY).pressEnter();
        locationSelectInTheCreateTransferModal.click();
        emptyListOfLocationsInTheTransferModal.shouldNotBe(Condition.visible);
        testCompanyLocationInTheCreateTransferModal.click();
        createButtonInTheCreateTransferModal.click();
        return page(this);
    }

    public BaseSupplySetPage openSupplySet() {
        userName.click();
        warehouseAndInventoryManagement.click();
        return page(BaseSupplySetPage.class);
    }

    public WarehousePage searchContainer(String containerNumber) {
        searchInput.setValue(containerNumber);
        searchButton.click();
        firstContainerNameInTheTable.shouldHave(Condition.exactText(containerNumber));
        return page(this);
    }
}
