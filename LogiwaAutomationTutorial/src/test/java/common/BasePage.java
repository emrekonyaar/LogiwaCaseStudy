package common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import util.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class BasePage {
    private final static int TIMEOUT = 20;
    protected static String Url = System.getenv("APP_URL");
    public WebDriver driver;


    public BasePage() {
        this.driver = PageFactory.driver;
    }

    public void openHomePage() {
        driver.get((Url));

    }

    public void sendKeys(WebElement element, String text) {
        try {
            centerElement(waitUntilElementIsVisible(element, System.currentTimeMillis())).sendKeys(text);
            //Assert.assertEquals(element.getText(),value);
            System.out.println("It was seen that the value was written to the " + element);
        } catch (ElementNotFoundException e) {
            Assert.fail(e.getMessage());
        }
    }

    public WebElement centerElement(WebElement element) {

        String scrollScript = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        ((JavascriptExecutor) driver).executeScript(scrollScript, element);

        waitFor(1);

        return element;
    }

    public void waitFor(double duration) {
        try {
            Thread.sleep((long) (duration * 1000L));
        } catch (InterruptedException exception) {
            Assert.fail(exception.getLocalizedMessage());
        }
    }

    public WebElement waitUntilElementIsVisible(WebElement element, long startTime) {
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        if ((System.currentTimeMillis() - startTime) > 10000) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return null;
        }
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            System.out.println(element + "element is visible");
            return element;
        } catch (StaleElementReferenceException | TimeoutException e) {
            return waitUntilElementIsVisible(element, startTime);
        }
    }

    public void click(WebElement element) {
        try {
            centerElement(waitUntilElementIsClickable(element, System.currentTimeMillis())).click();
            System.out.println(element + " click done.");
        } catch (ElementNotFoundException e) {
            Assert.fail(e.getMessage());
        }
    }

    public WebElement waitUntilElementIsClickable(WebElement element, long startTime) {
        WebDriver subDriver = driver;
        subDriver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        if ((System.currentTimeMillis() - startTime) > 15000) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return null;
        }
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            System.out.println(element + "element is clickable");
            return element;
        } catch (StaleElementReferenceException | ElementClickInterceptedException | TimeoutException e) {
            System.out.println(e.getMessage());
            return waitUntilElementIsClickable(element, startTime);
        }
    }
    public String getText(WebElement element) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public String getAttribute(WebElement element, String attribute) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.visibilityOf(element));
        return element.getAttribute(attribute);
    }

    public boolean isSelected(WebElement element, String attribute) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(element));
        return element.isSelected();
    }

    public boolean isDisplayed(WebElement element) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOf(element));
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void mouseHover(WebElement element){
        waitFor(1);
        new Actions(driver)
                .moveToElement(element)
                .perform();
    }
}
