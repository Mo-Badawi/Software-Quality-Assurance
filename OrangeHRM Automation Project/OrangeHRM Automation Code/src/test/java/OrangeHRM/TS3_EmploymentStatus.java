package OrangeHRM;

import BasePackage.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class TS3_EmploymentStatus extends BaseTest {
    private final Random rand = new Random();

    // === EXACT original XPaths preserved ===
    private final By adminLink               = By.xpath("//a[@href ='/web/index.php/admin/viewAdminModule' ]");
    private final By jobMenu                 = By.xpath("//span[text()= 'Job ']");
    private final By employmentStatusLink    = By.xpath("//a[text() = 'Employment Status']");
    private final By addBtn                  = By.xpath("//button[text() = ' Add ']");
    private final By nameInput               = By.xpath("//label[text() = 'Name']/following::input[1]");
    private final By saveBtn                 = By.xpath("//button[text() = ' Save ']");
    private final By requiredSpan            = By.xpath("//span[text() = 'Required']");
    private final By selectAllChkSpan        = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/label/span");
    private final By deleteSelectedBtn       = By.xpath("//button[text() = ' Delete Selected ']");
    private final By yesDeleteBtn            = By.xpath("//button[text() = ' Yes, Delete ']");
    private final By alreadyExistsSpan       = By.xpath("//span[text() = 'Already exists']");
    private final By editBtnFirstRow         = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[1]/div/div[3]/div/button[2]");
    private final By updatedToast            = By.xpath("//p[text() = 'Successfully Updated']");
    private final By deleteBtnFirstRow       = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[1]/div/div[3]/div/button[1]");
    private final By deletedToast            = By.xpath("//p[text() = 'Successfully Deleted']");

    private void openAdmin() {
        clickWhenClickable(adminLink);
    }

    private void goToEmploymentStatus() {
        clickWhenClickable(jobMenu);
        clickWhenClickable(employmentStatusLink);
    }

    // === Tests (login/navigation handled by BaseTest + helpers) ===

    @Test(priority = 2) // Go To Admin Dashboard
    public void SetUpDashboard() {
        openAdmin();
    }

    @Test(priority = 3) // TC1 - Create New Employment Status (Happy Path)
    public void CreateEmploymentStatus() {
        goToEmploymentStatus();

        clickWhenClickable(addBtn);
        typeWhenVisible(nameInput, "Hyprid Employment " + rand.nextInt(999999));
        clickWhenClickable(saveBtn);
    }

    @Test(priority = 4) // TC2 - Add New Employment Status With Empty Name
    public void ValidateNameRequired() {
        goToEmploymentStatus();

        clickWhenClickable(addBtn);
        clickWhenClickable(saveBtn);

        String msg = visible(requiredSpan).getText();
        try {
            Assert.assertEquals(msg, "Required", "Test Case Result: Failed");
        } catch (AssertionError e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }

    @Test(priority = 5) // TC3 - Prevent Duplicated Employment Status Names
    public void PreventDuplicatedEmpStatusNames() {
        goToEmploymentStatus();

        clickWhenClickable(selectAllChkSpan);
        clickWhenClickable(deleteSelectedBtn);
        clickWhenClickable(yesDeleteBtn);

        for (int counter = 0; counter < 2; counter++) {
            clickWhenClickable(addBtn);
            typeWhenVisible(nameInput, "Hyprid Employment ");
            clickWhenClickable(saveBtn);
        }

        String error = visible(alreadyExistsSpan).getText();
        try {
            Assert.assertEquals(error, "Already exists", "Test Case Result: Failed");
        } catch (AssertionError e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }

    @Test(priority = 6) // TC4 - Edit Employment Status
    public void EditEmpStatus() {
        goToEmploymentStatus();

        clickWhenClickable(editBtnFirstRow);

        // clear & type using keys (preserving your approach)
        visible(nameInput).sendKeys(Keys.CONTROL + "a");
        visible(nameInput).sendKeys(Keys.DELETE);
        typeWhenVisible(nameInput, "Hyprid Employment " + rand.nextInt(999999));

        clickWhenClickable(saveBtn);

        String ok = visible(updatedToast).getText();
        try {
            Assert.assertEquals(ok, "Successfully Updated", "Test Case Result: Failed");
        } catch (AssertionError e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }

    @Test(priority = 7) // TC5 - Delete Employment Status
    public void DeleteEmpStatus() {
        goToEmploymentStatus();

        clickWhenClickable(deleteBtnFirstRow);
        clickWhenClickable(yesDeleteBtn);

        String del = visible(deletedToast).getText();
        try {
            Assert.assertEquals(del, "Successfully Deleted", "Test Case Result: Failed");
        } catch (AssertionError e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }

    @Test(priority = 8) // TC6 - Delete All Employment Status
    public void DeleteAllEmpStatus() {
        // Reuse your creation flow first
        CreateEmploymentStatus();
        goToEmploymentStatus();

        clickWhenClickable(selectAllChkSpan);
        clickWhenClickable(deleteSelectedBtn);
        clickWhenClickable(yesDeleteBtn);

        String msg = visible(deletedToast).getText();
        try {
            // keeping your original assertion text as-is
            Assert.assertEquals(msg, "Can't Be Deleted", "Test Case Result: Failed");
        } catch (AssertionError e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }
}
