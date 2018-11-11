package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//*[@id='login-email']")
    private WebElement userEmailField;

    @FindBy(xpath = "//*[@id='login-password']")
    private WebElement userPasswordField;

    @FindBy(xpath = "//*[@id = 'login-submit']")
    private WebElement signInButton;

    @FindBy(className= "link-forgot-password")
    private WebElement buttonForgotPasword;

    /**
     * @param webDriver
     * Method which initiate web driver in this class and initiate web elements
     */
    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Method which test is page loaded or not
     */
    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/")
                && webDriver.getTitle().equals("LinkedIn: Войти или зарегистрироваться")
                && isSignInButtonDisplayed();
    }

    /**
     * Method which show signInButton is visible
     */
    public boolean isSignInButtonDisplayed() {
        return signInButton.isDisplayed();
    }

    /**
     * @param userEmail
     * @param userPassword
     * @param <T>
     * Method which enter to input email, password and click on submit button and login into Linkedin
     */
    public <T> T login(String userEmail, String userPassword){
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();

        if (webDriver.getCurrentUrl().contains("/feed")) {
            return (T) new HomePage(webDriver);
        }
        if (webDriver.getCurrentUrl().contains("uas/login-submit")) {
            return (T) new SubmitPage(webDriver);
        } else {
            return (T) new LoginPage(webDriver);
        }
    }

    /**
     * Method which click on ForgotPassword button
     */
    public RequestPasswordResetPage clickOnForgotPasswordButton() {
        buttonForgotPasword.click();
        return new RequestPasswordResetPage(webDriver);
    }
}

