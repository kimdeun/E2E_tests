package constants;

public class URLs {
    public static String STAGE_URL = "https://staging1.stm.redflag.cc/#/login";
    public static String BASE_API_URI = "https://staging1.stm.redflag.cc";
    public static String PURCHASE_ORDER_LIST_PAGE = "https://staging1.stm.redflag.cc/#/production/purchaseOrder/list";
    public static String PURCHASE_ORDER_PAGE = "https://staging1.stm.redflag.cc/#/production/purchaseOrder/%s";
    public static String WORK_ORDERS_LIST_PAGE = "https://staging1.stm.redflag.cc/#/production/workOrder/list";
    public static String WORK_ORDER_PAGE = "https://staging1.stm.redflag.cc/#/production/workOrder/%s";
    public static String TRANSFER_PAGE = "https://staging1.stm.redflag.cc/#/supply/transfer/%s";
    public static String TRANSFERS_LIST_PAGE = "https://staging1.stm.redflag.cc/#/supply/transfer/list";
    public static String PRODUCTION_COMPANIES_LIST_PAGE = "https://staging1.stm.redflag.cc/#/production/company/list";
    public static String SUPPLY_COMPANIES_LIST_PAGE = "https://staging1.stm.redflag.cc/#/supply/company/list";
    public static String SUPPLY_COMPANY_PAGE = "https://staging1.stm.redflag.cc/#/supply/company/%s/locations";
    public static String PRODUCTION_USERS_LIST_PAGE = "https://staging1.stm.redflag.cc/#/production/user/list";
    public static String SUPPLY_USERS_LIST_PAGE = "https://staging1.stm.redflag.cc/#/supply/user/list";

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
