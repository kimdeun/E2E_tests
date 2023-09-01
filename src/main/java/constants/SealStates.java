package constants;

public enum SealStates {
    //skid, box, bag, seal states
    IN_PRODUCTION("IN PRODUCTION"),
    PRODUCED("PRODUCED"),
    IN_STOCK("In Stock");

    private String sealState;

    SealStates(String sealState) {
        this.sealState = sealState;
    }

    public String getSealState() {
        return sealState;
    }
}
