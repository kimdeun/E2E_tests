package jsonObjects.company.createCompany;

public class CreateCompanyJsonObject {
    Country country;
    String name;
    ObjectType objectType;

    public CreateCompanyJsonObject(Country country, String name, ObjectType objectType) {
        this.country = country;
        this.name = name;
        this.objectType = objectType;
    }

    public CreateCompanyJsonObject() {
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
    }
}
