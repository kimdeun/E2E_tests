package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pageObject.productionSet.BaseProductionSetPage;
import pageObject.securitySet.BaseSecuritySetPage;
import pageObject.supplySet.BaseSupplySetPage;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class LoginPage extends BasePage {
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Enter Login']")
    private SelenideElement emailField;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Enter Password']")
    private SelenideElement passwordField;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Log in')]")
    private SelenideElement loginButton;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//header[text()='Applications']")
    private SelenideElement popupHeader;

    private void setEmail(String email) {
        emailField.setValue(email);
    }

    private void setPassword(String password) {
        passwordField.setValue(password);
    }

    private void clickLoginButton() {
        loginButton.click();
    }

    public LoginPage login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
        return page(this);
    }

    public BaseProductionSetPage openProductionSetPage() {
        sleep(1500);
        userName.shouldBe(Condition.visible).click();
        popupHeader.shouldHave(Condition.exactText("Applications"));
        productionAndOrderFulfillment.shouldBe(Condition.visible);
        productionAndOrderFulfillment.click();
        return page(BaseProductionSetPage.class);
    }

    public BaseSupplySetPage openSupplySetPage() {
        sleep(1500);
        userName.shouldBe(Condition.visible).click();
        popupHeader.shouldHave(Condition.exactText("Applications"));
        warehouseAndInventoryManagement.shouldBe(Condition.visible);
        warehouseAndInventoryManagement.click();
        return page(BaseSupplySetPage.class);
    }

    public BaseSecuritySetPage openSecuritySetPage() {
        sleep(1500);
        userName.shouldBe(Condition.visible).click();
        popupHeader.shouldHave(Condition.exactText("Applications"));
        securityTransitManagement.shouldBe(Condition.visible);
        securityTransitManagement.click();
        return page(BaseSecuritySetPage.class);
    }

    public void waitForLoadingPageAfterLogin() {
        userName.shouldBe(Condition.visible);
    }
}
