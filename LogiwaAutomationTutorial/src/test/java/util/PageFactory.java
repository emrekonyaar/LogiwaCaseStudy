package util;

import com.thoughtworks.gauge.BeforeScenario;
import driver.Driver;
import org.openqa.selenium.WebDriver;
import pages.*;

public class PageFactory extends org.openqa.selenium.support.PageFactory {
    public static WebDriver driver;
    public static HomePage homePage;
    public static CustomerLoginPage customerLoginPage;
    public static AccountPage accountPage;
    public static SearchPage searchPage;
    public static CategoryPage categoryPage;
    public static ProductPage productPage;
    public static BasketPage basketPage;

    @BeforeScenario
    public void init() {
        driver = Driver.webDriver;

        homePage = PageFactory.initElements(driver, HomePage.class);
        customerLoginPage = PageFactory.initElements(driver, CustomerLoginPage.class);
        accountPage = PageFactory.initElements(driver, AccountPage.class);
        searchPage = PageFactory.initElements(driver, SearchPage.class);
        categoryPage = PageFactory.initElements(driver, CategoryPage.class);
        productPage = PageFactory.initElements(driver, ProductPage.class);
        basketPage = PageFactory.initElements(driver, BasketPage.class);
    }

}
