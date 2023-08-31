package productionSet;

import api.user.DeleteUserRequest;
import api.user.GetAllUsersRequest;
import baseTests.BaseTest;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import constants.Entities;
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
import static constants.Entities.*;
import static constants.Entities.ADMIN_ROLE;

public class UserCreatingTest extends BaseTest {
    Faker faker = new Faker();
    String userName = faker.name().fullName();
    String position = faker.company().profession();
    String email = faker.internet().emailAddress();

    List<Integer> userId;

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
        //delete user
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest();
        deleteUserRequest.getResponseForDeletingUserRequest(token, userId.get(userId.size() - 1).toString());
    }

    @Test
    public void createOperator() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .openProductionSetPage()
                .openUsersListPage()
                .createOperator(userName, position, email)
                .searchUser(userName)
                .checkUserRole(OPERATOR_ROLE);
    }

    @Test
    public void createSupervisor() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .openProductionSetPage()
                .openUsersListPage()
                .createSupervisor(userName, position, email)
                .searchUser(userName)
                .checkUserRole(SUPERVISOR_ROLE);
    }

    @Test
    public void createOfficer() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .openProductionSetPage()
                .openUsersListPage()
                .createOfficer(userName, position, email)
                .searchUser(userName)
                .checkUserRole(OFFICER_ROLE)
                .checkUserRole(OPERATOR_ROLE);
    }

    @Test
    public void createAdmin() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .openProductionSetPage()
                .openUsersListPage()
                .createAdmin(userName, position, email)
                .searchUser(userName)
                .checkUserRole(ADMIN_ROLE);
    }
}
