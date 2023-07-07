package jsonObjects.purchaseOrder.createPurchaseOrder;

public class Buyer {
    private String email;
    private String fullName;
    private Integer id;
    private Integer phone;

    public Buyer(String email, String fullName, Integer id, Integer phone) {
        this.email = email;
        this.fullName = fullName;
        this.id = id;
        this.phone = phone;
    }

    public Buyer() {
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
}
