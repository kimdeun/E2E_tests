package supplySet;

import api.company.CreateCompanyRequest;
import api.company.DeleteCompanyRequest;
import baseTests.BaseTest;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import constants.Credentials;
import constants.URLs;
import io.restassured.RestAssured;
import jsonObjects.company.createCompany.Country;
import jsonObjects.company.createCompany.CreateCompanyJsonObject;
import jsonObjects.company.createCompany.ObjectType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageObject.LoginPage;
import pageObject.supplySet.CompaniesListPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class CompaniesListTest extends BaseTest {
    String companyName = faker.company().name();
    String newCompanyName = faker.company().name();
    pageObject.supplySet.CompaniesListPage companiesListPage = page(CompaniesListPage.class);

    //объекты для создания компании
    Country country = new Country(4);
    ObjectType objectType = new ObjectType("simpleCompany");
    CreateCompanyJsonObject createCompanyJsonObject = new CreateCompanyJsonObject(country, companyName, objectType);
    Integer companyIds;

    @Override
    @BeforeEach
    public void setUp() {
        Configuration.browserSize = Credentials.BROWSER_SIZE_1920_1080;
        loginPage = open(URLs.STAGE_URL, LoginPage.class);
        RestAssured.baseURI = URLs.BASE_API_URI;

        //вытаскиваем токен
        token = authRequest.getResponseForUserAuthorization()
                .extract()
                .body()
                .path("content.token");

        //cоздаем компанию и вытаскиваем id компании
        CreateCompanyRequest createCompanyRequest = new CreateCompanyRequest();
        companyIds = createCompanyRequest.getResponseForCreatingCompanyRequest(token, createCompanyJsonObject)
                .extract()
                .body()
                .path("id");
    }

    @Override
    @AfterEach
    public void tearDown() {
        //удаляем компанию
        DeleteCompanyRequest deleteCompanyRequest = new DeleteCompanyRequest();
        deleteCompanyRequest.getResponseForDeletingCompanyRequest(token, companyIds.toString());
        Selenide.closeWindow();
    }

    @Test
    public void createCompany() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage();
        open(URLs.SUPPLY_COMPANIES_LIST_PAGE);
        companiesListPage.searchCompany(companyName)
                .checkCompanyName(companyName);
    }

    @Test
    public void checkCompaniesCountry() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage();
        open(URLs.SUPPLY_COMPANIES_LIST_PAGE);
        companiesListPage.searchCompany(companyName)
                .checkCompaniesCountryIsAfghanistan();
    }

    @Test
    public void editCompanyName() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage();
        open(URLs.SUPPLY_COMPANIES_LIST_PAGE);
        companiesListPage.searchCompany(companyName)
                .editCompanyName(newCompanyName)
                .searchCompany(newCompanyName)
                .checkCompanyName(newCompanyName);
    }

    @Test
    public void editCompaniesCountry() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage();
        open(URLs.SUPPLY_COMPANIES_LIST_PAGE);
        companiesListPage.searchCompany(companyName)
                .editCompaniesCountryToAlbania()
                .searchCompany(companyName)
                .checkCompaniesCountryIsAlbania();
    }

    @Test
    public void deleteCompany() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage();
        open(URLs.SUPPLY_COMPANIES_LIST_PAGE);
        companiesListPage.searchCompany(companyName)
                .deleteCompany()
                .checkNoContentTableAfterDeletingCompany();
    }
}
