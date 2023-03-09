package pageObject.supplySet;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TransfersPage {
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Transfers')]")
    private SelenideElement transfersButtonInTheNavigationMenu;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//ul[@class='nav ui-navigation-navs']/li[3]/a[contains(text(), 'Warehouse')]")
    private SelenideElement warehouseButtonInTheNavigationMenu;
}
