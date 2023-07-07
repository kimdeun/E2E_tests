package jsonObjects.workOrder.createWorkOrder;

public class Capacity {
    private int id;
    private Quantity quantity;
    private Source source;
    private Target target;

    public Capacity(int id, Quantity quantity, Source source, Target target) {
        this.id = id;
        this.quantity = quantity;
        this.source = source;
        this.target = target;
    }

    public Capacity() {
    }

    public int getId() {
        return id;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Source getSource() {
        return source;
    }

    public Target getTarget() {
        return target;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public void setTarget(Target target) {
        this.target = target;
    }
}
