package jsonObjects.user;

public class Contacts {
    boolean isPreferred;
    String type;
    String value;

    public Contacts(boolean isPreferred, String type, String value) {
        this.isPreferred = isPreferred;
        this.type = type;
        this.value = value;
    }

    public Contacts() {
    }

    public boolean isPreferred() {
        return isPreferred;
    }

    public void setPreferred(boolean preferred) {
        isPreferred = preferred;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
