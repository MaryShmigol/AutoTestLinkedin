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


    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                { "klymenkosergey87@gmail.com", "vera228606" },
                {"KLYMENKOsergey87@gmail.com","vera228606"},
                {"  klymenkosergey87@gmail.com  ","vera228606"},
                };
    }
    @DataProvider
    public Object[][] validationMessagesCombination(){
        return new Object[][]{
                {"klymenkofsdfsergey8@7gmail.com", "vera228606", "",""},
                {"klymenkosergey87@gmail.com", "86ewweewww06", "", ""},
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
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        HomePage homePage = loginPage.login(userEmail, userPassword); //пример для перехода на страницу
        sleep(3000);
        Assert.assertTrue(homePage.isHomePageLoaded(),
                "profile NavItem is not displayed on Login Page");
    }

    @Test(dataProvider = "validationMessagesCombination")
    public void validMessageOnInvalidEmailPasswordTest (String userEmail,
                                                        String userPassword,
                                                        String emailValidationMessage,
                                                        String passwordValidationMessage)
    {

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");

        SubmitPage loginSubmit = loginPage.login(userEmail, userPassword );
        Assert.assertTrue(loginSubmit.isErrorPageLoaded(), "profile NavItem is not displayed on Login Page");
        Assert.assertEquals(loginSubmit.getAlertMessageText(),
                "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.",
                "Alert message text i wrong");
        Assert.assertEquals(loginSubmit.getEmailValidationMessage(), emailValidationMessage,
                "Email validation message is wrong");
        Assert.assertEquals(loginSubmit.getPasswordValidationMessage(), passwordValidationMessage,
                "Password validation message is wrong");
    }

    @Test(dataProvider = "negativeTestToLoginPage")
    public void negativeTestLoginPage(String userEmail, String userPassword){
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
        LoginPage loginPageTest = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(loginPageTest.isPageLoaded(),"profile NavItem is not displayed on Login Page");
    }
}

