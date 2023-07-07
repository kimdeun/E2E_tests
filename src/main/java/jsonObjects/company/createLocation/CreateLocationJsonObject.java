package jsonObjects.company.createLocation;

public class CreateLocationJsonObject {
    String address;
    Company company;
    IsShippingDestination isShippingDestination;
    Double latitude;
    Double longitude;
    String name;

    public CreateLocationJsonObject(String address, Company company, IsShippingDestination isShippingDestination, Double latitude, Double longitude, String name) {
        this.address = address;
        this.company = company;
        this.isShippingDestination = isShippingDestination;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public CreateLocationJsonObject() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public IsShippingDestination getIsShippingDestination() {
        return isShippingDestination;
    }

    public void setIsShippingDestination(IsShippingDestination isShippingDestination) {
        this.isShippingDestination = isShippingDestination;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

