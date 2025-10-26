package BasePackage;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.*;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0)); // explicit waits only
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

            typeWhenVisible(By.name("username"), "Admin");
            typeWhenVisible(By.name("password"), "admin123");
            clickWhenClickable(By.cssSelector("button[type='submit']"));

            wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("header.oxd-topbar")),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("aside.oxd-sidepanel"))
            ));
        } catch (Throwable t) {
            t.printStackTrace();
            throw new SkipException("BaseTest @BeforeClass failed: " + t.getMessage());
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        try { if (driver != null) driver.quit(); } catch (Throwable ignored) {}
    }
    
    protected WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement clickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void clickWhenClickable(By locator) {
        clickable(locator).click();
    }

    protected void typeWhenVisible(By locator, String text) {
        WebElement e = visible(locator);
        e.clear();
        e.sendKeys(text);
    }

    protected void clearAndType(By locator, String text) {
        WebElement e = visible(locator);
        e.sendKeys(Keys.CONTROL + "a");
        e.sendKeys(Keys.DELETE);
        e.sendKeys(text);
    }
}
