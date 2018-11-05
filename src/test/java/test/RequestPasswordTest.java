package test;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;
import static java.lang.Thread.sleep;

public class RequestPasswordTest extends BaseTest{
    @Test
    public void RequestPassword() throws InterruptedException {
        String userEmail = "klymenkosergey87@gmail.com";
        String newPassword = "hello12345678";
        String confirmNewPassword = "hello12345678";

        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");

        EnterEmailPage enterEmailPage = loginPage.clickOnForgotPasswordButton();
        Assert.assertTrue(enterEmailPage.isPageLoaded(),"Request Page is not loaded");

       PasswordOnYourEmailPage passwordOnYourEmailPage = enterEmailPage.enterEmailOnField(userEmail);
       sleep(3000);
       Assert.assertTrue(passwordOnYourEmailPage.isPageLoaded(),
               "page.PasswordOnYourEmailPage was not loaded");

       sleep(50000);
       ChangePasswordPage changePasswordPage = passwordOnYourEmailPage.navigateToLinkFromEmail();
       Assert.assertTrue(changePasswordPage.isPageLoaded(), "page.ChangePasswordPage was not loaded");

       PasswordWasChangePage passwordWasChangePage = changePasswordPage.changePassword(newPassword,confirmNewPassword);
       Assert.assertTrue(passwordWasChangePage.isPageLoaded(), "Password was not change");
    }
}


