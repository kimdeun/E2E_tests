package productionSet;

import baseTests.BaseTest;
import constants.Credentials;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

public class CompaniesListTest extends BaseTest {
    String companyName = RandomStringUtils.randomAlphanumeric(7);
    String newCompanyName = RandomStringUtils.randomAlphanumeric(7);

    @Test
    public void createCompany() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openCompaniesListPage()
                .createCompany(companyName)
                .searchCompany(companyName)
                .checkCompanyName(companyName);
    }

    @Test
    public void checkCompaniesCountry() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openCompaniesListPage()
                .createCompany(companyName)
                .searchCompany(companyName)
                .checkCompanysCountryIsAfghanistan();
    }

    @Test
    public void editCompanyName() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openCompaniesListPage()
                .createCompany(companyName)
                .searchCompany(companyName)
                .editCompanyName(newCompanyName)
                .searchCompany(newCompanyName)
                .checkCompanyName(newCompanyName);
    }

    @Test
    public void editCompanysCountry() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openCompaniesListPage()
                .createCompany(companyName)
                .searchCompany(companyName)
                .editCompaniesCountryToAlbania()
                .searchCompany(companyName)
                .checkCompanysCountryIsAlbania();
    }

    @Test
    public void deleteCompany() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openCompaniesListPage()
                .createCompany(companyName)
                .searchCompany(companyName)
                .deleteCompany()
                .checkNoContentTableAfterDeletingCompany();
    }
}
