package steps;

import com.thoughtworks.gauge.Step;
import util.PageFactory;
import pages.SearchPage;

public class SearchPageSteps {

    SearchPage searchPage;

    public SearchPageSteps() {
        searchPage = PageFactory.searchPage;
    }

    @Step("Check search <search>")
    public void checkSearchInputOption(String search) {
        searchPage.checkSearchInput(search);
    }

    @Step("Control no result")
    public void controlNoResult() {
        searchPage.controlNoResult();
    }
}
