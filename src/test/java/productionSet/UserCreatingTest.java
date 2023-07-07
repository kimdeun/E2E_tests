package productionSet;

import api.user.DeleteUserRequest;
import api.user.GetAllUsersRequest;
import baseTests.BaseTest;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import constants.Credentials;
import constants.URLs;
import io.restassured.RestAssured;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageObject.LoginPage;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.open;
import static constants.Credentials.*;
import static constants.Credentials.ADMIN_ROLE;

public class UserCreatingTest extends BaseTest {
    Faker faker = new Faker();
    String userName = faker.name().fullName();
    String position = faker.company().profession();
    String email = faker.internet().emailAddress();

    List<Integer> userId;

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
    }

    @Override
    @AfterEach
    public void tearDown() {
        GetAllUsersRequest getAllUsersRequest = new GetAllUsersRequest();
        userId = getAllUsersRequest.getResponseWithAllUsers(token)
                .extract()
                .body()
                .jsonPath().getList("id");
        userId = userId.stream()
                .sorted()
                .collect(Collectors.toList());
        //удаляем пользователя
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest();
        deleteUserRequest.getResponseForDeletingUserRequest(token, userId.get(userId.size() - 1).toString());
        Selenide.closeWindow();
    }

    @Test
    public void createOperator() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openUsersListPage()
                .createOperator(userName, position, email)
                .searchUser(userName)
                .checkUserRole(OPERATOR_ROLE);
    }

    @Test
    public void createSupervisor() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openUsersListPage()
                .createSupervisor(userName, position, email)
                .searchUser(userName)
                .checkUserRole(SUPERVISOR_ROLE);
    }

    @Test
    public void createOfficer() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openUsersListPage()
                .createOfficer(userName, position, email)
                .searchUser(userName)
                .checkUserRole(OFFICER_ROLE)
                .checkUserRole(OPERATOR_ROLE);
    }

    @Test
    public void createAdmin() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openProductionSetPage()
                .openUsersListPage()
                .createAdmin(userName, position, email)
                .searchUser(userName)
                .checkUserRole(ADMIN_ROLE);
    }
}
