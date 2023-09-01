package constants;

public enum UserRoles {
    OPERATOR("Operator"),
    SUPERVISOR("Supervisor"),
    OFFICER("Officer"),
    ADMIN("Admin");

    private String role;

    UserRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
