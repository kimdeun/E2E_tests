package pageObject.supplySet;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pageObject.productionSet.CompaniesListPage;
import pageObject.productionSet.UsersListPage;

import static com.codeborne.selenide.Selenide.page;

public class BaseSupplySetPage {
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Transfers')]")
    private SelenideElement transfersButtonInTheNavigationMenu;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Users')]")
    private SelenideElement usersButtonInTheNavMenu;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Companies')]")
    private SelenideElement companiesButtonInTheNavMenu;

    public WarehousePage openWarehousePage() {
        return page(WarehousePage.class);
    }

    public TransfersListPage openTransfersListPage() {
        transfersButtonInTheNavigationMenu.click();
        return page(TransfersListPage.class);
    }

    public pageObject.productionSet.UsersListPage openUsersListPage() {
        usersButtonInTheNavMenu.click();
        return page(UsersListPage.class);
    }

    public CompaniesListPage openCompaniesListPage() {
        companiesButtonInTheNavMenu.click();
        return page(CompaniesListPage.class);
    }
}
