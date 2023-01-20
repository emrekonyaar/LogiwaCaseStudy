package steps;

import com.thoughtworks.gauge.Step;
import util.PageFactory;
import pages.CategoryPage;

public class CategoryPageSteps {

    CategoryPage categoryPage;

    public CategoryPageSteps() {
        categoryPage = PageFactory.categoryPage;
    }

    @Step("Select and control <menuitem>")
    public void selectAndControlMenuItem(String category) {
        categoryPage.selectMenuItem(category);
        categoryPage.controlSelectItem(category);
    }

    @Step("Click Random product")
    public void clickRandomProduct() {
        categoryPage.clickRandomProduct();
    }

    @Step("Check Category Page")
    public void checkCategoryPage() {
        categoryPage.checkPage();

    }
}
