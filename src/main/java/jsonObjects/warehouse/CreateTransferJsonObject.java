package jsonObjects.warehouse;

import java.util.List;

public class CreateTransferJsonObject {
    List<Content> content;
    Long estimationApproximate;
    Long estimationMaximum;
    boolean isOwnershipHandoverRequired;
    Receiver receiver;
    Target target;

    public CreateTransferJsonObject(List<Content> content, Long estimationApproximate, Long estimationMaximum, boolean isOwnershipHandoverRequired, Receiver receiver, Target target) {
        this.content = content;
        this.estimationApproximate = estimationApproximate;
        this.estimationMaximum = estimationMaximum;
        this.isOwnershipHandoverRequired = isOwnershipHandoverRequired;
        this.receiver = receiver;
        this.target = target;
    }

    public CreateTransferJsonObject() {
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public Long getEstimationApproximate() {
        return estimationApproximate;
    }

    public void setEstimationApproximate(Long estimationApproximate) {
        this.estimationApproximate = estimationApproximate;
    }

    public Long getEstimationMaximum() {
        return estimationMaximum;
    }

    public void setEstimationMaximum(Long estimationMaximum) {
        this.estimationMaximum = estimationMaximum;
    }

    public boolean isOwnershipHandoverRequired() {
        return isOwnershipHandoverRequired;
    }

    public void setOwnershipHandoverRequired(boolean ownershipHandoverRequired) {
        isOwnershipHandoverRequired = ownershipHandoverRequired;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }
}
