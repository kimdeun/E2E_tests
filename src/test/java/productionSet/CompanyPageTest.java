package productionSet;

import api.company.CreateCompanyRequest;
import api.company.CreateLocationRequest;
import api.company.DeleteCompanyRequest;
import baseTests.BaseTest;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import constants.Entities;
import constants.URLs;
import io.restassured.RestAssured;
import jsonObjects.company.createCompany.Country;
import jsonObjects.company.createCompany.CreateCompanyJsonObject;
import jsonObjects.company.createCompany.ObjectType;
import jsonObjects.company.createLocation.Company;
import jsonObjects.company.createLocation.CreateLocationJsonObject;
import jsonObjects.company.createLocation.IsShippingDestination;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageObject.LoginPage;
import pageObject.productionSet.CompaniesListPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class CompanyPageTest extends BaseTest {
    Faker faker = new Faker();
    String locationName = faker.address().cityName();
    String locationAddress = faker.address().country();
    String areaName = faker.address().fullAddress();
    String fieldOfficeName = faker.address().streetName();
    String companyName = faker.company().name();
    CompaniesListPage companiesListPage = page(CompaniesListPage.class);

    //objects for company creation
    Country country = new Country(4);
    ObjectType objectType = new ObjectType("simpleCompany");
    CreateCompanyJsonObject createCompanyJsonObject = new CreateCompanyJsonObject(country, companyName, objectType);
    Integer companyId;

    @Override
    @BeforeEach
    public void setUp() {
        Configuration.browserSize = Entities.BROWSER_SIZE_1920_1080;
        loginPage = open(URLs.STAGE_URL, LoginPage.class);
        RestAssured.baseURI = URLs.BASE_API_URI;

        //get a token
        token = authRequest.getToken();

        //create a company and get the company id
        CreateCompanyRequest createCompanyRequest = new CreateCompanyRequest();
        companyId = createCompanyRequest.getResponseForCreatingCompanyRequest(token, createCompanyJsonObject)
                .extract()
                .body()
                .path("id");
    }

    @AfterEach
    public void tearDown() {
        //delete company
        DeleteCompanyRequest deleteCompanyRequest = new DeleteCompanyRequest();
        deleteCompanyRequest.getResponseForDeletingCompanyRequest(token, companyId.toString());
    }

    @Test
    public void createLocation() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.PRODUCTION_COMPANIES_LIST_PAGE);
        companiesListPage.searchCompany(companyName)
                .openCompanyPage()
                .openLocationsTab()
                .createLocation(locationName, locationAddress)
                .searchLocation(locationName)
                .checkLocationName(locationName);
    }

    @Test
    public void createArea() {
        //objects for location creation
        Company company = new Company(companyId);;
        IsShippingDestination isShippingDestination = new IsShippingDestination(false);
        CreateLocationJsonObject createLocationJsonObject = new CreateLocationJsonObject(locationAddress, company, isShippingDestination, 54.6, 83.4, locationName);

        //create location
        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.getResponseForCreatingLocationRequest(token, createLocationJsonObject);

        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.PRODUCTION_COMPANIES_LIST_PAGE);
        companiesListPage.searchCompany(companyName)
                .openCompanyPage()
                .openLocationsTab()
                .createArea(areaName, locationName)
                .clickOnTheAreaCounterButton()
                .checkAreaName(areaName);
    }

    @Test
    public void createFieldOffice() {
        //objects for location creation
        Company company = new Company(companyId);;
        IsShippingDestination isShippingDestination = new IsShippingDestination(false);
        CreateLocationJsonObject createLocationJsonObject = new CreateLocationJsonObject(locationAddress, company, isShippingDestination, 54.6, 83.4, locationName);

        //create location
        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.getResponseForCreatingLocationRequest(token, createLocationJsonObject);

        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.PRODUCTION_COMPANIES_LIST_PAGE);
        companiesListPage.searchCompany(companyName)
                .openCompanyPage()
                .openLocationsTab()
                .createFieldOffice(fieldOfficeName, locationName)
                .checkFieldOfficeName(fieldOfficeName);
    }

    @Test
    public void checkLocationAddressInTheTable() {
        //objects for location creation
        Company company = new Company(companyId);;
        IsShippingDestination isShippingDestination = new IsShippingDestination(false);
        CreateLocationJsonObject createLocationJsonObject = new CreateLocationJsonObject(locationAddress, company, isShippingDestination, 54.6, 83.4, locationName);

        //create location
        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.getResponseForCreatingLocationRequest(token, createLocationJsonObject);

        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.PRODUCTION_COMPANIES_LIST_PAGE);
        companiesListPage.searchCompany(companyName)
                .openCompanyPage()
                .openLocationsTab()
                .checkLocationAddress(locationAddress);
    }
}
