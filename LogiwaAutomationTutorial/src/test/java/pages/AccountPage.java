package pages;

import common.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AccountPage extends BasePage {

    @FindBy(css = "[class='panel wrapper'] [class='greet welcome']")
    public WebElement welcomeUsername;

    public void checkLoginSuccessed() {
        Assert.assertTrue(isDisplayed(welcomeUsername));;
    }
}
