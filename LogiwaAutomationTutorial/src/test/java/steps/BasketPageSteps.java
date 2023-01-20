package steps;

import com.thoughtworks.gauge.Step;
import util.PageFactory;
import pages.BasketPage;

public class BasketPageSteps {

    BasketPage basketPage;

    public BasketPageSteps() {
        basketPage = PageFactory.basketPage;
    }

    @Step("Verify basket")
    public void verifyBasket() {
        basketPage.verifyBasket();
    }

    @Step("Check Basket Page")
    public void checkBasketPage() {
        basketPage.checkPage();
    }
}
