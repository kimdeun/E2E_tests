package constants;

public enum UserStates {
    NOT_APPROVED("Not Approved"),
    PENDING_INITIAL_TRAINING("Pending Initial Training"),
    ACTIVE("Active");
    private String userState;

    UserStates(String userState) {
        this.userState = userState;
    }

    public String getUserState() {
        return userState;
    }
}
