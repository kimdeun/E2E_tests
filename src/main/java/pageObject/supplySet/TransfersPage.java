package pageObject.supplySet;

import com.codeborne.selenide.SelenideElement;
import constants.Credentials;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;

public class TransfersPage {
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div/div[1]/div[2]/span")
    private SelenideElement containerNumber;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div/div[1]/div[4]/span")
    private SelenideElement containerSealType;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-content']/div/div[1]/div[5]/span")
    private SelenideElement containerSealColor;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div[6]/div[1]/div[1]")
    private SelenideElement containersQuantity;
//    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div[7]/span")
//    private SelenideElement containerLogo;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div[8]/span")
    private SelenideElement containersStartNumber;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div[9]/span")
    private SelenideElement containersEndNumber;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div[10]/span")
    private SelenideElement containersStateInTheTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div[11]/span")
    private SelenideElement containersCommentInTheTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-page-header']/div[2]//span")
    private SelenideElement containersStateInTheTopRightCorner;

    public void checkContainerNumber(String containerNumber) {
        this.containerNumber.shouldHave(exactText(containerNumber));
    }

    public void checkContainerSealType() {
        containerSealType.shouldHave(exactText(Credentials.SEAL_TYPE));
    }

    public void checkContainerColor() {
        containerSealColor.shouldHave(attribute("style", Credentials.GRAY_COLOR_OF_THE_SEAL));
    }

    public void checkContainersQuantity(String containersQuantity) {
        this.containersQuantity.shouldHave(exactText(containersQuantity));
    }

    // Нужно обновить дамп, удалить test logo
//    public void checkContainerLogo() {
//        containerLogo.shouldHave(exactText(Credentials.LOGO));
//    }

    public void checkContainersStartNumber(String containersStartNumber) {
        this.containersStartNumber.shouldHave(exactText(containersStartNumber));
    }

    public void checkContainersEndNumber(String containersEndNumber) {
        this.containersEndNumber.shouldHave(exactText(containersEndNumber));
    }

    public void checkContainersTransitStateInTheTable() {
        containersStateInTheTable.shouldHave(exactText(Credentials.TRANSIT_STATE));
    }

    public void checkContainersProblemStateInTheTable() {
        containersStateInTheTable.shouldHave(exactText(Credentials.PROBLEM_STATE));
    }

    public void checkContainersLostStateInTheTable() {
        containersStateInTheTable.shouldHave(exactText(Credentials.LOST_STATE));
    }

    public void checkContainersCommentInTheTable(String comment) {
        containersCommentInTheTable.shouldHave(exactText(comment));
    }

    public void checkContainersTransitStateInTheTopRightCorner() {
        containersStateInTheTopRightCorner.shouldHave((exactText(Credentials.TRANSIT_STATE)));
    }

    public void checkContainersReceivedWithAProblemStateInTheTopRightCorner() {
        containersStateInTheTopRightCorner.shouldHave((exactText(Credentials.RECEIVED_WITH_A_PROBLEM_STATE)));
    }
}
