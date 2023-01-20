package pages;

import common.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerLoginPage extends BasePage {

    @FindBy(css = "[class='page-title']")
    public WebElement pageTitle;

    @FindBy(id = "send2")
    public WebElement signInButton;
    @FindBy(id = "email")
    public WebElement emailInput;

    @FindBy(id = "pass")
    public WebElement passwordInput;

    @FindBy(css = "[id*='error']")
    public WebElement errorMessages;

    @FindBy(css = "[data-ui-id='message-error'] div ")
    public WebElement pageErrorMessage;

    public void checkCustomerLoginPage() {
        Assert.assertEquals("Customer Login", getText(pageTitle));
    }

    public void clickSignInButton() {
        click(signInButton);
    }

    public void setEmail(String email) {
        sendKeys(emailInput, email);
    }

    public void setPassword(String password) {
        sendKeys(passwordInput, password);
    }

    public void checkError(String error) {
        assertThat(getText(errorMessages)).isEqualTo(error);
    }

    public void checkPasswordIsWrongErrorMessage() {
        Assert.assertEquals("The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later." , getText(pageErrorMessage));
    }
}
