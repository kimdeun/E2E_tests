package pageObject.productionSet;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import static constants.Entities.*;

public class UsersListPage {
    @FindBy(how = How.XPATH, using = ".//button[contains(text(), 'Create')]")
    private SelenideElement createUserButton;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Name']")
    private SelenideElement nameInput;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Position']")
    private SelenideElement positionInput;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select company']")
    private SelenideElement companySelect;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Email']")
    private SelenideElement emailInput;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Go to selecting the roles')]")
    private SelenideElement goToSelectingTheRolesButton;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), '+ Add role')]")
    private SelenideElement addRoleButton;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select role']")
    private SelenideElement roleSelect;
    @FindBy(how = How.XPATH, using = ".//div[@class='multiselect__tags']")
    private SelenideElement locationAccessDiv;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select location access']")
    private SelenideElement locationAccessInput;
    @FindBy(how = How.XPATH, using = ".//button[contains(text(), 'Add role')]")
    private SelenideElement addRoleButtonInTheSelectRoles;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Create user')]")
    private SelenideElement createUserButtonInTheModal;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Search']")
    private SelenideElement searchInput;
    @FindBy(how = How.XPATH, using = ".//button[text()='Search ']")
    private SelenideElement searchButton;
    @FindBy(how = How.CSS, using = "a.ui-link[href^='#/production/user/']")
    private SelenideElement userNameInTheTable;
    @FindBy(how = How.CSS, using = "a.ui-link[href^='#/supply/company/']")
    private SelenideElement companyNameInTheTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]/div[1]/div[4]//span")
    private SelenideElement positionNameInTheTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]/div[1]/div[5]//span")
    private ElementsCollection userRolesCollection;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]//span[contains(text(), '@')]")
    private SelenideElement emailInTheTable;
    @FindBy(how = How.CSS, using = ".ui-table-row-wrapper:nth-child(1) .btn.ui-btn")
    private SelenideElement usersStateButton;
    @FindBy(how = How.CSS, using = ".no-options")
    private SelenideElement emptyListOfDataInTheModal;
    @FindBy(how = How.XPATH, using = ".//button[contains(text(), 'Search')]")
    private SelenideElement refreshButton;
    @FindBy(how = How.CSS, using = ".multiselect__single")
    private SelenideElement selectStatusSpanInTheEditStatusModal;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select user status']")
    private SelenideElement selectStatusInputInTheEditStatusModal;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Edit')]")
    private SelenideElement editButtonInTheEditStatusUserModal;
    @FindBy(how = How.XPATH, using = ".//span[text()='List is empty.']/parent::li")
    private SelenideElement listIsEmptyTip;

    public UsersListPage searchUser(String userName) {
        refreshButton.click();
        sleep(2500);
        searchInput.click();
        searchInput.setValue(userName);
        searchButton.click();
        userNameInTheTable.shouldHave(exactText(userName));
        return page(this);
    }

    private void addUserInfo(String name, String position, String email) {
        createUserButton.click();
        nameInput.setValue(name);
        positionInput.setValue(position);
        companySelect.click();
        waitForListOfDateInTheModal();
        companySelect.setValue(USERS_COMPANY).pressEnter();
        emailInput.setValue(email);
        goToSelectingTheRolesButton.click();
        addRoleButton.click();
        roleSelect.click();
        waitForListOfDateInTheModal();
    }

    public UsersListPage createOperator(String name, String position, String email) {
        addUserInfo(name, position, email);
        roleSelect.setValue(OPERATOR_ROLE).pressEnter();
        locationAccessDiv.click();
        locationAccessInput.pressEnter();
        addRoleButtonInTheSelectRoles.click();
        createUserButtonInTheModal.click();
        return page(this);
    }

    public UsersListPage createSupervisor(String name, String position, String email) {
        addUserInfo(name, position, email);
        roleSelect.setValue(SUPERVISOR_ROLE).pressEnter();
        locationAccessDiv.click();
        locationAccessInput.pressEnter();
        addRoleButtonInTheSelectRoles.click();
        createUserButtonInTheModal.click();
        return page(this);
    }

    public UsersListPage createOfficer(String name, String position, String email) {
        addUserInfo(name, position, email);
        roleSelect.setValue(OFFICER_ROLE).pressEnter();
        addRoleButtonInTheSelectRoles.click();
        createUserButtonInTheModal.click();
        return page(this);
    }

    public UsersListPage createAdmin(String name, String position, String email) {
        addUserInfo(name, position, email);
        roleSelect.setValue(ADMIN_ROLE).pressEnter();
        addRoleButtonInTheSelectRoles.click();
        createUserButtonInTheModal.click();
        return page(this);
    }

    public void checkUserName(String userName) {
        userNameInTheTable.shouldHave(exactText(userName));
    }

    public void checkCompanyName() {
        companyNameInTheTable.shouldHave(exactText(USERS_COMPANY));
    }

    public void checkPositionName(String position) {
        positionNameInTheTable.shouldHave(exactText(position));
    }

    public UsersListPage checkUserRole(String userRole) {
        userRolesCollection.should(CollectionCondition.containExactTextsCaseSensitive(userRole));
        return page(this);
    }

    public void checkEmail(String email) {
        emailInTheTable.shouldHave(exactText(email));
    }

    public void checkUsersState(String userState) {
        usersStateButton.shouldHave(exactText(userState));
    }

    public void waitForListOfDateInTheModal() {
        emptyListOfDataInTheModal.shouldNotBe(exist);
    }

    public UserPage openUserPage() {
        userNameInTheTable.click();
        return page(UserPage.class);
    }

    public UsersListPage changeUserState() {
        usersStateButton.click();
        selectStatusSpanInTheEditStatusModal.click();
        listIsEmptyTip.shouldHave(Condition.attribute("style", "display: none;"));
        selectStatusInputInTheEditStatusModal.pressEnter();
        editButtonInTheEditStatusUserModal.click();
        return page(this);
    }
}
