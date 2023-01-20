package steps;

import com.thoughtworks.gauge.Step;
import util.PageFactory;
import pages.ProductPage;

public class ProductPageSteps {

    ProductPage productPage;

    public ProductPageSteps() {
        productPage = PageFactory.productPage;
    }

    @Step("Click Add to cart")
    public void clickAddToCart() {
        productPage.selectSize();
        productPage.selectColor();
        productPage.clickAddToCart();
    }

    @Step("Check Product Page")
    public void checkProductPage() {
        productPage.checkPage();
    }
}
