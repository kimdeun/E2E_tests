package jsonObjects.warehouse;

public class Target {
    Company company;
    Location location;

    public Target(Company company, Location location) {
        this.company = company;
        this.location = location;
    }

    public Target() {
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
