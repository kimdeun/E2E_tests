package productionSet;

import api.user.CreateUserRequest;
import api.user.DeleteUserRequest;
import baseTests.BaseTest;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import constants.Credentials;
import constants.URLs;
import io.restassured.RestAssured;
import jsonObjects.user.Company;
import jsonObjects.user.Contacts;
import jsonObjects.user.CreateUserJsonObject;
import jsonObjects.user.Roles;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageObject.LoginPage;
import pageObject.productionSet.UsersListPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static constants.Credentials.*;

public class UserPageTest extends BaseTest {
    Faker faker = new Faker();
    String userName = faker.name().fullName();
    String position = faker.company().profession();
    String email = faker.internet().emailAddress();
    UsersListPage usersListPage = page(UsersListPage.class);

    //объекты для создания пользователя
    Company company = new Company(TEST_COMPANY_ID);
    Contacts contact = new Contacts(true, "email", email);
    List<Contacts> contacts = new ArrayList<>(List.of(contact));
    List<String> locations;
    Roles role = new Roles("operator", true, true, locations);
    List<Roles> roles = new ArrayList<>(List.of(role));
    CreateUserJsonObject createUserJsonObject = new CreateUserJsonObject(company, contacts, true, userName, position, roles);
    Integer userId;

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

        //cоздаем пользователя и вытаскиваем id
        CreateUserRequest createUserRequest = new CreateUserRequest();
        userId = createUserRequest.getResponseForCreatingUserRequest(token, createUserJsonObject)
                .extract()
                .body()
                .path("id");
    }

    @Override
    @AfterEach
    public void tearDown() {
        //удаляем пользователя
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest();
        deleteUserRequest.getResponseForDeletingUserRequest(token, userId.toString());
        Selenide.closeWindow();
    }

    @Test
    public void checkNotApprovedUserState() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.PRODUCTION_USERS_LIST_PAGE);
        usersListPage.searchUser(userName)
                .openUserPage()
                .checkUserState(NOT_APPROVED_USERS_STATE);
    }

    @Test
    public void checkPendingInitialTrainingUserState() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.PRODUCTION_USERS_LIST_PAGE);
        usersListPage.searchUser(userName)
                .changeUserState()
                .openUserPage()
                .checkUserState(PENDING_INITIAL_TRAINING_USERS_STATE);
    }

    @Test
    public void checkActiveUserState() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.PRODUCTION_USERS_LIST_PAGE);
        usersListPage.searchUser(userName)
                .changeUserState()
                .changeUserState()
                .openUserPage()
                .checkUserState(ACTIVE_USERS_STATE);
    }

    @Test
    public void changeUserStateToPendingTraining() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.PRODUCTION_USERS_LIST_PAGE);
        usersListPage.searchUser(userName)
                .openUserPage()
                .changeUserState()
                .checkUserState(PENDING_INITIAL_TRAINING_USERS_STATE);
    }

    @Test
    public void changeUserStateToActive() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .waitForLoadingPageAfterLogin();
        open(URLs.PRODUCTION_USERS_LIST_PAGE);
        usersListPage.searchUser(userName)
                .openUserPage()
                .changeUserState()
                .changeUserState()
                .checkUserState(ACTIVE_USERS_STATE);
    }
}
