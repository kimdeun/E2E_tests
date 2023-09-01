package pageObject.supplySet;

import com.codeborne.selenide.SelenideElement;
import constants.Country;
import constants.Entities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class CompaniesListPage {
    @FindBy(how = How.XPATH, using = ".//button[contains(text(), 'Create')]")
    private SelenideElement createCompanyButton;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Enter company name']")
    private SelenideElement nameFieldInTheModal;
    @FindBy(how = How.XPATH, using = ".//span[contains(text(), 'Select country')]")
    private SelenideElement countrySelectSpanInTheModal;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select country']")
    private SelenideElement countrySelectInputInTheModal;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Create')]")
    private SelenideElement createButtonInTheCreateCompanyModal;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Edit')]")
    private SelenideElement editButtonInTheEditCompanyModal;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Search']")
    private SelenideElement searchInput;
    @FindBy(how = How.XPATH, using = ".//button[text()='Search ']")
    private SelenideElement searchButton;
    @FindBy(how = How.CSS, using = "a[href^='#/supply/company/']")
    private SelenideElement companyName;
    @FindBy(how = How.CSS, using = "div.ui-table-country-cell > span")
    private SelenideElement companiesCountry;
    @FindBy(how = How.XPATH, using = ".//span[text()='List is empty.']")
    private SelenideElement emptyListOfCountriesInTheCreateCompanyModal;
    @FindBy(how = How.XPATH, using = ".//button[text()='Actions']")
    private SelenideElement actionsButton;
    @FindBy(how = How.XPATH, using = ".//a[text()='Edit']")
    private SelenideElement editButtonInTheActionsDropdown;
    @FindBy(how = How.XPATH, using = ".//a[text()='Delete']")
    private SelenideElement deleteButtonInTheActionsDropdown;
    @FindBy(how = How.XPATH, using = ".//span[contains(text(), 'Albania')]")
    private SelenideElement albaniaInTheCountriesSelect;
    @FindBy(how = How.XPATH, using = ".//span[text()='Afghanistan' and @class='multiselect__single']")
    private SelenideElement afghanistanCountryInTheEditCompanyModal;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Ok')]")
    private SelenideElement okButtonInTheDeleteModal;
    @FindBy(how = How.XPATH, using = ".//div[text()='No content']")
    private SelenideElement noContentTable;

    private void waitListOfCountries() {
        emptyListOfCountriesInTheCreateCompanyModal.shouldNotBe(visible);
    }

    public CompaniesListPage createCompany(String companyName) {
        createCompanyButton.click();
        nameFieldInTheModal.setValue(companyName);
        countrySelectSpanInTheModal.click();
        countrySelectInputInTheModal.click();
        waitListOfCountries();
        countrySelectInputInTheModal.pressEnter();
        createButtonInTheCreateCompanyModal.click();
        return page(this);
    }

    public CompaniesListPage searchCompany(String companyName) {
        searchInput.click();
        searchInput.setValue(companyName);
        searchButton.click();
        this.companyName.shouldHave(exactText(companyName));
        return page(this);
    }

    public void checkCompanyName(String companyName) {
        this.companyName.shouldHave(exactText(companyName));
    }

    public void checkCompaniesCountryIsAfghanistan() {
        companiesCountry.shouldHave(exactText(Country.AFGHANISTAN.getCountry()));
    }

    public void checkCompaniesCountryIsAlbania() {
        companiesCountry.shouldHave(exactText(Country.ALBANIA.getCountry()));
    }

    public CompaniesListPage editCompanyName(String newCompanyName) {
        actionsButton.click();
        editButtonInTheActionsDropdown.click();
        nameFieldInTheModal.clear();
        nameFieldInTheModal.setValue(newCompanyName);
        editButtonInTheEditCompanyModal.click();
        return page(this);
    }

    public CompaniesListPage editCompaniesCountryToAlbania() {
        actionsButton.click();
        editButtonInTheActionsDropdown.click();
        afghanistanCountryInTheEditCompanyModal.click();
        waitListOfCountries();
        albaniaInTheCountriesSelect.click();
        editButtonInTheEditCompanyModal.click();
        return page(this);
    }

    public CompaniesListPage deleteCompany() {
        actionsButton.click();
        deleteButtonInTheActionsDropdown.click();
        okButtonInTheDeleteModal.click();
        return page(this);
    }

    public void checkNoContentTableAfterDeletingCompany() {
        noContentTable.shouldBe(visible);
    }

    public CompanyPage openCompanyPage() {
        companyName.click();
        return page(CompanyPage.class);
    }
}
