package pageObject.productionSet;

import pageObject.BasePage;

import static com.codeborne.selenide.Selenide.page;

public class BaseProductionSetPage extends BasePage {

    public PurchaseOrdersPage openPurchaseOrdersPage() {
        return page(PurchaseOrdersPage.class);
    }
}
