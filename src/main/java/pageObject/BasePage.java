package pageObject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BasePage {
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//span[text()='Test User']")
    protected SelenideElement userName;

    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Production & Order Fulfillment')]")
    protected SelenideElement productionAndOrderFulfillment;

    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Warehouse & Inventory Management')]")
    protected SelenideElement warehouseAndInventoryManagement;

    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Security Transit Management')]")
    protected SelenideElement securityTransitManagement;
}
