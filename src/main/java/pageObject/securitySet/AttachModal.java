package pageObject.securitySet;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import constants.Entities;
import constants.Locations;
import constants.PackingTypes;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class AttachModal {
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Attach')]")
    private SelenideElement attachButtonInNavMenu;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Conveyances')]")
    private SelenideElement conveyancesButtonInNavMenu;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder = 'Location']")
    private SelenideElement locationInput;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder = 'Enter container number']")
    private SelenideElement enterContainerNumberInput;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder = 'Bag or Seal?']")
    private SelenideElement bagOrSealInput;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder = 'Enter seal numbers one by one']")
    private SelenideElement attachSealsInput;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Attach')]")
    private SelenideElement attachButtonInModal;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Close')]")
    private SelenideElement closeButtonInModal;
    @FindBy(how = How.CSS, using = ".no-options")
    private SelenideElement emptyListOfDataInTheCreateWOModal;

    public ConveyancesListPage openConveyancesListPage() {
        conveyancesButtonInNavMenu.click();
        return page(ConveyancesListPage.class);
    }

    public AttachModal attachSeal(String containerNumber, String  sealNumber) {
        attachButtonInNavMenu.click();
        locationInput.setValue(Locations.TEST_LOCATION.getLocation());
        emptyListOfDataInTheCreateWOModal.shouldNot(Condition.exist);
        locationInput.pressEnter();
        enterContainerNumberInput.setValue(containerNumber);
        bagOrSealInput.setValue(PackingTypes.SEAL.toString()).pressEnter();
        attachSealsInput.setValue(sealNumber);
        attachButtonInModal.click();
        closeButtonInModal.click();
        return page(this);
    }

    public AttachModal attachBag(String containerNumber, String bagNumber) {
        attachButtonInNavMenu.click();
        locationInput.setValue(Locations.TEST_LOCATION.getLocation());
        emptyListOfDataInTheCreateWOModal.shouldNot(Condition.exist);
        locationInput.pressEnter();
        enterContainerNumberInput.setValue(containerNumber);
        bagOrSealInput.setValue(PackingTypes.BAG.toString()).pressEnter();
        attachSealsInput.setValue(bagNumber);
        attachButtonInModal.click();
        closeButtonInModal.click();
        return page(this);
    }
}
