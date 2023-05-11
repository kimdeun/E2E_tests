package pageObject.productionSet;

import pageObject.BasePage;

import static com.codeborne.selenide.Selenide.page;

public class BaseProductionSetPage {

    public PurchaseOrderListPage openPurchaseOrdersPage() {
        return page(PurchaseOrderListPage.class);
    }

    public WarehousePage openWarehousePage() {
        return page(WarehousePage.class);
    }

    public CompaniesListPage openCompaniesListPage() {
        return page(CompaniesListPage.class);
    }
}
