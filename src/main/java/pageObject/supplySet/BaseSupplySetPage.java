package pageObject.supplySet;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pageObject.productionSet.CompaniesListPage;

import static com.codeborne.selenide.Selenide.page;

public class BaseSupplySetPage {
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Transfers')]")
    private SelenideElement transfersButtonInTheNavigationMenu;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Users')]")
    private SelenideElement usersButtonInTheNavMenu;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Companies')]")
    private SelenideElement companiesButtonInTheNavMenu;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Find seals')]")
    private SelenideElement findSealsButtonInTheNavMenu;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[@class='nav-link' and contains(text(), 'Warehouse')]")
    private SelenideElement warehouseButtonInTheNavMenu;

    public WarehousePage openWarehousePage() {
        warehouseButtonInTheNavMenu.click();
        return page(WarehousePage.class);
    }

    public TransfersListPage openTransfersListPage() {
        transfersButtonInTheNavigationMenu.click();
        return page(TransfersListPage.class);
    }

    public UsersListPage openUsersListPage() {
        usersButtonInTheNavMenu.click();
        return page(UsersListPage.class);
    }

    public CompaniesListPage openCompaniesListPage() {
        companiesButtonInTheNavMenu.click();
        return page(CompaniesListPage.class);
    }

    public FindSealsPage openFindSealsPage() {
        findSealsButtonInTheNavMenu.click();
        return page(FindSealsPage.class);
    }
}
