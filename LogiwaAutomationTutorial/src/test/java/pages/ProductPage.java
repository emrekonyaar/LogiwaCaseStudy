package pages;

import com.thoughtworks.gauge.datastore.SpecDataStore;
import common.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {

    public static List<String> productList = new ArrayList<>();
    @FindBy(css = "[class='swatch-option text']")
    public List<WebElement> productSize;

    @FindBy(css = "[class='swatch-option color']")
    public List<WebElement> productColor;

    @FindBy(id = "product-addtocart-button")
    public WebElement productAddToCart;

    @FindBy(css = "[class='page-title'] span")
    public WebElement productName;
    public void selectSize() {
        click(productSize.get(0));
    }

    public void selectColor() {
        click(productColor.get(0));
    }

    public void clickAddToCart() {
        click(productAddToCart);

        productList.add(getText(productName));
        System.out.println(productList);

    }

    public void checkPage() {
        Assert.assertTrue(isDisplayed(productAddToCart));
    }
}
