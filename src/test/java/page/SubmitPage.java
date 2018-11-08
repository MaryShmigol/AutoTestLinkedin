package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubmitPage {
    private WebDriver webDriver;

    @FindBy (xpath = "//*[@id= 'session_key-login-error']")
    private WebElement emailValidationMessage;

    @FindBy (xpath = "//*[@id = 'session_password-login-error']")
    private WebElement passwordValidationMessage;

    @FindBy(xpath = "//div[@role='alert']")
    private WebElement alertBox;

    public SubmitPage(WebDriver webDriver){
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver, this);
    }
    public boolean isPageLoaded(){
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME")
                && webDriver.getTitle().contains("Войти в LinkedIn")
                && isErrorMessageOnDisplayed();
    }
    public boolean isErrorMessageOnDisplayed(){
        return alertBox.isDisplayed();
    }
    public String getAlertMessageText() {
        return alertBox.getText();
    }
    public String getEmailValidationMessage() {
        return emailValidationMessage.getText();
    }
    public String getPasswordValidationMessage() {
        return passwordValidationMessage.getText();
    }
}

