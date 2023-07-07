package jsonObjects.user;

import java.util.List;

public class Roles {
    String name;
    boolean isEnabled;
    boolean isEnabledForAllCompanyLocations;
    List<String> locations;

    public Roles(String name, boolean isEnabled, boolean isEnabledForAllCompanyLocations, List<String> locations) {
        this.name = name;
        this.isEnabled = isEnabled;
        this.isEnabledForAllCompanyLocations = isEnabledForAllCompanyLocations;
        this.locations = locations;
    }

    public Roles() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isEnabledForAllCompanyLocations() {
        return isEnabledForAllCompanyLocations;
    }

    public void setEnabledForAllCompanyLocations(boolean enabledForAllCompanyLocations) {
        isEnabledForAllCompanyLocations = enabledForAllCompanyLocations;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
}
