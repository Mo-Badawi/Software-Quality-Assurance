package base;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {
    protected AndroidDriver<MobileElement> driver;
    protected WebDriverWait wait;
    
    private static final boolean USE_WD_HUB = true;
    private static final String SERVER = USE_WD_HUB ? "http://127.0.0.1:4723"
                                                    : "http://127.0.0.1:4723/wd/hub";

    @SuppressWarnings("deprecation")
	@BeforeClass
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability("udid", "emulator-5554");
        caps.setCapability("deviceName", "sdk_gphone64_x86_64");
        caps.setCapability("appPackage", "org.tasks");
        caps.setCapability("appActivity", "com.todoroo.astrid.activity.TaskListActivity");
        caps.setCapability("unicodeKeyboard", true);
        caps.setCapability("resetKeyboard", true);
        caps.setCapability("noReset", false);
        
        driver = new AndroidDriver<MobileElement>(new URL(SERVER), caps);
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 20);

        // Example first click
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                MobileBy.AndroidUIAutomator("new UiSelector().text(\"Continue without sync\")")
            )
        ).click();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
