package test;
import org.testng.Assert;;
import org.testng.annotations.Test;
import page.HomePage;
import page.SearchPage;
import java.util.List;

public class SearchTest extends BaseTest{
    /**
     * Precondition:
     * -open new Browser.
     * -Navigate to linkedin.com.
     *
     * Scenario:
     * -Verify that Login Page loaded.
     * -login with valid credentials.
     * =Verify that Home page is loaded.
     * -Enter 'search term' enter search field and press RETURM  key.
     * -Verify that Search page is loaded.
     * -Verify 10 searchResult display on page.
     * -Verify each result item contains 'searchTerm'.
     *
     * PostCondition:
     * -close browser
     *
     */
    @Test
    public void basicSearchTest() throws InterruptedException {
        String searchTerm = "HR";
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        HomePage homePage = loginPage.login("uu08474@gmail.com", "hello228606");
        Assert.assertTrue(homePage.isPageLoaded(),
                "profile NavItem is not displayed on Login Page");

        SearchPage searchPage = homePage.search(searchTerm);
        Assert.assertTrue(searchPage.isPageLoaded(), "Login page is not loaded");
        Assert.assertEquals(searchPage.getSearchResultsCount(), 10, "Search results count is wrong");
        List<String> searchResultList = searchPage.getSearchResults();

        for (String searchResult : searchResultList){
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()),
                    "Search Term" + searchTerm + " not found"+ searchResult);
        }
    }
}
