package DeleteComplete_Test;
import base.TestBase;
import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;

public class TS3_DeleteTask extends TestBase{
	public int counter;
	protected String AddTaskLocator = "org.tasks:id/fab";
	protected String TaskNameLocator = "new UiSelector().text(\"Task name\")";
	protected String SaveButtonLocator = "new UiSelector().className(\"android.widget.Button\").instance(1)";
	protected String TaskLocator0 = "new UiSelector().resourceId(\"org.tasks:id/row\").instance(0)";
	protected String MoreOptionLocator = "new UiSelector().description(\"More options\").instance(2)";
	protected String DeleteOptionLocator = "new UiSelector().text(\"Delete\")";
	protected String OkButtonLocator = "android:id/button1";
	protected String CancelButtonLocator = "android:id/button2";
	protected String TaskClickLocator = "org.tasks:id/rowBody";
	protected String DeleteTaskIcon = "Delete task";
	protected String DefaultListLocator = "new UiSelector().text(\"Default list\").instance(0)";
	protected String MainMenuLocator = "new UiSelector().className(\"android.widget.ImageButton\").instance(0)";
	protected String SelectAllLocator = "new UiSelector().text(\"Select all\")";
	protected String CompleteBoxLocator = "new UiSelector().resourceId(\"org.tasks:id/completeBox\").instance(0)";
	protected String CompletedListLocator = "new UiSelector().text(\"Completed\")";
	protected String MoreOptinBottomLocator = "new UiSelector().description(\"More options\").instance(1)";
	protected String MoreOptionBottomChooseLocator = "new UiSelector().text(\"Clear Completed\")";
	protected String SearchIconLocator = "Search";
	protected String SearchFieldClickLocator = "org.tasks:id/search_src_text";
	protected String SearchFieldLocator = "org.tasks:id/search_src_text";
	protected String AddLocationLocator = "new UiSelector().text(\"Add location\")";
	protected String SelectLocationLocator = "org.tasks:id/select_this_location";
	
	public void AddTaskButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.id(AddTaskLocator))).click();
	}
	public void TaskTitleAdd() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator(TaskNameLocator))).sendKeys("This is Demo Task");
	}
	public void AddLocation() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator(AddLocationLocator))).click();
	}
	public void SelectLocation() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.id(SelectLocationLocator))).click();
	}
	public void SaveButtonClick() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator(SaveButtonLocator))).click();
	}
	public void DefaultList() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator(DefaultListLocator))).click();
	}
	public void Default() {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator(MainMenuLocator))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("all_inbox"))).click();
        DefaultList();
	}
	public void LongPress(String Locator) {
    	WebElement element = driver.findElement(MobileBy.AndroidUIAutomator(Locator));  
    	
    	PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence LongPress = new Sequence(finger, 1);
        LongPress.addAction(finger.createPointerMove(Duration.ofMillis(0),
                    PointerInput.Origin.fromElement(element), 0, 0));
        LongPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        LongPress.addAction(new org.openqa.selenium.interactions.Pause(finger, Duration.ofSeconds(2)));
        LongPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(LongPress));
	}
	public void MoreOption() {
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator(MoreOptionLocator))).click();
	}
	public void DeleteOption() {
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator(DeleteOptionLocator))).click();
	}
	public void OkButton() {
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id(OkButtonLocator))).click();
	}
	public void CancelButton() {
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id(CancelButtonLocator))).click();
	}
	public void TaskClick() {
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id(TaskClickLocator))).click();
	}
	public void DeleteTaskByIcon() {
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId(DeleteTaskIcon))).click();
	}
	public void SelectAll() {
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator(SelectAllLocator))).click();
	}
	public void MarkComplete() {
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator(CompleteBoxLocator))).click();
	}
	public void OpenComplete() {
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator(CompletedListLocator))).click();
	}
	public void MoreOptionBottom() {
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator(MoreOptinBottomLocator))).click();
	}
	public void MoreOptionBottomChooseClick() {
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator(MoreOptionBottomChooseLocator))).click();
	}
	public void SearchIconClick() {
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId(SearchIconLocator))).click();
	    wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id(SearchFieldClickLocator))).click();
	}
	public void SearchFieldFill() {
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id(SearchFieldLocator))).sendKeys("Demo Task: 2");
	}
	
	
    @Test(priority = 1)
    public void AddDemoTasks() {
    	
    	for(counter = 0; counter < 3; counter++) {	
    		AddTaskButton();   	
    		wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator(TaskNameLocator))).sendKeys("Demo Task: " + counter);
    		SaveButtonClick();
        	}
    	}
    
    @Test(priority = 2)
    public void DeleteTask() {
    	//Default();
    	LongPress(TaskLocator0); 
    	MoreOption();
    	DeleteOption();
    	OkButton();
        }
    	
    @Test(priority = 3)
    public void CancelDeleteTask() {
    	//Default();
    	LongPress(TaskLocator0); 
    	MoreOption();
    	DeleteOption();
    	CancelButton();
    	}
    
    @Test(priority = 4)
    public void DeleteFromTask() throws InterruptedException {
	   Thread.sleep(1000);
    	//Default();
	   	TaskClick();
    	TaskClick();
    	DeleteTaskByIcon();
    	OkButton();
    }
   
    @Test(priority = 5)
    public void DeleteMultipleTasks() {
	   AddDemoTasks();
	   DefaultList();
	   LongPress(TaskLocator0);
	   
	   for (int counter1 = 1; counter1 <= counter; counter1++) {
		   wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"org.tasks:id/rowBody\").instance("+counter1+")"))).click();
	   }
   		MoreOption();
   		DeleteOption();
   		OkButton();
	   
   }
    
    @Test(priority = 6)
    public void DeleteAllTask() {
 	   AddDemoTasks();
 	   DefaultList();
 	   LongPress(TaskLocator0);
 	   MoreOption();
 	   SelectAll();
 	   MoreOption();
 	   DeleteOption();
 	   OkButton();
    }
    
    @Test(priority = 7)
    public void ClearCompleteTask() {
  	   AddDemoTasks();
  	   MarkComplete();
  	   OpenComplete();
  	   MoreOptionBottom();
  	   MoreOptionBottomChooseClick();
  	   OkButton();	
    }
    
    @Test(priority = 8)
    public void DeleteMixedTask() {
    	AddDemoTasks();
    	MarkComplete();
    	MarkComplete();
    	LongPress(TaskLocator0);
  	    MoreOption();
  	    SelectAll();
  	    MoreOption();
  	    DeleteOption();
  	    OkButton();
    }
    
    @Test(priority = 9)
    public void DeleteFromSearch(){
    	AddDemoTasks();
    	SearchIconClick();
    	SearchFieldFill();
    	LongPress(TaskLocator0);
    	MoreOption();
    	DeleteOption();
  	    OkButton();
    }
   
}