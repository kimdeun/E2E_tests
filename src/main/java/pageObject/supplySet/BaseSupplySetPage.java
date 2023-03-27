package pageObject.supplySet;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class BaseSupplySetPage {
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-header']//a[contains(text(), 'Transfers')]")
    private SelenideElement transfersButtonInTheNavigationMenu;

    public WarehousePage openWarehousePage() {
        return page(WarehousePage.class);
    }

    public TransfersListPage openTransfersListPage() {
        transfersButtonInTheNavigationMenu.click();
        return page(TransfersListPage.class);
    }
}
