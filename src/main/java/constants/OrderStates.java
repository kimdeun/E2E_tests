package constants;

public enum OrderStates {
    ENTERED("ENTERED"),
    CONFIRMED("CONFIRMED");
    private String orderState;

    OrderStates(String orderState) {
        this.orderState = orderState;
    }

    public String getOrderState() {
        return orderState;
    }
}
