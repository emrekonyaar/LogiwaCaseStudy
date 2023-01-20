package pages;

import common.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

public class CategoryPage extends BasePage {

    @FindBy(css = "[class='item'] a")
    public List<WebElement> columnMenuItems;
    @FindBy(css = "[class='sidebar sidebar-main']")
    public WebElement sideBar;

    @FindBy(css = "[class='breadcrumbs'] [class='items'] ")
    public WebElement breadCrumbs;

    @FindBy(css = "[class='product-image-photo'] ")
    public List<WebElement> productImage;

    public void selectMenuItem(String category) {
        for (WebElement columnItem : columnMenuItems) {
            if (getText(columnItem).equals(category)) {
                click(columnItem);
                break;
            }
        }
    }

    public void controlSelectItem(String category) {
        Assert.assertTrue(getText(breadCrumbs).contains(category));
    }

    public void clickRandomProduct() {
        Random rand = new Random();
        int random = rand.nextInt(productImage.size());
        click(productImage.get(random));
    }

    public void checkPage() {
        Assert.assertTrue(isDisplayed(sideBar));


    }
}
