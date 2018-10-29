import org.openqa.selenium.By;
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
    @DataProvider
    public Object[][] negativeTestLoginSubmit(){
        return new Object[][]{
                {"klymenkofsdfsergey8@7gmail.com", "vera228606",
                "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.",
                        "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля."},
                {"klymenkosergey87@gmail.com", "8606",
                        " Это неверный пароль. Повторите попытку или ",
                        "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля."},
                {"klymenkosREWRergey87@gmail.com","sfdf",
                        "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.",
                        "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля."}

                 };
    }
    @DataProvider
    public Object[][] negativeTestToLoginPage(){
        return new Object[][]{
                {"","vera228606"},
                {"klymenkosergey87@gmail.com",""},
                {"",""},
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
    public void successfulLoginTest(String userEmail, String userPassword)
            throws InterruptedException {
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        HomePage homePage = loginPage.login(userEmail, userPassword);
        sleep(3000);
        Assert.assertTrue(homePage.isHomePageLoaded(),
                "profile NavItem is not displayed on Login Page");
    }

    @Test(dataProvider = "negativeTestLoginSubmit")
    public void negativeTestToSubmitPage (String userEmail, String userPassword,
                                                    String fieldsErrorMessage,
                                                    String generalAlertMessage)
    {
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        LoginSubmit loginSubmit = loginPage.login(userEmail, userPassword );
        Assert.assertTrue(loginSubmit.isErrorPageLoaded(), "profile NavItem is not displayed on Login Page");
        webDriver.getPageSource().contains(fieldsErrorMessage);
        webDriver.getPageSource().contains(generalAlertMessage);
    }
    @Test(dataProvider = "negativeTestToLoginPage")
    public void negativeTestLoginPage(String userEmail, String userPassword){
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        LoginPage loginPageTest = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(loginPageTest.isPageLoaded(),"profile NavItem is not displayed on Login Page");
    }

}

