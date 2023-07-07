package jsonObjects.workOrder.createWorkOrder;

public class Quantity {
    private int actual;

    public Quantity(int actual) {
        this.actual = actual;
    }

    public Quantity() {
    }

    public int getActual() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }
}
