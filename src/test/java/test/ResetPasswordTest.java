package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;
import static java.lang.Thread.sleep;

public class ResetPasswordTest extends BaseTest{

    @Test
    public void successfulPasswordResetTest() throws InterruptedException {
        String userEmail = "uu08474@gmail.com";
        String newUserPassword = "hello2222";

        RequestPasswordResetPage requestPasswordResetPage = loginPage.clickOnForgotPasswordButton();
        Assert.assertTrue(requestPasswordResetPage.isPageLoaded(), "RequestPasswordResetPage is not loaded");

        RequestPasswordResetSubmitPage requestPasswordResetSubmitPage = requestPasswordResetPage.submitUserEmail(userEmail);
        Assert.assertTrue(requestPasswordResetSubmitPage.isPageLoaded(), "SetNewPasswordPage is not loaded");
        sleep(9000);
        SetNewPasswordPage setNewPasswordPage = requestPasswordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(setNewPasswordPage.isPageLoaded(), "SetNewPasswordPage was not loaded");

        SuccessfulPasswordResetPage successfulPasswordResetPage = setNewPasswordPage.submitNewPassword(newUserPassword);
        Assert.assertTrue(successfulPasswordResetPage.isPageLoaded(),"SuccessfulPasswordResetPage is not loaded");

        HomePage homePage = successfulPasswordResetPage.clickOnGoToHomeButton();
        Assert.assertTrue(homePage.isPageLoaded(),"HomePage is not loaded");
    }
}
