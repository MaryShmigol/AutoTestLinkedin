import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSubmit {
    private WebDriver webDriver;

    @FindBy (xpath = "//*[@id= 'session_key-login-error']")
    private WebElement loginError;

    @FindBy (xpath = "//*[@id = 'session_password-login-error']")
    private WebElement passwordError;

    @FindBy(xpath = "//*id = 'session_key-login-error']")
    private WebElement loginPaswordError;

    @FindBy(xpath = "//*[@id = 'control_gen_1']")
    private WebElement errorMessage;


    public LoginSubmit (WebDriver webDriver){
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isErrorPageLoaded(){
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME")
                && webDriver.getTitle().contains("Войти в LinkedIn")
                && isErrorMessageOnDisplayed();
    }

    public boolean isErrorMessageOnDisplayed(){
        return errorMessage.isDisplayed();
    }

}