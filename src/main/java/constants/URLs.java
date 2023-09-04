package constants;

public class URLs {
    public static String STAGE_URL = "***";
    public static String BASE_API_URI = "***";
    public static String PURCHASE_ORDER_LIST_PAGE = "***";
    public static String PURCHASE_ORDER_PAGE = "***";
    public static String WORK_ORDERS_LIST_PAGE = "***";
    public static String WORK_ORDER_PAGE = "***";
    public static String TRANSFER_PAGE = "***";
    public static String TRANSFERS_LIST_PAGE = "***";
    public static String PRODUCTION_COMPANIES_LIST_PAGE = "***";
    public static String SUPPLY_COMPANIES_LIST_PAGE = "***";
    public static String SUPPLY_COMPANY_PAGE = "***";
    public static String PRODUCTION_USERS_LIST_PAGE = "***";
    public static String SUPPLY_USERS_LIST_PAGE = "***";

    public static String getPurchaseOrderPageURL(String purchaseOrderId) {
        return String.format(PURCHASE_ORDER_PAGE, purchaseOrderId);
    }

    public static String getWorkOrderPageURL(String workOrderId) {
        return String.format(WORK_ORDER_PAGE, workOrderId);
    }

    public static String getTransferPage(String transferId) {
        return String.format(TRANSFER_PAGE, transferId);
    }
}
