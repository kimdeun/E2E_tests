package pageObject.productionSet;

import pageObject.BasePage;

import static com.codeborne.selenide.Selenide.page;

public class BaseProductionSetPage extends BasePage {

    public PurchaseOrderListPage openPurchaseOrdersPage() {
        return page(PurchaseOrderListPage.class);
    }
}
