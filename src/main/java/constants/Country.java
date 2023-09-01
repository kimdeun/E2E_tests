package constants;

public enum Country {
    AFGHANISTAN("Afghanistan"),
    ALBANIA("Albania");
    private String country;

    Country(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
