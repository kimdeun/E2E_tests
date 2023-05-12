import constants.Credentials;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

public class CompanyPageTest extends BaseTest {
    Faker faker = new Faker();
    String locationName = faker.address().cityName();
    String locationAddress = faker.address().country();
    String areaName = faker.address().fullAddress();
    String fieldOfficeName = faker.address().streetName();

    @Test
    public void createLocation() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openCompaniesListPage()
                .searchCompany(Credentials.USERS_COMPANY)
                .openCompanyPage()
                .openLocationsTab()
                .createLocation(locationName, locationAddress)
                .searchLocation(locationName)
                .checkLocationName(locationName);
    }

    @Test
    public void createArea() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openCompaniesListPage()
                .searchCompany(Credentials.USERS_COMPANY)
                .openCompanyPage()
                .openLocationsTab()
                .createLocation(locationName, locationAddress)
                .createArea(areaName, locationName)
                .searchLocation(locationName)
                .clickOnTheAreaCounterButton()
                .checkAreaName(areaName);
    }

    @Test
    public void createFieldOffice() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openCompaniesListPage()
                .searchCompany(Credentials.USERS_COMPANY)
                .openCompanyPage()
                .openLocationsTab()
                .createLocation(locationName, locationAddress)
                .createFieldOffice(fieldOfficeName, locationName)
                .searchLocation(locationName)
                .checkFieldOfficeName(fieldOfficeName);
    }

    @Test
    public void checkLocationAddressInTheTable() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openCompaniesListPage()
                .searchCompany(Credentials.USERS_COMPANY)
                .openCompanyPage()
                .openLocationsTab()
                .createLocation(locationName, locationAddress)
                .searchLocation(locationName)
                .checkLocationAddress(locationAddress);
    }
}
