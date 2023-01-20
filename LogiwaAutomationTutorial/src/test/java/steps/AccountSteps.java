package steps;

import com.thoughtworks.gauge.Step;
import util.PageFactory;
import pages.AccountPage;

public class AccountSteps {

    AccountPage accountPage;

    public AccountSteps() {
        accountPage = PageFactory.accountPage;
    }

    @Step("Check login successed")
    public void checkLoginSuccessed() {
        accountPage.checkLoginSuccessed();
    }
}
