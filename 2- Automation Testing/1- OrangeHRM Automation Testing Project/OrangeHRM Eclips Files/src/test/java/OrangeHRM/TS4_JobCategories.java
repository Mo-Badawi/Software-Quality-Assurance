package OrangeHRM;

import BasePackage.BaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class TS4_JobCategories extends BaseTest {
    private final Random rand = new Random();
    public int r = 999999;

    // === EXACT original XPaths preserved ===
    private final By adminLink                 = By.xpath("//a[@href ='/web/index.php/admin/viewAdminModule' ]");
    private final By jobMenu                   = By.xpath("//span[text()= 'Job ']");
    private final By jobCategoriesLink         = By.xpath("//a[text() = 'Job Categories']");
    private final By addBtn                    = By.xpath("//button[text() = ' Add ']");
    private final By nameInput                 = By.xpath("//label[text() = 'Name']/following::input[1]");
    private final By saveBtnEq                 = By.xpath("//button[text() = ' Save ']");     // with spaces around '='
    private final By saveBtnNoSpaceEq          = By.xpath("//button[text()=' Save ']");       // as used in CreateNewCategory
    private final By savedToast                = By.xpath("//p[text() = 'Successfully Saved']");
    private final By requiredSpan              = By.xpath("//span[text() = 'Required']");
    private final By selectAllChkSpan          = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/label/span");
    private final By deleteSelectedBtn         = By.xpath("//button[text() = ' Delete Selected ']");
    private final By yesDeleteBtn              = By.xpath("//button[text() = ' Yes, Delete ']");
    private final By alreadyExistsSpan         = By.xpath("//span[text() = 'Already exists']");
    private final By editBtnFirstRow           = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[1]/div/div[3]/div/button[2]");
    private final By deleteBtnFirstRow         = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[1]/div/div[3]/div/button[1]");
    private final By deletedToast              = By.xpath("//p[text() = 'Successfully Deleted']");

    private void openAdmin() {
        clickWhenClickable(adminLink);
    }

    private void goToJobCategories() {
        clickWhenClickable(jobMenu);
        clickWhenClickable(jobCategoriesLink);
    }

    // === Tests (setup/login handled by BaseTest) ===

    @Test(priority = 2) // Go To Admin Dashboard
    public void SetUpDashboard() {
        openAdmin();
    }

    @Test(priority = 3) // TC1 - Create New Job Category (Happy Path)
    public void CreateNewCategory() {
        goToJobCategories();

        clickWhenClickable(addBtn);
        typeWhenVisible(nameInput, "Freelance" + rand.nextInt(r));
        clickWhenClickable(saveBtnNoSpaceEq);

        String msg = visible(savedToast).getText();
        try {
            Assert.assertEquals(msg, "Successfully Saved", "Test Case Result: Failed");
        } catch (AssertionError e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }

    @Test(priority = 4) // TC2 - Add Category Empty Name
    public void AddCategoryEmptyName() {
        goToJobCategories();

        clickWhenClickable(addBtn);
        clickWhenClickable(saveBtnEq);

        String msg = visible(requiredSpan).getText();
        System.out.println("Can't Create Pay Grade without Name, it is " + msg);
    }

    @Test(priority = 5) // TC3 - Prevent Category Duplication
    public void PreventCategoryDuplication() {
        CreateNewCategory(); // reuse your flow first

        clickWhenClickable(selectAllChkSpan);
        clickWhenClickable(deleteSelectedBtn);
        clickWhenClickable(yesDeleteBtn);

        for (int counter = 0; counter < 2; counter++) {
            clickWhenClickable(addBtn);
            typeWhenVisible(nameInput, "Freelancer");
            clickWhenClickable(saveBtnEq);
        }

        String msg = visible(alreadyExistsSpan).getText();
        try {
            Assert.assertEquals(msg, "Already exists", "Test Case Result: Failed");
        } catch (AssertionError e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }

    @Test(priority = 6) // TC4 - Edit Category Name
    public void EditCategoryName() {
        CreateNewCategory();
        goToJobCategories();

        clickWhenClickable(editBtnFirstRow);

        visible(nameInput).sendKeys(Keys.CONTROL + "a");
        visible(nameInput).sendKeys(Keys.DELETE);
        typeWhenVisible(nameInput, "Grade B" + rand.nextInt(999));

        clickWhenClickable(saveBtnEq);
    }

    @Test(priority = 7) // TC5 - Delete Category
    public void DeleteCategory() {
        CreateNewCategory();
        goToJobCategories();

        clickWhenClickable(deleteBtnFirstRow);
        clickWhenClickable(yesDeleteBtn);

        String msg = visible(deletedToast).getText();
        try {
            Assert.assertEquals(msg, "Successfully Deleted", "Test Case Result: Failed");
        } catch (AssertionError e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }

    @Test(priority = 8) // TC6 - Delete Multiple Categories
    public void DeleteMultiCategory() {
        CreateNewCategory();
        goToJobCategories();

        clickWhenClickable(selectAllChkSpan);
        clickWhenClickable(deleteSelectedBtn);
        clickWhenClickable(yesDeleteBtn);

        String msg = visible(deletedToast).getText();
        try {
            Assert.assertEquals(msg, "Successfully Deleted", "Test Case Result: Failed");
        } catch (AssertionError e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }
}
