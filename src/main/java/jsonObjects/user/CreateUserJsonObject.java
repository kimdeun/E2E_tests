package jsonObjects.user;

import java.util.List;

public class CreateUserJsonObject {
    Company company;
    List<Contacts> contacts;
    boolean createAccount;
    String fullName;
    String position;
    List<Roles> roles;

    public CreateUserJsonObject(Company company, List<Contacts> contacts, boolean createAccount, String fullName,
                                String position, List<Roles> roles) {
        this.company = company;
        this.contacts = contacts;
        this.createAccount = createAccount;
        this.fullName = fullName;
        this.position = position;
        this.roles = roles;
    }

    public CreateUserJsonObject() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contacts> contacts) {
        this.contacts = contacts;
    }

    public boolean isCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(boolean createAccount) {
        this.createAccount = createAccount;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
}
