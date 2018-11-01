import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class SearchTest {
    WebDriver webDriver;
    LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {

        webDriver = new FirefoxDriver();
        webDriver.get("https://linkedin.com");
        loginPage = new LoginPage(webDriver);
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

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
    public void basicSearchTest() {
        String searchTerm = "hr";

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        HomePage homePage = loginPage.login("klymenkosergey87@gmail.com", "vera228606");
        Assert.assertTrue(homePage.isHomePageLoaded(),
                "profile NavItem is not displayed on Login Page");

        SearchPage searchPage = homePage.searchByTerm(searchTerm);
        Assert.assertTrue(searchPage.isSearchPageLoaded(), "Search page is not loaded");


        List<String> searchResultList = searchPage.getSearchResults();
        Assert.assertEquals(searchResultList.size(), 10, "Number of results is wrong");

        for (String searchResult : searchResultList) {
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()),
                    "Searchterm "+searchResult+ " not found"+  searchResult);
        }
    }
}
