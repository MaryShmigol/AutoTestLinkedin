import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {
    /**
     *precondition
     * open ff browsers
     * Scenario
     * Navigate to linkedin.com
     * Verify that login page is load
     * enter user and mail in to user email field
     * enter user password in to user password field
     * click on sign in button
     * verify that home page is load
     *
     * postcondition
     * close browser
     */
    @Test
    public void succsessfulLoginTest(){
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com/");
        Assert.assertEquals(webDriver.getCurrentUrl(),"https://www.linkedin.com/", "home page url is wrong");




        webDriver.quit();
    }
}
