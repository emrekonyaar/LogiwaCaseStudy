package steps;

import com.thoughtworks.gauge.Step;
import util.PageFactory;
import pages.CustomerLoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerLoginSteps {

    CustomerLoginPage customerLoginPage;

    public CustomerLoginSteps() {
        customerLoginPage = PageFactory.customerLoginPage;
    }

    @Step("Check Customer Login Page")
    public void checkCustomerLoginPage() {
        customerLoginPage.checkCustomerLoginPage();
    }

    @Step("Click Sign in button on the customer login page")
    public void clickSignInButton() {
        customerLoginPage.clickSignInButton();
    }

    @Step("Set e-mail to <email>")
    public void setEmail(String email) {
        customerLoginPage.setEmail(email);
    }

    @Step("Set password to <password>")
    public void setPassword(String password) {
        customerLoginPage.setPassword(password);
    }

    @Step("Check error is <error>")
    public void checkError(String error) {
        customerLoginPage.checkError(error);
    }

    @Step("Check password is wrong error message")
    public void checkPasswordIsWrongErrorMessage() {
        customerLoginPage.checkPasswordIsWrongErrorMessage();
    }
}
