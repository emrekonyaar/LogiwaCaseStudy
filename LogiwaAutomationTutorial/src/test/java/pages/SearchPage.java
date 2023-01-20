package pages;

import common.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class SearchPage extends BasePage {

    @FindBy(css = "[class='page-title']")
    public WebElement pageTitle;

    @FindBy(css = "[class='message notice'] div")
    public WebElement messageNotice;

    public void checkSearchInput(String search) {
        Assert.assertTrue(getText(pageTitle).contains(search));
    }

    public void controlNoResult() {
        Assert.assertTrue(isDisplayed(messageNotice));
    }
}
