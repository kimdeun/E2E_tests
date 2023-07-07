package jsonObjects.purchaseOrder.addSeals;

public class AddSealsJsonObject {
    private Quantity quantity;
    private SealColor sealColor;
    private SealType sealType;

    public AddSealsJsonObject(Quantity quantity, SealColor sealColor, SealType sealType) {
        this.quantity = quantity;
        this.sealColor = sealColor;
        this.sealType = sealType;
    }

    public AddSealsJsonObject() {
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public SealColor getSealColor() {
        return sealColor;
    }

    public SealType getSealType() {
        return sealType;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public void setSealColor(SealColor sealColor) {
        this.sealColor = sealColor;
    }

    public void setSealType(SealType sealType) {
        this.sealType = sealType;
    }
}
