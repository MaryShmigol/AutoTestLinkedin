import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class RequestPasswordTest {
    private WebDriver webDriver;
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
    @Test
    public void RequestPassword() throws InterruptedException {
        String userEmail = "klymenkosergey87@gmail.com";

        String newPassword = "hello123456";
        String confirmNewPassword = "hello123456";

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");

        EnterEmailPage enterEmailPage = loginPage.clickOnForgotPasswordButton();
        Assert.assertTrue(enterEmailPage.isEnterEmailPageLoaded(),"Request Page is not loaded");

       PasswordOnYourEmailPage passwordOnYourEmailPage = enterEmailPage.enterEmailOnField(userEmail);
       Assert.assertTrue(passwordOnYourEmailPage.isPasswordOnYourEmailPageLoaded(),"PasswordOnYourEmailPage was not loaded");

       ChangePasswordPage changePasswordPage = passwordOnYourEmailPage.navigateToLinkFromEmail();
       sleep(15000);
       Assert.assertTrue(changePasswordPage.isChangePasswordPageLoaded(), "ChangePasswordPage was not loaded");

       PasswordWasChangePage passwordWasChangePage = changePasswordPage.changePassword(newPassword,confirmNewPassword);






    }
}


