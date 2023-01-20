package pages;

import com.thoughtworks.gauge.Step;
import common.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class BasketPage extends BasePage {

    @FindBy(css = "[class='product-item-name']")
    public List<WebElement> productItemList;

    @FindBy(css = "[class='minicart-items']")
    public WebElement miniCartItems;
    @FindBy(css = "[class='title']")
    public WebElement title;

    public void verifyBasket() {
        if (isDisplayed(miniCartItems)) {
            click(title);
        }
        List<String> productItems = new ArrayList<>();
        for (WebElement productItem : productItemList) {
            productItems.add(getText(productItem));
        }
        Assert.assertEquals(productItems, ProductPage.productList);
    }


    public void checkPage() {
        Assert.assertTrue(isDisplayed(title));
    }
}
