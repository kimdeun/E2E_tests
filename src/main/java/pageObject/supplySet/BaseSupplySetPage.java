package pageObject.supplySet;

import static com.codeborne.selenide.Selenide.page;

public class BaseSupplySetPage {
    public WarehousePage openWarehousePage() {
        return page(WarehousePage.class);
    }

    public TransfersPage openTransfersPage() {
        return page(TransfersPage.class);
    }
}
