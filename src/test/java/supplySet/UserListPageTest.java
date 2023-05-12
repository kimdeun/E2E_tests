package supplySet;

import constants.Credentials;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import baseTests.BaseTest;

import static constants.Credentials.*;

public class UserListPageTest extends BaseTest {
    Faker faker = new Faker();
    String userName = faker.name().fullName();
    String position = faker.company().profession();
    String email = faker.internet().emailAddress();

    @Test
    public void createOperator() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createOperator(userName, position, email)
                .searchUser(userName)
                .checkUserRole(OPERATOR_ROLE);
    }

    @Test
    public void createSupervisor() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createSupervisor(userName, position, email)
                .searchUser(userName)
                .checkUserRole(SUPERVISOR_ROLE);
    }

    @Test
    public void createOfficer() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createOfficer(userName, position, email)
                .searchUser(userName)
                .checkUserRole(OFFICER_ROLE)
                .checkUserRole(OPERATOR_ROLE);
    }

    @Test
    public void createAdmin() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createAdmin(userName, position, email)
                .searchUser(userName)
                .checkUserRole(ADMIN_ROLE);
    }

    @Test
    public void checkUserName() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createOperator(userName, position, email)
                .searchUser(userName)
                .checkUserName(userName);
    }

    @Test
    public void checkUserCompany() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createOperator(userName, position, email)
                .searchUser(userName)
                .checkCompanyName();
    }

    @Test
    public void checkUserPosition() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createOperator(userName, position, email)
                .searchUser(userName)
                .checkPositionName(position);
    }

    @Test
    public void checkUserEmail() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createOperator(userName, position, email)
                .searchUser(userName)
                .checkEmail(email);
    }

    @Test
    public void checkNotApprovedUserState() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createOperator(userName, position, email)
                .searchUser(userName)
                .checkUsersState(NOT_APPROVED_USERS_STATE);
    }

    @Test
    public void changeUserStateToPendingTraining() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createOperator(userName, position, email)
                .searchUser(userName)
                .changeUserState()
                .checkUsersState(PENDING_INITIAL_TRAINING_USERS_STATE);
    }

    @Test
    public void changeUserStateToActive() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createOperator(userName, position, email)
                .searchUser(userName)
                .changeUserState()
                .changeUserState()
                .checkUsersState(ACTIVE_USERS_STATE);
    }
}
