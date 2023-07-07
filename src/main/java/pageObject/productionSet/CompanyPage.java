package pageObject.productionSet;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class CompanyPage {
    @FindBy(how = How.XPATH, using = ".//a[contains(text(), 'Locations')]")
    private SelenideElement locationsTab;
    @FindBy(how = How.XPATH, using = ".//button[text()='Create']")
    private SelenideElement createButton;
    @FindBy(how = How.XPATH, using = ".//a[text()='Area']")
    private SelenideElement createAreaButton;
    @FindBy(how = How.XPATH, using = ".//a[text()='Location']")
    private SelenideElement createLocationButton;
    @FindBy(how = How.XPATH, using = ".//a[text()='Field Office']")
    private SelenideElement createFieldOfficeButton;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Enter location name']")
    private SelenideElement nameInputInTheCreateLocationModal;
    @FindBy(how = How.XPATH, using = ".//label[contains(text(), 'A destination for Redflag seal shipments')]")
    private SelenideElement shippingDestinationRadioButton;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Address']")
    private SelenideElement addressInputInTheCreateLocationModal;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Create')]")
    private SelenideElement createButtonInTheModal;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Search']")
    private SelenideElement searchInput;
    @FindBy(how = How.XPATH, using = ".//button[text()='Search ']")
    private SelenideElement searchButton;
    @FindBy(how = How.ID, using = "ui-map")
    private SelenideElement googleMap;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div[1]/div[2]/span")
    private SelenideElement locationNameInTheTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div[1]/div[4]/a")
    private SelenideElement locationAddressInTheTable;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Enter name']")
    private SelenideElement nameInputInTheCreateAreaAndFieldOfficeModal;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select location']")
    private SelenideElement locationSelectInTheCreateAreaModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div[1]/div[6]/button")
    private SelenideElement areasCounterButton;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[2]/div[1]/div[2]/span")
    private SelenideElement areaNameInTheTable;
    @FindBy(how = How.XPATH, using = ".//span[contains(text(), 'Select locations')]")
    private SelenideElement selectLocationsSpanInTheCreateFieldOfficeModal;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select locations']")
    private SelenideElement selectLocationsInputInTheCreateFieldOfficeModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div[1]/div[3]/span")
    private SelenideElement fieldOfficeNameInTheTable;
//    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div[1]/div[1]/i")
//    private SelenideElement locationCheckboxInTheTable;
//    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[2]/div[1]/div[1]/i")
//    private SelenideElement areaCheckboxInTheTable;
//    @FindBy(how = How.XPATH, using = ".//button[contains(text(), 'Delete')]")
//    private SelenideElement deleteButton;
//    @FindBy(how = How.XPATH, using = ".//div[text()='No content']")
//    private SelenideElement noContentTable;
//    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div[1]/div[1]/div[7]/button")
//    private SelenideElement editLocationButton;

    public CompanyPage openLocationsTab() {
        locationsTab.click();
        return page(this);
    }

    private void waitForTheGoogleMap() {
        googleMap.shouldBe(visible);
    }

    public CompanyPage createLocation(String locationName, String locationAddress) {
        createButton.click();
        createLocationButton.click();
        nameInputInTheCreateLocationModal.setValue(locationName);
        addressInputInTheCreateLocationModal.setValue(locationAddress);
        waitForTheGoogleMap();
        createButtonInTheModal.click();
        return page(this);
    }

    public CompanyPage searchLocation(String locationName) {
        searchInput.click();
        searchInput.setValue(locationName);
        searchButton.click();
        locationNameInTheTable.shouldHave(exactText(locationName));
        return page(this);
    }

    public void checkLocationName(String locationName) {
        locationNameInTheTable.shouldHave(exactText(locationName));
    }

    public void checkLocationAddress(String locationAddress) {
        locationAddressInTheTable.shouldHave(exactText(locationAddress));
    }

    public CompanyPage createArea(String areaName, String locationName) {
        createButton.click();
        createAreaButton.click();
        nameInputInTheCreateAreaAndFieldOfficeModal.setValue(areaName);
        locationSelectInTheCreateAreaModal.setValue(locationName).pressEnter();
        createButtonInTheModal.click();
        return page(this);
    }

    public CompanyPage clickOnTheAreaCounterButton() {
        areasCounterButton.click();
        areaNameInTheTable.shouldBe(visible, Duration.ofSeconds(15));
        return page(this);
    }

    public void checkAreaName(String areaName) {
        areaNameInTheTable.shouldHave(exactText(areaName));
    }

    public CompanyPage createFieldOffice(String fieldOfficeName, String locationName) {
        createButton.click();
        createFieldOfficeButton.click();
        nameInputInTheCreateAreaAndFieldOfficeModal.setValue(fieldOfficeName);
        selectLocationsSpanInTheCreateFieldOfficeModal.click();
        selectLocationsInputInTheCreateFieldOfficeModal.setValue(locationName).pressEnter();
        createButtonInTheModal.click();
        return page(this);
    }

    public void checkFieldOfficeName(String fieldOfficeName) {
        fieldOfficeNameInTheTable.shouldHave(exactText(fieldOfficeName));
    }
}
