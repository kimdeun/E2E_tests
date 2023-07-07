package jsonObjects.purchaseOrder.createPurchaseOrder;

public class Code {
    private Sequence sequence;
    private Type type;
    private String value;

    public Code(Sequence sequence, Type type, String value) {
        this.sequence = sequence;
        this.type = type;
        this.value = value;
    }

    public Code() {
    }

    public Sequence getSequence() {
        return sequence;
    }

    public Type getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
