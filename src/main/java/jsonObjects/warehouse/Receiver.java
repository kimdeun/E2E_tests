package jsonObjects.warehouse;

public class Receiver {
    String fullName;
    boolean isExternal;
    User user;

    public Receiver(String fullName, boolean isExternal, User user) {
        this.fullName = fullName;
        this.isExternal = isExternal;
        this.user = user;
    }

    public Receiver() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isExternal() {
        return isExternal;
    }

    public void setExternal(boolean external) {
        isExternal = external;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
