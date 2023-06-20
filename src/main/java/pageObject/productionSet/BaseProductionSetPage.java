package pageObject.productionSet;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pageObject.BasePage;

import static com.codeborne.selenide.Selenide.page;

public class BaseProductionSetPage {
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[@id='order__BV_toggle_']")
    private SelenideElement ordersButton;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(),'Purchase Orders')]")
    private SelenideElement purchaseOrdersButton;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Companies')]")
    private SelenideElement companiesButtonInTheNavMenu;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Users')]")
    private SelenideElement usersButtonInTheNavMenu;

    public PurchaseOrderListPage openPurchaseOrdersPage() {
        ordersButton.click();
        purchaseOrdersButton.click();
        return page(PurchaseOrderListPage.class);
    }

    public WarehousePage openWarehousePage() {
        return page(WarehousePage.class);
    }

    public CompaniesListPage openCompaniesListPage() {
        companiesButtonInTheNavMenu.click();
        return page(CompaniesListPage.class);
    }

    public UsersListPage openUsersListPage() {
        usersButtonInTheNavMenu.click();
        return page(UsersListPage.class);
    }
}
