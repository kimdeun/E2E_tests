package pageObject.productionSet;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class UserPage {
    @FindBy(how = How.CSS, using = ".ui-page-parameter-label")
    private ElementsCollection userRolesCollection;
    @FindBy(how = How.CSS, using = ".ui-btn-label")
    private SelenideElement userStateLabel;
    @FindBy(how = How.CSS, using = ".multiselect__placeholder")
    private SelenideElement selectStatusSpanInTheEditStatusModal;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Select user status']")
    private SelenideElement selectStatusInputInTheEditStatusModal;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Edit')]")
    private SelenideElement editButtonInTheEditStatusUserModal;
    @FindBy(how = How.XPATH, using = ".//span[text()='List is empty.']/parent::li")
    private SelenideElement listIsEmptyTip;

    public UserPage checkUserRole(String userRole) {
        userRolesCollection.should(CollectionCondition.containExactTextsCaseSensitive(userRole));
        return page(this);
    }

    public void checkUserState(String userState) {
        userStateLabel.shouldHave(Condition.exactText(userState));
    }

    public UserPage changeUserState() {
        userStateLabel.click();
        selectStatusSpanInTheEditStatusModal.click();
        listIsEmptyTip.shouldHave(Condition.attribute("style", "display: none;"));
        selectStatusInputInTheEditStatusModal.pressEnter();
        editButtonInTheEditStatusUserModal.click();
        return page(this);
    }
}
