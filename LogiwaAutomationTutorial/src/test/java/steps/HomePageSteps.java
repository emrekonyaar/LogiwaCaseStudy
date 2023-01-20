package steps;

import com.thoughtworks.gauge.Step;
import util.PageFactory;
import pages.HomePage;

public class HomePageSteps {

    HomePage homePage;

    public HomePageSteps() {
        homePage = PageFactory.homePage;
    }

    @Step("Click Sign in button on the home page")
    public void clickSignInButton() {
        homePage.clickSignInButton();
    }

    @Step("Search <iphone>")
    public void searchInput(String search) {
        homePage.search(search);
    }


    @Step("Click Menu item <category>")
    public void clickMenuItem(String category) {
        homePage.clickMenuItem(category);
    }


    @Step("Click Random Menu item")
    public void clickRandomMenuItem() {
        homePage.clickRandomMenuItem();
    }

    @Step("Click to basket")
    public void clickToBasket() {
        homePage.clickToBasket();
    }

    @Step("Open homepage")
    public void openHomePage() {
        homePage.openHomePage();
    }

    @Step("Check Home Page")
    public void checkHomePage() {
        homePage.pageCheck();
    }
}
