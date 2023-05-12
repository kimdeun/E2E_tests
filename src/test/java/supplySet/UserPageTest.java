package supplySet;

import constants.Credentials;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import baseTests.BaseTest;

import static constants.Credentials.*;

public class UserPageTest extends BaseTest {
    Faker faker = new Faker();
    String userName = faker.name().fullName();
    String position = faker.company().profession();
    String email = faker.internet().emailAddress();

    @Test
    public void checkOperatorRole() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createOperator(userName, position, email)
                .searchUser(userName)
                .openUserPage()
                .checkUserRole(OPERATOR_ROLE);
    }

    @Test
    public void checkSupervisorRole() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createSupervisor(userName, position, email)
                .searchUser(userName)
                .openUserPage()
                .checkUserRole(SUPERVISOR_ROLE);
    }

    @Test
    public void checkOfficerRole() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createOfficer(userName, position, email)
                .searchUser(userName)
                .openUserPage()
                .checkUserRole(OPERATOR_ROLE)
                .checkUserRole(OFFICER_ROLE);
    }

    @Test
    public void checkAdminRole() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createAdmin(userName, position, email)
                .searchUser(userName)
                .openUserPage()
                .checkUserRole(ADMIN_ROLE);
    }

    @Test
    public void checkNotApprovedUserState() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createOperator(userName, position, email)
                .searchUser(userName)
                .openUserPage()
                .checkUserState(NOT_APPROVED_USERS_STATE);
    }

    @Test
    public void checkPendingInitialTrainingUserState() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createOperator(userName, position, email)
                .searchUser(userName)
                .changeUserState()
                .openUserPage()
                .checkUserState(PENDING_INITIAL_TRAINING_USERS_STATE);
    }

    @Test
    public void checkActiveUserState() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createOperator(userName, position, email)
                .searchUser(userName)
                .changeUserState()
                .changeUserState()
                .openUserPage()
                .checkUserState(ACTIVE_USERS_STATE);
    }

    @Test
    public void changeUserStateToPendingTraining() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createOperator(userName, position, email)
                .searchUser(userName)
                .openUserPage()
                .changeUserState()
                .checkUserState(PENDING_INITIAL_TRAINING_USERS_STATE);
    }

    @Test
    public void changeUserStateToActive() {
        loginPage.login(Credentials.USER_LOGIN, Credentials.USER_PASSWORD)
                .openSupplySetPage()
                .openUsersListPage()
                .createOperator(userName, position, email)
                .searchUser(userName)
                .openUserPage()
                .changeUserState()
                .changeUserState()
                .checkUserState(ACTIVE_USERS_STATE);
    }
}
