package supplySet;

import api.user.DeleteUserRequest;
import api.user.GetAllUsersRequest;
import baseTests.BaseTest;
import constants.Entities;
import constants.UserRoles;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.open;

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
                .openSupplySetPage()
                .openUsersListPage()
                .createOperator(userName, position, email)
                .searchUser(userName)
                .checkUserRole(UserRoles.OPERATOR.getRole());
    }

    @Test
    public void createSupervisor() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createSupervisor(userName, position, email)
                .searchUser(userName)
                .checkUserRole(UserRoles.SUPERVISOR.getRole());
    }

    @Test
    public void createOfficer() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createOfficer(userName, position, email)
                .searchUser(userName)
                .checkUserRole(UserRoles.OFFICER.getRole())
                .checkUserRole(UserRoles.OPERATOR.getRole());
    }

    @Test
    public void createAdmin() {
        loginPage.login(Entities.USER_LOGIN, Entities.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createAdmin(userName, position, email)
                .searchUser(userName)
                .checkUserRole(UserRoles.ADMIN.getRole());
    }
}
