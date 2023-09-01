package constants;

public enum TransferStates {
    TRANSIT("TRANSIT"),
    RECEIVED("Received"),
    RECEIVED_WITH_A_PROBLEM("Received with a problem"),
    PROBLEM("PROBLEM"),
    LOST("LOST");
    private String transferState;

    TransferStates(String transferState) {
        this.transferState = transferState;
    }

    public String getState() {
        return transferState;
    }
}
