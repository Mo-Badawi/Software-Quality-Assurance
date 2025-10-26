package OrangeHRM;

import BasePackage.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class TS2_PayGrades extends BaseTest {
    private final Random rand = new Random();

    // === EXACT original XPaths preserved ===
    private final By adminLink                 = By.xpath("//a[@href ='/web/index.php/admin/viewAdminModule' ]");
    private final By jobMenu                   = By.xpath("//span[text()= 'Job ']");
    private final By payGradesLink             = By.xpath("//a[text() = 'Pay Grades']");
    private final By addBtn                    = By.xpath("//button[text() = ' Add ']");
    private final By nameInput                 = By.xpath("//label[text() = 'Name']/following::input[1]");
    private final By paySaveBtn                = By.xpath("//button[text() = ' Save ']");
    private final By requiredSpan              = By.xpath("//span[text() = 'Required']");
    private final By addCurrencyToolbarBtn     = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div[2]/div/div[1]/div/button");
    private final By chooseCurrencyDropdown1   = By.xpath("//div[text() = '-- Select --']");
    private final By currencyOptionAED         = By.xpath("//span[text() = 'AED - Utd. Arab Emir. Dirham']");
    private final By minSalaryInput            = By.xpath(" //label[text() = 'Minimum Salary']/following::input[1]");
    private final By maxSalaryInput            = By.xpath("//label[text() = 'Maximum Salary']/following::input[1]");
    private final By currencySaveBtn           = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div[2]/form/div[3]/button[2]");
    private final By chooseCurrencyDropdown2   = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div[2]/form/div[1]/div/div/div/div[2]/div/div");
    private final By minLessThanMaxError       = By.xpath("//span[text()='Should be higher than Minimum Salary']");
    private final By payGradeEditBtn           = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[1]/div/div[4]/div/button[2]");
    private final By updatedToast              = By.xpath("//p[text()='Successfully Updated']");
    private final By currencyDeleteBtn         = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div[2]/div/div[3]/div/div[2]/div/div/div[5]/div/button[1]");
    private final By confirmDeleteDialogBtn    = By.xpath("//*[@id=\"app\"]/div[3]/div/div/div/div[3]/button[2]");
    private final By payGradeDeleteBtn         = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[1]/div/div[4]/div/button[1]");
    private final By yesDeleteBtn              = By.xpath("//button[text()= ' Yes, Delete ']");
    private final By deletedToast              = By.xpath("//p[text()= 'Successfully Deleted']");

    private void openAdmin() {
        clickWhenClickable(adminLink);
    }

    private void goToPayGrades() {
        clickWhenClickable(jobMenu);
        clickWhenClickable(payGradesLink);
    }

    // === Tests (login/setup handled by BaseTest) ===

    @Test(priority = 1) // Go To Admin Dashboard
    public void SetUpDashboard() {
        openAdmin();
    }

    @Test(priority = 2) // TC1 - Add New Pay Grade (Happy Path)
    public void AddNewPayGrade() throws InterruptedException {
        goToPayGrades();

        clickWhenClickable(addBtn);
        typeWhenVisible(nameInput, "Grade A" + rand.nextInt(999999));
        clickWhenClickable(paySaveBtn);
        Thread.sleep(2000); // To See when Selenium click Save
    }

    @Test(priority = 3) // TC2 - Add New Pay Grade With Empty Name
    public void AddNewPayGradeEmptyName() throws InterruptedException {
        goToPayGrades();
        Thread.sleep(2000); // To prevent Add button overlapping (No special or unique Identifier)

        clickWhenClickable(addBtn);
        clickWhenClickable(paySaveBtn);

        String msg = visible(requiredSpan).getText();
        try {
            Assert.assertEquals(msg, "Required", "Test Case Result: Failed");
        } catch (AssertionError e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }

    @Test(priority = 4) // TC3 - Add New Currency With Valid Min Max Values
    public void AddNewCurrencyWithValidMinMaxValues() throws InterruptedException {
        goToPayGrades();
        Thread.sleep(2000);

        clickWhenClickable(addBtn);
        typeWhenVisible(nameInput, "Grade A" + rand.nextInt(999999));
        clickWhenClickable(paySaveBtn);

        clickWhenClickable(addCurrencyToolbarBtn);

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200);");

        clickWhenClickable(chooseCurrencyDropdown1);
        clickWhenClickable(currencyOptionAED);

        typeWhenVisible(minSalaryInput, "2000");
        typeWhenVisible(maxSalaryInput, "4000");

        Thread.sleep(3000); // Stop to see what happened

        clickWhenClickable(currencySaveBtn);
    }

    @Test(priority = 5) // TC4 - Validate Min Must Be Less Than Max
    public void ValidateMinMustBeLessThanMax() throws InterruptedException {
        goToPayGrades();
        Thread.sleep(2000);

        clickWhenClickable(addBtn);
        Thread.sleep(2000);

        typeWhenVisible(nameInput, "Grade A" + rand.nextInt(999999));
        Thread.sleep(500);

        clickWhenClickable(paySaveBtn);
        Thread.sleep(2000);

        clickWhenClickable(addBtn);

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200);");

        clickWhenClickable(chooseCurrencyDropdown2);
        clickWhenClickable(currencyOptionAED);

        typeWhenVisible(minSalaryInput, "4000");
        typeWhenVisible(maxSalaryInput, "2000");

        String err = visible(minLessThanMaxError).getText();
        try {
            Assert.assertEquals(err, "Should be higher than Minimum Salary", "There is an Error");
        } catch (AssertionError e) {
            System.out.println("Assertion Failed: " + e.getMessage());
        }
    }

    @Test(priority = 6) // TC5 - Edit Pay Grade Name
    public void EditPayGradeName() throws InterruptedException {
        goToPayGrades();
        Thread.sleep(2000);

        clickWhenClickable(payGradeEditBtn);

        // clear & type new name
        visible(nameInput).sendKeys(Keys.CONTROL + "a");
        visible(nameInput).sendKeys(Keys.DELETE);
        typeWhenVisible(nameInput, "Grade B" + rand.nextInt(999999));

        clickWhenClickable(paySaveBtn);

        String status = visible(updatedToast).getText();
        try {
            Assert.assertEquals(status, "Successfully Updated", "There is an Error");
        } catch (AssertionError e) {
            System.out.println("Assertion Failed: " + e.getMessage());
        }
    }

    @Test(priority = 7) // TC6 - Delete Currency Row
    public void DeleteCurrencyRow() throws InterruptedException {
        goToPayGrades();
        Thread.sleep(2000);

        clickWhenClickable(addBtn);
        typeWhenVisible(nameInput, "Grade A" + rand.nextInt(999999));
        clickWhenClickable(paySaveBtn);

        clickWhenClickable(addBtn);

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200);");

        clickWhenClickable(chooseCurrencyDropdown2);
        clickWhenClickable(currencyOptionAED);

        typeWhenVisible(minSalaryInput, "2000");
        typeWhenVisible(maxSalaryInput, "4000");

        clickWhenClickable(currencySaveBtn);

        clickWhenClickable(currencyDeleteBtn);
        clickWhenClickable(confirmDeleteDialogBtn);
    }

    @Test(priority = 8) // TC7 - Delete Pay Grade
    public void DeletePayGrade() throws InterruptedException {
        goToPayGrades();
        Thread.sleep(2000);

        clickWhenClickable(payGradeDeleteBtn);
        clickWhenClickable(yesDeleteBtn);

        String delMsg = visible(deletedToast).getText();
        try {
            Assert.assertEquals(delMsg, "Successfully Deleted", "There is an Error");
        } catch (AssertionError e) {
            System.out.println("Assertion Failed: " + e.getMessage());
        }
    }
}
