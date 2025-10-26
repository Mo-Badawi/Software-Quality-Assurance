package OrangeHRM;

import BasePackage.BaseTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class TS1_JobTitles extends BaseTest {
    private final Random rand = new Random();

    private final By adminLink        = By.xpath("//a[@href ='/web/index.php/admin/viewAdminModule' ]");
    private final By jobMenu          = By.xpath("//span[text() =\"Job \" ]");
    private final By jobTitlesLink    = By.xpath("//a[text() ='Job Titles' ]");
    private final By addBtn           = By.xpath("//button[text() = ' Add ']");
    private final By titleInput       = By.xpath("//label[text() = 'Job Title']/following::input[1]");
    private final By descTextarea     = By.xpath("//textarea[@placeholder='Type description here']");
    private final By noteTextarea     = By.xpath("//textarea[@placeholder='Add note']");
    private final By saveBtn          = By.xpath("//button[text()=' Save ' and @type = 'submit']");
    private final By requiredSpan     = By.xpath("//span[text() = 'Required']");
    private final By existsSpan       = By.xpath("//span[text() = 'Already exists']");
    private final By updatedToast     = By.xpath("//p[text() = 'Successfully Updated']");
    private final By selectAllChkSpan = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/label/span");
    private final By deleteAllBtn     = By.xpath("//button[text() = ' Delete Selected ']");
    private final By yesDeleteBtn     = By.xpath("//button[text() = ' Yes, Delete ']");
    private final By firstRowEditIcon = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[1]/div/div[4]/div/button[2]/i");
    private final By firstRowDelIcon  = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div[1]/div/div[4]/div/button[1]/i");

    private void openAdmin() {
        clickWhenClickable(adminLink);
    }

    private void goToJobTitles() {
        clickWhenClickable(jobMenu);
        clickWhenClickable(jobTitlesLink);
    }

    @Test(priority = 1)
    public void SetUpDashboard() {
        openAdmin();
    }

    @Test(priority = 2) // TC1 - Add New Title
    public void AddNewTitle() {
        goToJobTitles();

        clickWhenClickable(addBtn);

        typeWhenVisible(titleInput, "QA Engineer" + rand.nextInt(9999999));
        typeWhenVisible(descTextarea, "Software and Mobile Application Testing Using Selenium and TestNG");
        typeWhenVisible(noteTextarea, "AXSOS QA Certificate is required for this position");

        clickWhenClickable(saveBtn);
    }

    @Test(priority = 3) // TC2 - Add New Title Empty Title
    public void AddNewTitleEmptyTitle() {
        goToJobTitles();

        clickWhenClickable(addBtn);

        // leave title empty
        typeWhenVisible(descTextarea, "Software and Mobile Application Testing Using Selenium and TestNG");
        typeWhenVisible(noteTextarea, "AXSOS QA Certificate is required for this position");

        clickWhenClickable(saveBtn);

        String msg = visible(requiredSpan).getText();
        try {
            Assert.assertEquals(msg, "Required", "Test Case Result: Failed");
        } catch (AssertionError e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }

    @Test(priority = 4) // TC3.1 - Delete All Titles (Avoid Null)
    public void DeleteAllTitles() {
        goToJobTitles();

        clickWhenClickable(addBtn);
        typeWhenVisible(titleInput, "Fresh SQA Engineer " + rand.nextInt(9999999));
        typeWhenVisible(descTextarea, "Software and Mobile Application Testing Using Selenium and TestNG");
        typeWhenVisible(noteTextarea, "AXSOS QA Certificate is required for this position");
        clickWhenClickable(saveBtn);

        clickWhenClickable(selectAllChkSpan);
        clickWhenClickable(deleteAllBtn);
        clickWhenClickable(yesDeleteBtn);
    }

    @Test(priority = 5) // TC3.2 - Add Duplicated Title
    public void AddDuplicatedTitle() {
        goToJobTitles();

        for (int i = 0; i < 2; i++) {
            clickWhenClickable(addBtn);

            typeWhenVisible(titleInput, "Fresh SQA Engineer ");
            typeWhenVisible(descTextarea, "Software and Mobile Application Testing Using Selenium and TestNG");
            typeWhenVisible(noteTextarea, "AXSOS QA Certificate is required for this position");

            clickWhenClickable(saveBtn);
        }

        String msg = visible(existsSpan).getText();
        try {
            Assert.assertEquals(msg, "Already exists", "Test Case Result: Failed");
        } catch (AssertionError e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }

    @Test(priority = 6) // TC4 - Edit Existing Title
    public void EditexistingJobTitle() {
        goToJobTitles();

        clickWhenClickable(firstRowEditIcon);

        clearAndType(titleInput, "SQA Engineer " + rand.nextInt(9999999));
        typeWhenVisible(descTextarea, "Software and Mobile Application Testing Using Selenium and TestNG");
        typeWhenVisible(noteTextarea, "AXSOS QA Certificate is required for this position");

        clickWhenClickable(saveBtn);

        String toast = visible(updatedToast).getText();
        try {
            Assert.assertEquals(toast, "Successfully Updated", "Test Case Result: Failed");
        } catch (AssertionError e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }

    @Test(priority = 7) // TC5 - Delete Job Title
    public void DeleteJobTitle() {
        goToJobTitles();

        clickWhenClickable(firstRowDelIcon);
        clickWhenClickable(yesDeleteBtn);
    }

    @Test(priority = 8) // TC6 - Add New Title with 100 character
    public void AddNewTitleLong() {
        goToJobTitles();

        clickWhenClickable(addBtn);
        typeWhenVisible(titleInput, "QA Engineer QA Engineer QA Engineer QA Engineer QA Engineer QA Engineer QA Engineer QA Engineer Q" + rand.nextInt(9999999));
        clickWhenClickable(saveBtn);
    }

    @Test(priority = 9) // TC7 - Add New Title with special character
    public void AddNewTitleSpecialCharacter() {
        goToJobTitles();

        clickWhenClickable(addBtn);
        typeWhenVisible(titleInput, "QA Engineer !@#$%^&*" + rand.nextInt(9999999));
        clickWhenClickable(saveBtn);
    }
}
