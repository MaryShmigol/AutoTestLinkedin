package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

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
     * Method which enter to input email, password and click on submit button and login into Linkedin
     * @param userEmail-String with user Email
     * @param userPassword - String with user Password
     * @param <T> - generic type to cat different page object
     * @return - either Home Page one of the most page
     */
    public <T> T login(String userEmail, String userPassword){
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();

        if (webDriver.getCurrentUrl().contains("/feed")) {
            return (T) new HomePage(webDriver);
        }
        if (webDriver.getCurrentUrl().contains("https://www.linkedin.com/")) {
            return (T) new LoginPage(webDriver);
        } else {
            return (T) new SubmitPage(webDriver);
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

