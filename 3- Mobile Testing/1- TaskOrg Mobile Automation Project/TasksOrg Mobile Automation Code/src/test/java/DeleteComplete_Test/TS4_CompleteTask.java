package DeleteComplete_Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.TestBase;
import io.appium.java_client.MobileBy;
 
public class TS4_CompleteTask extends TestBase {
	
	    @Test(priority = 1)      //TC_01
	    public void testAddSimpleTask() {
	        String taskTitle = "project";
	        driver.findElement(By.id("org.tasks:id/fab")).click();
            driver.findElement(By.xpath("//android.widget.EditText[@text='Task name']")).sendKeys(taskTitle);
            driver.findElement(MobileBy.AccessibilityId("Save")).click();
            WebElement addedTask = driver.findElement(By.xpath("//android.widget.TextView[@text='" + taskTitle + "']"));

	        Assert.assertEquals(addedTask.getText(), taskTitle, "Task was not added correctly!");
	    }
	}