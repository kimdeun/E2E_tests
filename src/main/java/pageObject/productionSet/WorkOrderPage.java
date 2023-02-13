package pageObject.productionSet;

import com.codeborne.selenide.*;
import constants.Credentials;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.page;

public class WorkOrderPage {
    @FindBy(how = How.XPATH, using = ".//div[@class='ml-auto ui-page-label']/span[1]")
    private SelenideElement stateLabel;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Mark as']")
    private SelenideElement markAsInput;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-form-group']//a")
    private SelenideElement newStateOfPurchaseOrder;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Ok')]")
    private SelenideElement okButtonInTheUpdateStateModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-work-order-packing']//div[@class='ui-table-row']")
    private SelenideElement firstRowOfTheContainersTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-work-order-packing']//div[@class='ui-table-row']/div[last()]/button")
    private ElementsCollection containersStateCollectionInTheContainersTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-work-order-packing']//div[@class='ui-table-row']/div[2]/span")
    private ElementsCollection containersSkidNumbersCollectionInTheContainersTable;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Ok')]")
    private SelenideElement okButtonInTheUpdateWorkOrderStateConfirmationModal;

    public WorkOrderPage changeStateOfWorkOrder() {
        stateLabel.click();
        markAsInput.click();
        newStateOfPurchaseOrder.click();
        okButtonInTheUpdateStateModal.click();
        return page(this);
    }

    public WorkOrderPage waitForContainersTable() {
        firstRowOfTheContainersTable.shouldBe(Condition.visible, Duration.ofSeconds(180));
        return page(this);
    }

    public boolean containersTableIsDisplayed() {
        return firstRowOfTheContainersTable.isDisplayed();
    }

    public String getWorkOrderState() {
        return stateLabel.getText();
    }

    public void containersStateShouldBeInProduction() {
        containersStateCollectionInTheContainersTable.shouldHave(CollectionCondition.sizeGreaterThan(0));
        for (SelenideElement element : containersStateCollectionInTheContainersTable) {
            element.shouldHave(Condition.text(Credentials.STATE_IN_PRODUCTION));
        }
    }

    public void containersStateShouldBeProduced() {
        containersStateCollectionInTheContainersTable.shouldHave(CollectionCondition.sizeGreaterThan(0));
        for (SelenideElement element : containersStateCollectionInTheContainersTable) {
            element.shouldHave(Condition.text(Credentials.STATE_PRODUCED), Duration.ofSeconds(180));
        }
    }

    public WorkOrderPage clickOkButtonInTheUpdateWorOrderStateConfirmationModal() {
        okButtonInTheUpdateWorkOrderStateConfirmationModal.click();
        Selenide.sleep(1500);
        return page(this);
    }

    public String getRandomSkidNumber() {
        containersStateCollectionInTheContainersTable.shouldHave(CollectionCondition.sizeGreaterThan(0));
        return containersSkidNumbersCollectionInTheContainersTable.get(1).getText();
    }
}
