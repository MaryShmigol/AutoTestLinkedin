package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

import static java.lang.Thread.sleep;

public class ResetPasswordTest extends BaseTest{

    @Test
    public void succesfulPasswordResetTest() throws InterruptedException {
        String userEmail = "uu08474@gmail.com";
        String newUserPassword = "hello228606";

        RequestPasswordResetPage requestPasswordResetPage = loginPage.clickOnForgotPasswordButton();
        Assert.assertTrue(requestPasswordResetPage.isPageLoaded(), "RequestPasswordResetPage is not loaded");

        RequestPasswordResetSubmitPage requestPasswordResetSubmitPage = requestPasswordResetPage.submitUserEmail(userEmail);
        Assert.assertTrue(requestPasswordResetSubmitPage.isPageLoaded(), "SetNewPasswordPage is not loaded");
        sleep(9000);
        SetNewPasswordPage setNewPasswordPage = requestPasswordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(setNewPasswordPage.isPageLoaded(), "SetNewPasswordPage was not loaded");

        SuccesfulPasswordResetPage succesfulPasswordResetPage = setNewPasswordPage.submitNewPassword(newUserPassword);
        Assert.assertTrue(succesfulPasswordResetPage.isPageLoaded(),"SuccessfulPasswordResetPage is not loaded");

        HomePage homePage = succesfulPasswordResetPage.clickOnGoToHomeButton();
        Assert.assertTrue(homePage.isPageLoaded(),"HomePage is not loaded");







    }
}
