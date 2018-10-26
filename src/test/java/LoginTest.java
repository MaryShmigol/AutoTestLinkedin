import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LoginTest {
    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new FirefoxDriver();
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }


    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                { "klymenkosergey87@gmail.com", "vera228606" },
                {"KLYMENKOsergey87@gmail.com","vera228606"},
                {"  klymenkosergey87@gmail.com  ","vera228606"},
                };
    }

    /**
     * PreConditions:
     * - Open FF browser.
     * <p>
     * Scenario:
     * - Navigate to https://linkedin.com.
     * - Verify that Login page is loaded.
     * - Enter userEmail into userEmail field.
     * - Enter userPassword into userPassword field.
     * - Click on signIn button.
     * - Verify that Home page is loaded.
     * PostCondition:
     * - Close FF browser.
     */
    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPassword) throws InterruptedException {
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        HomePage homePage = loginPage.login(userEmail, userPassword);
        sleep(3000);
        Assert.assertTrue(homePage.isHomePageLoaded(),
                "profile NavItem is not displayed on Login Page");
    }

    @Test
    public void negativeLoginTestEmailWithoutAtTest(){
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        LoginSubmit loginSubmit = loginPage.login("klymenkosergey87gmail.com", "vera228606");
        Assert.assertTrue(loginSubmit.isErrorPageLoaded(), "profile NavItem is not displayed on Login Page");
    }
    @Test
    public void negativeRegistryPasswordTest(){
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        LoginSubmit loginSubmit  = loginPage.login( "klymenkosergey87@gmail.com", "VERA228606");
        Assert.assertTrue(loginSubmit.isErrorPageLoaded(),"profile NavItem is not displayed on Login Page");
    }

    @Test
    public void negativeLoginTestWrongPasswordEmailTest(){
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        LoginSubmit loginSubmit = loginPage.login( "klyme@gmail.com", "8606");
        Assert.assertTrue(loginSubmit.isErrorPageLoaded(),"profile NavItem is not displayed on Login Page");
    }

    @Test
    public void  negativeLoginTestWrongLoginTest(){
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        LoginSubmit loginSubmit = loginPage.login( "klyme@gmail.com", "vera228606");
        Assert.assertTrue(loginSubmit.isErrorPageLoaded(),"profile NavItem is not displayed on Login Page");
    }

    @Test
    public void negativeLoginTestWrongPasswordTest(){
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        LoginSubmit loginSubmit =loginPage.login( "klymenkosergey87@gmail.com", "vera5568606");
        Assert.assertTrue(loginSubmit.isErrorPageLoaded(),"profile NavItem is not displayed on Login Page");
    }

    @Test
    public void negativeLoginTestEmptyLoginTest(){
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        LoginPage loginPage3 = loginPage.login( "", "vera228606");
        Assert.assertTrue(loginPage3.isPageLoaded(),"profile NavItem is not displayed on Login Page");
    }
    @Test
    public void negativeLoginWithEmptyPasswordTest(){
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        LoginPage loginPageEmptyPassword = loginPage.login("a@b.c", "");
        Assert.assertTrue(loginPageEmptyPassword.isPageLoaded(),"profile NavItem is not displayed on Login Page");
    }
}

