package jsonObjects.purchaseOrder.addSeals;

public class Quantity {
    private Integer entered;

    public Quantity(Integer entered) {
        this.entered = entered;
    }

    public Quantity() {
    }

    public Integer getEntered() {
        return entered;
    }

    public void setEntered(Integer entered) {
        this.entered = entered;
    }
}
