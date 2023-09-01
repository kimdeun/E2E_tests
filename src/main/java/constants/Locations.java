package constants;

public enum Locations {
    PAVLOVSK("Павловск"),
    MILWAKEE("Milwakee, WI"),
    TEST_LOCATION("TestCompanyLocation");
    private String location;

    Locations(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
