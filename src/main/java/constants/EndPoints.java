package constants;

public class EndPoints {
    public static final String LOGIN_END_POINT = "/rest/login";
    public static final String CREATE_PURCHASE_ORDER_END_POINT = "/rest/production/purchaseOrder";
    public static final String ADD_SEALS_END_POINT = "/rest/production/purchaseOrder/%s/sealGroup";
    public static final String CREATE_WORK_ORDER_END_POINT = "/rest/production/workOrder";
    public static final String DELETE_PURCHASE_ORDER = "/rest/production/purchaseOrder?ids=%s";
    public static final String GET_ALL_PURCHASE_ORDERS = "/rest/production/purchaseOrder";
    public static final String DELETE_WORK_ORDER = "/rest/production/workOrder?ids=%s";
    public static final String GET_ALL_WORK_ORDERS = "/rest/production/workOrder";
    public static final String CHANGE_WO_STATE = "/rest/production/workOrder/%s/status";
    public static final String GET_SKIDS_IN_WORK_ORDER_CONTAINER_TABLE = "/rest/production/workOrder/%s/packing/table?page=1&size=10&totalPages=&search=";
    public static final String GET_BOXES_IN_WORK_ORDER_CONTAINER_TABLE = "/rest/production/workOrder/%s/packing/container/%s";
    public static final String CREATE_TRANSFER_IN_PRODUCTION_SET = "/rest/supply/transfer";
    public static final String DELETE_TRANSFER = "/rest/supply/transfer?ids=%s";
    public static final String GET_ALL_TRANSFERS = "/rest/production/transfer";
    public static final String CREATE_COMPANY = "/rest/production/company";
    public static final String DELETE_COMPANY = "/rest/production/company/%s";
    public static final String CREATE_LOCATION = "/rest/production/location";
    public static final String CREATE_USER = "/rest/production/user";
    public static final String DELETE_USER = "/rest/production/user?ids=%s";
    public static final String GET_ALL_USERS = "/rest/production/user";
    public static final String CHANGE_TRANSFER_STATUS = "/rest/production/transfer/%s/status";

    public static String addSealsEndPoint(String purchaseOrderId) {
        return String.format(ADD_SEALS_END_POINT, purchaseOrderId);
    }

    public static String deletePurchaseOrderEndPoint(String purchaseOrderId) {
        return String.format(DELETE_PURCHASE_ORDER, purchaseOrderId);
    }

    public static String deleteWorkOrderEndPoint(String workOrderId) {
        return String.format(DELETE_WORK_ORDER, workOrderId);
    }

    public static String changeWorkOrderStateEndPoint(String workOrderId) {
        return String.format(CHANGE_WO_STATE, workOrderId);
    }

    public static String getSkidsInWorkOrderContainerTableEndPoint(String workOrderId) {
        return String.format(GET_SKIDS_IN_WORK_ORDER_CONTAINER_TABLE, workOrderId);
    }

    public static String getBoxesInWorkOrderContainerTableEndPoint(String workOrderId, String skidId) {
        return String.format(GET_BOXES_IN_WORK_ORDER_CONTAINER_TABLE, workOrderId, skidId);
    }

    public static String getDeleteTransferEndPoint(String transferId) {
        return String.format(DELETE_TRANSFER, transferId);
    }

    public static String getDeleteCompanyEndPoint(String companyId) {
        return String.format(DELETE_COMPANY, companyId);
    }

    public static String getDeleteUserEndPoint(String userId) {
        return String.format(DELETE_USER, userId);
    }

    public static String getChangeTransferStatusEndPoint(String transferId) {
        return String.format(CHANGE_TRANSFER_STATUS, transferId);
    }
}
