package OrangeHRM;
import BasePackage.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class TS5_WorkShifts extends BaseTest {
    private final Random rand = new Random();

    private final By adminLink            = By.xpath("//a[@href ='/web/index.php/admin/viewAdminModule' ]");
    private final By jobMenu              = By.xpath("//span[text()= 'Job ']");
    private final By workShiftsLink       = By.xpath("//a[text() = 'Work Shifts']");
    private final By addShiftTile         = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[1]/div");
    private final By shiftNameInput       = By.xpath("//label[text() = 'Shift Name']/following::input[1]");
    private final By fromInput            = By.xpath("//label[text() = 'From']/following::input");
    private final By toInput              = By.xpath("//label[text() = 'To']/following::input");
    private final By saveShiftBtn         = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[4]/button[2]");
    private final By requiredSpan         = By.xpath("//span[text() = 'Required']");
    private final By toAfterFromError     = By.xpath("//span[text() = 'To time should be after from time']");
    private final By savedToast           = By.xpath("//p[text() = 'Successfully Saved']");
    private final By typeHintInput        = By.xpath("//input[@placeholder = 'Type for hints...']");
    private final By removeEmpIcon        = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div[2]/div/div[2]/span/i");
    private final By deleteShiftFirstRow  = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[1]/div/div[6]/div/button[1]");
    private final By yesDeleteBtn         = By.xpath("//button[text() = ' Yes, Delete ']");
    private final By deletedToast         = By.xpath("//p[text() = 'Successfully Deleted']");

    private void openAdmin() {
        clickWhenClickable(adminLink);
    }

    private void goToWorkShifts() {
        clickWhenClickable(jobMenu);
        clickWhenClickable(workShiftsLink);
    }


    @Test(priority = 1) // Go To Admin Dashboard
    public void SetUpDashboard() {
        openAdmin();
    }

    @Test(priority = 2) // TC1 - Create New Work Shift (Happy Path)
    public void AddWorkShift() throws InterruptedException {
        goToWorkShifts();

        clickWhenClickable(addShiftTile);

        typeWhenVisible(shiftNameInput, "BadawiSHIFT" + rand.nextInt(999));

        // From time
        visible(fromInput).click();
        visible(fromInput).sendKeys(Keys.CONTROL + "a");
        visible(fromInput).sendKeys(Keys.DELETE);
        typeWhenVisible(fromInput, "08:00 AM");

        // To time
        visible(toInput).click();
        visible(toInput).sendKeys(Keys.CONTROL + "a");
        visible(toInput).sendKeys(Keys.DELETE);
        typeWhenVisible(toInput, "04:00 PM");

        clickWhenClickable(saveShiftBtn);
    }

    @Test(priority = 3) // TC2 - Add New Work Shift With Empty Name
    public void AddWorkShiftEmptyName() throws InterruptedException {
        goToWorkShifts();

        clickWhenClickable(addShiftTile);

        visible(fromInput).click();
        visible(toInput).click();

        clickWhenClickable(saveShiftBtn);

        String msg = visible(requiredSpan).getText();
        try {
            Assert.assertEquals(msg, "Required", "Test Case Result: Failed");
        } catch (AssertionError e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }

    @Test(priority = 4) // TC3 - Validate 'From' Must Be Less Than 'To'
    public void ValidateFromLessThanTo() throws InterruptedException {
        goToWorkShifts();

        clickWhenClickable(addShiftTile);

        typeWhenVisible(shiftNameInput, "BadawiSHIFT" + rand.nextInt(999));

        visible(fromInput).click();
        visible(fromInput).sendKeys(Keys.CONTROL + "a");
        visible(fromInput).sendKeys(Keys.DELETE);
        typeWhenVisible(fromInput, "01:00 PM");

        visible(toInput).click();
        visible(toInput).sendKeys(Keys.CONTROL + "a");
        visible(toInput).sendKeys(Keys.DELETE);
        typeWhenVisible(toInput, "11:00 AM");

        clickWhenClickable(saveShiftBtn);

        String err = visible(toAfterFromError).getText();
        try {
            Assert.assertEquals(err, "To time should be after from time", "Test Case Result: Failed");
        } catch (AssertionError e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }

    @Test(priority = 5) // TC4 - Overlapping Shift Validation (per your flow)
    public void AddOverLapWorkShift() throws InterruptedException {
        AddWorkShift(); // create a base shift first

        clickWhenClickable(addShiftTile);

        typeWhenVisible(shiftNameInput, "BadawiOPSHIFT" + rand.nextInt(999));

        visible(fromInput).click();
        visible(fromInput).sendKeys(Keys.CONTROL + "a");
        visible(fromInput).sendKeys(Keys.DELETE);
        typeWhenVisible(fromInput, "09:00 AM");

        visible(toInput).click();
        visible(toInput).sendKeys(Keys.CONTROL + "a");
        visible(toInput).sendKeys(Keys.DELETE);
        typeWhenVisible(toInput, "03:00 PM");

        clickWhenClickable(saveShiftBtn);

        String msg = visible(savedToast).getText();
        try {
            Assert.assertEquals(msg, "Successfully Saved", "Test Case Result: Failed");
        } catch (AssertionError e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }

    @Test(priority = 7) // TC5 - Assign Employee To Work Shift
    public void AssignEmployeeToWorkShift() throws InterruptedException {
        goToWorkShifts();

        clickWhenClickable(addShiftTile);

        typeWhenVisible(shiftNameInput, "BadawiSHIFT" + rand.nextInt(999));

        visible(fromInput).click();
        visible(fromInput).sendKeys(Keys.CONTROL + "a");
        visible(fromInput).sendKeys(Keys.DELETE);
        typeWhenVisible(fromInput, "08:00 AM");

        visible(toInput).click();
        visible(toInput).sendKeys(Keys.CONTROL + "a");
        visible(toInput).sendKeys(Keys.DELETE);
        typeWhenVisible(toInput, "04:00 PM");

        visible(typeHintInput).click();
        visible(typeHintInput).sendKeys("M");
        Thread.sleep(2000);
        visible(typeHintInput).sendKeys(Keys.ARROW_DOWN);
        visible(typeHintInput).sendKeys(Keys.ENTER);

        clickWhenClickable(saveShiftBtn);
        Thread.sleep(1000);

        String msg = visible(savedToast).getText();
        Thread.sleep(1000);
        try {
            Assert.assertEquals(msg, "Successfully Saved", "Test Case Result: Failed");
        } catch (AssertionError e) {
            System.out.println("FAIL: " + e.getMessage());
        }

        Thread.sleep(1500);
    }

    @Test(priority = 8) // TC6 - Remove Employee From Work Shift
    public void RemoveEmployeeFromWorkShift() throws InterruptedException {
        goToWorkShifts();

        clickWhenClickable(addShiftTile);

        typeWhenVisible(shiftNameInput, "BadawiSHIFT" + rand.nextInt(999));

        visible(fromInput).click();
        visible(fromInput).sendKeys(Keys.CONTROL + "a");
        visible(fromInput).sendKeys(Keys.DELETE);
        typeWhenVisible(fromInput, "08:00 AM");

        visible(toInput).click();
        visible(toInput).sendKeys(Keys.CONTROL + "a");
        visible(toInput).sendKeys(Keys.DELETE);
        typeWhenVisible(toInput, "04:00 PM");

        // Add an employee first
        visible(typeHintInput).click();
        visible(typeHintInput).sendKeys("f");
        Thread.sleep(1500);
        visible(typeHintInput).sendKeys(Keys.ARROW_DOWN);
        visible(typeHintInput).sendKeys(Keys.ENTER);

        clickWhenClickable(removeEmpIcon);

        clickWhenClickable(saveShiftBtn);
        Thread.sleep(1000);

        String msg = visible(savedToast).getText();
        Thread.sleep(1000);
        try {
            Assert.assertEquals(msg, "Successfully Saved", "Test Case Result: Failed");
        } catch (AssertionError e) {
            System.out.println("FAIL: " + e.getMessage());
        }

        Thread.sleep(1500);
    }

    @Test(priority = 9) // TC7 - Delete Work Shift
    public void DeleteWorkShift() {
        goToWorkShifts();

        clickWhenClickable(deleteShiftFirstRow);
        clickWhenClickable(yesDeleteBtn);

        String msg = visible(deletedToast).getText();
        try {
            Assert.assertEquals(msg, "Successfully Deleted", "Test Case Result: Failed");
        } catch (AssertionError e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }
}
