package pageObject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pageObject.productionSet.BaseProductionSetPage;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage extends BasePage {
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Enter Login']")
    private SelenideElement emailField;

    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Enter Password']")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = (".//div[contains(text(), 'Log in')]"))
    private SelenideElement loginButton;

    public void setEmail(String email) {
        emailField.setValue(email);
    }

    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public LoginPage login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
        return page(this);
    }

    public BaseProductionSetPage openProductionSetPage() {
        userName.click();
        productionAndOrderFulfillment.click();
        return page(BaseProductionSetPage.class);
    }

//    public BaseProductionSetPage openSupplySetPage() {
//        userName.click();
//        warehouseAndInventoryManagement.click();
//        return page(BaseProductionSetPage.class);
//    }
}
