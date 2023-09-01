package pageObject.productionSet;

import com.codeborne.selenide.*;
import constants.Entities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.page;

public class WorkOrderPage {
    @FindBy(how = How.XPATH, using = ".//span[text()='Entered']")
    private SelenideElement enteredStateLabel;
    @FindBy(how = How.XPATH, using = ".//span[text()='Confirmed']")
    private SelenideElement confirmedStateLabel;
    @FindBy(how = How.XPATH, using = ".//span[text()='Produced']")
    private SelenideElement producedStateLabel;
    @FindBy(how = How.XPATH, using = ".//span[text()='In Production']")
    private SelenideElement inProductionStateLabel;
    @FindBy(how = How.XPATH, using = ".//input[@placeholder='Mark as']")
    private SelenideElement markAsInput;
    @FindBy(how = How.CSS, using = "ul[role='listbox'].dropdown-menu a")
    private SelenideElement newStateOfWorkOrder;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-form-group']//a[contains(text(), 'In Production')]")
    private SelenideElement inProductionStateOfWO;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Ok')]")
    private SelenideElement okButtonInTheUpdateStateModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-work-order-packing']//div[@class='ui-table-row']")
    private SelenideElement rowsOfTheContainersTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row']/div[last()]/button")
    private ElementsCollection containersStateCollectionInContainersTable;
    @FindBy(how = How.XPATH, using = ".//div[contains(text(), 'Ok')]")
    private SelenideElement okButtonInUpdateWOStateConfirmationModal;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]//button[contains(text(), 'Skid')]")
    private SelenideElement firstSkidInTheContainersTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=1]//button[contains(text(), 'In Production') or contains(text(), 'Produced')]")
    private SelenideElement firstSkidStateInTheContainersTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=2]//button[contains(text(), 'Box')]")
    private SelenideElement firstBoxInTheSkidInTheContainersTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=2]//button[contains(text(), 'In Production') or contains(text(), 'Produced')]")
    private SelenideElement firstBoxStateInTheContainersTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=3]//button[contains(text(), 'Bag')]")
    private SelenideElement firstBagInTheContainersTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=3]//button[contains(text(), 'In Production') or contains(text(), 'Produced')]")
    private SelenideElement firstBagStateInTheContainersTable;
    @FindBy(how = How.XPATH, using = ".//div[@class='ui-table-row-wrapper' and position()=4]//button[contains(text(), 'Seal')]")
    private SelenideElement firstSealInTheContainersTable;

//    public WorkOrderPage changeStateOfWorkOrder() {
//        stateLabel.click();
//        markAsInput.click();
//        newStateOfWorkOrder.click();
//        okButtonInTheUpdateStateModal.click();
//        return page(this);
//    }
    public WorkOrderPage changeStateOfWorkOrderToConfirmed() {
        enteredStateLabel.click();
        markAsInput.click();
        newStateOfWorkOrder.click();
        okButtonInTheUpdateStateModal.click();
        okButtonInTheUpdateStateModal.shouldNotBe(Condition.visible, Duration.ofSeconds(300));
        return page(this);
    }

    public WorkOrderPage changeStateOfWorkOrderToInProduction() {
        confirmedStateLabel.click();
        markAsInput.click();
        inProductionStateOfWO.click();
        okButtonInTheUpdateStateModal.click();
        okButtonInTheUpdateStateModal.shouldNotBe(Condition.visible, Duration.ofSeconds(300));
        return page(this);
    }

    public WorkOrderPage changeStateOfWorkOrderToProduced() {
        confirmedStateLabel.click();
        markAsInput.click();
        newStateOfWorkOrder.click();
        okButtonInTheUpdateStateModal.click();
        okButtonInTheUpdateStateModal.click();
        okButtonInTheUpdateStateModal.shouldNotBe(Condition.visible, Duration.ofSeconds(300));
        return page(this);
    }

    public void stateShouldBeConfirmed() {
        confirmedStateLabel.shouldHave(Condition.text(Entities.STATE_CONFIRMED));
    }

    public void stateShouldBeInProduction() {
        inProductionStateLabel.shouldHave(Condition.text(Entities.STATE_IN_PRODUCTION));
    }

    public void stateShouldBeProduced() {
        producedStateLabel.shouldHave(Condition.text(Entities.STATE_PRODUCED), Duration.ofSeconds(300));
    }

    public WorkOrderPage waitForContainersTable() {
        rowsOfTheContainersTable.shouldBe(Condition.visible, Duration.ofSeconds(300));
        return page(this);
    }

    public boolean containersTableIsDisplayed() {
        return rowsOfTheContainersTable.isDisplayed();
    }

    public void containersStateShouldBeInProduction() {
        containersStateCollectionInContainersTable.shouldHave(CollectionCondition.sizeGreaterThan(0));
        for (SelenideElement element : containersStateCollectionInContainersTable) {
            element.shouldHave(Condition.text(Entities.STATE_IN_PRODUCTION));
        }
    }

    public void containersStateShouldBeProduced() {
        containersStateCollectionInContainersTable.shouldHave(CollectionCondition.sizeGreaterThan(0));
        for (SelenideElement element : containersStateCollectionInContainersTable) {
            element.shouldHave(Condition.text(Entities.STATE_PRODUCED), Duration.ofSeconds(300));
        }
    }

//    public WorkOrderPage clickOkButtonInTheUpdateWorOrderStateConfirmationModal() {
//        okButtonInTheUpdateWorkOrderStateConfirmationModal.click();
//        okButtonInTheUpdateStateModal.shouldNotBe(Condition.visible, Duration.ofSeconds(30));
//        Selenide.sleep(1500);
//        return page(this);
//    }

    public WorkOrderPage firstChildContainerIsBox() {
        firstBoxInTheSkidInTheContainersTable.shouldHave(Condition.text(Entities.BOX));
        return page(this);
    }

    public WorkOrderPage firstChildContainerIsBag() {
        firstBagInTheContainersTable.shouldHave(Condition.text(Entities.BAG));
        return page(this);
    }

    public WorkOrderPage firstChildContainerIsSeal() {
        firstSealInTheContainersTable.shouldHave(Condition.text(Entities.SEAL));
        return page(this);
    }

    public WorkOrderPage changeStateOfTheSkid() {
        firstSkidStateInTheContainersTable.click();
        okButtonInTheUpdateStateModal.click();
        okButtonInTheUpdateStateModal.shouldNotBe(Condition.visible, Duration.ofSeconds(10));
        firstSkidInTheContainersTable.click();
        return page(this);
    }

    public WorkOrderPage changeStateOfTheBox() {
        firstSkidInTheContainersTable.click();
        firstBoxInTheSkidInTheContainersTable.shouldHave(Condition.text(Entities.BOX));
        firstBoxStateInTheContainersTable.click();
        okButtonInTheUpdateStateModal.click();
        okButtonInTheUpdateStateModal.shouldNotBe(Condition.visible, Duration.ofSeconds(10));
        firstBoxInTheSkidInTheContainersTable.click();
        return page(this);
    }

    public WorkOrderPage changeStateOfTheBag() {
        firstSkidInTheContainersTable.click();
        firstBoxInTheSkidInTheContainersTable.click();
        firstBagInTheContainersTable.shouldHave(Condition.text(Entities.BAG));
        firstBagStateInTheContainersTable.click();
        okButtonInTheUpdateStateModal.click();
        okButtonInTheUpdateStateModal.shouldNotBe(Condition.visible, Duration.ofSeconds(10));
        firstBagInTheContainersTable.click();
        return page(this);
    }
}
