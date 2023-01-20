package pages;

import com.thoughtworks.gauge.Step;
import common.BasePage;
import models.enums.MenuItemEnum;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HomePage extends BasePage {


    @FindBy(css = "[class='message global demo'] [class='content']")
    public WebElement globalDemoMessageContent;

    @FindBy(css = "[class='panel header'] [class='authorization-link']")
    public WebElement signInButton;

    @FindBy(id = "search")
    public WebElement searchInput;

    @FindBy(id = "top-cart-btn-checkout")
    public WebElement topCartBtnCheckout;

    @FindBy(css = "[class='action showcart']")
    public WebElement basketIcon;

    @FindBy(css = "[role='menu'] [role='presentation'] [class='level-top ui-corner-all'] span")
    public List<WebElement> menuItems;

    public void pageCheck() {
        Assert.assertEquals("This is a demo store. No orders will be fulfilled.", getText(globalDemoMessageContent));
    }

    public void clickSignInButton() {
        click(signInButton);
    }

    public void search(String search) {
        sendKeys(searchInput, search);
        searchInput.sendKeys(Keys.ENTER);
    }

    public void clickMenuItem(String category) {
        for (WebElement menuitem : menuItems) {
            if (getText(menuitem).equals(category)) {
                click(menuitem);
                break;
            }
        }
    }

    public void clickRandomMenuItem() {
        Random rand = new Random();
        int random = rand.nextInt(MenuItemEnum.values().length - 1);
        Object[] randomMenuItem = Arrays.stream(MenuItemEnum.values()).toArray();

        for (WebElement menuitem : menuItems) {
            if (getText(menuitem).equals(randomMenuItem[random].toString())) {
                click(menuitem);
                break;
            }
        }
    }


    public void clickToBasket() {
        click(basketIcon);
        click(topCartBtnCheckout);
    }


}
