package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SetNewPasswordPage extends BasePage{

    @FindBy(id= "newPasword")
    private WebElement newPassword;

    @FindBy(id= "confirmPassword")
    private WebElement confirmPassword;

    @FindBy(id = "reset-password-submit-button")
    private WebElement resetPassword;

    /**
     * @param webDriver
     * Method which initiate web driver in this class and initiate web elements
     */
    public SetNewPasswordPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * @return
     * Method which test is page loaded or not
     */
    public boolean isPageLoaded() {
        return  webDriver.getCurrentUrl().contains("checkpoint/rp/password-reset")
                && webDriver.getTitle().contains("Изменить пароль | LinkedIn")
                && isResetPasswordOnDisplayed();
    }

    /**
     * @return
     * Method which show Reset Password Button is visible
     */
    private boolean isResetPasswordOnDisplayed() {
        return resetPassword.isDisplayed();
    }

    /**
     * @param newUserPassword
     * @return
     * Method which enter to input new password,  confirm password and click on  reset  button
     */
    public SuccessfulPasswordResetPage submitNewPassword(String newUserPassword){
        waitUntilElementIsClickable(newPassword, 5);
        newPassword.sendKeys(newUserPassword);
        confirmPassword.sendKeys(newUserPassword);
        resetPassword.click();
        return new SuccessfulPasswordResetPage(webDriver);
    }

}
