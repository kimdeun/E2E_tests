package jsonObjects.workOrder.createWorkOrder;

public class TargetCompanyLocation {
    Company company;
    Location location;

    public TargetCompanyLocation(Company company, Location location) {
        this.company = company;
        this.location = location;
    }

    public TargetCompanyLocation() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
