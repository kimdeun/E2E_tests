package jsonObjects.workOrder.createWorkOrder;

import java.util.List;

public class CreateWorkOrderJsonObject {
    private List<Capacity> capacity;
    private int estimation;
    private EtchingFormat etchingFormat;
    private boolean isTemplateCreationRequired;
    private String notes;
    private Packing packing;
    private Production production;
    private PurchaseOrder purchaseOrder;
    private long quantity;
    private SealEnumerationMode sealEnumerationMode;
    private SealGroup sealGroup;
    private TargetCompanyLocation target;

    public CreateWorkOrderJsonObject(List<Capacity> capacity, int estimation, EtchingFormat etchingFormat,
                                     boolean isTemplateCreationRequired, String notes, Packing packing,
                                     Production production, PurchaseOrder purchaseOrder, long quantity,
                                     SealEnumerationMode sealEnumerationMode, SealGroup sealGroup, TargetCompanyLocation target) {
        this.capacity = capacity;
        this.estimation = estimation;
        this.etchingFormat = etchingFormat;
        this.isTemplateCreationRequired = isTemplateCreationRequired;
        this.notes = notes;
        this.packing = packing;
        this.production = production;
        this.purchaseOrder = purchaseOrder;
        this.quantity = quantity;
        this.sealEnumerationMode = sealEnumerationMode;
        this.sealGroup = sealGroup;
        this.target = target;
    }

    public CreateWorkOrderJsonObject() {
    }

    public List<Capacity> getCapacity() {
        return capacity;
    }

    public int getEstimation() {
        return estimation;
    }

    public EtchingFormat getEtchingFormat() {
        return etchingFormat;
    }

    public boolean isTemplateCreationRequired() {
        return isTemplateCreationRequired;
    }

    public String getNotes() {
        return notes;
    }

    public Packing getPacking() {
        return packing;
    }

    public Production getProduction() {
        return production;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public long getQuantity() {
        return quantity;
    }

    public SealEnumerationMode getSealEnumerationMode() {
        return sealEnumerationMode;
    }

    public SealGroup getSealGroup() {
        return sealGroup;
    }

    public TargetCompanyLocation getTarget() {
        return target;
    }

    public void setCapacity(List<Capacity> capacity) {
        this.capacity = capacity;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public void setEtchingFormat(EtchingFormat etchingFormat) {
        this.etchingFormat = etchingFormat;
    }

    public void setTemplateCreationRequired(boolean templateCreationRequired) {
        isTemplateCreationRequired = templateCreationRequired;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setPacking(Packing packing) {
        this.packing = packing;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public void setSealEnumerationMode(SealEnumerationMode sealEnumerationMode) {
        this.sealEnumerationMode = sealEnumerationMode;
    }

    public void setSealGroup(SealGroup sealGroup) {
        this.sealGroup = sealGroup;
    }

    public void setTarget(TargetCompanyLocation target) {
        this.target = target;
    }
}
