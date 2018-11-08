package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.print.DocFlavor;

public class SetNewPasswordPage extends BasePage{

    @FindBy(id="newPasword")
    private WebElement newPassword;

    @FindBy(id="confirmPassword")
    private WebElement confirmPassword;

    @FindBy(id="reset-password-submiit-button")
    private WebElement resetPassword;

    public SetNewPasswordPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("checkpoint/rp/password-reset")
                && webDriver.getTitle().contains("Изменить пароль | LinkedIn")
                && isResetPasswordOnDisplayed();
    }

    private boolean isResetPasswordOnDisplayed() {
        return resetPassword.isDisplayed();
    }

    public SuccesfulPasswordResetPage submitNewPassword(String newUserPassword){

        newPassword.sendKeys(newUserPassword);
        confirmPassword.sendKeys(newUserPassword);
        resetPassword.click();
        return new SuccesfulPasswordResetPage(webDriver);
    }

}
