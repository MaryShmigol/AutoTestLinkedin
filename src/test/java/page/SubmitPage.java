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

    /**
     * @param webDriver
     * Method which initiate web driver in this class and initiate web elements
     */
    public SubmitPage(WebDriver webDriver){
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * @return
     * Method which test is page loaded or not
     */
    public boolean isPageLoaded(){
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME")
                && webDriver.getTitle().contains("Войти в LinkedIn")
                && isErrorMessageOnDisplayed();
    }

    /**
     * @return
     * Method which show error message on display or not
     */
    public boolean isErrorMessageOnDisplayed(){
        return alertBox.isDisplayed();
    }

    /**
     * @return
     * Method which pull notification by web element alertBox
     */
    public String getAlertMessageText() {
        return alertBox.getText();
    }

    /**
     * @return
     * Method which pull notification by web element emailValidationMessage
     */
    public String getEmailValidationMessage() {
        return emailValidationMessage.getText();
    }

    /**
     * @return
     * Method which pull notification by passwordValidationMessage
     */
    public String getPasswordValidationMessage() {
        return passwordValidationMessage.getText();
    }
}

