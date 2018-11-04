import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EnterEmailPage {
    private WebDriver webDriver;

    @FindBy(className = "form__submit")
    private WebElement sendNewPaswordOnEmailText;

    @FindBy(id = "username")
    private WebElement enterEmailField;

    @FindBy(className = "reset-password-submit-button" )
    private WebElement sendNewPasswordOnYourEmailButton;

    public EnterEmailPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isEnterEmailPageLoaded() {
        return webDriver.getCurrentUrl().contains("/uas/request-password-reset")
                && webDriver.getTitle().contains("Изменить пароль | LinkedIn")
                && isSendNewPasswordOnEmailOnDisplayed();
    }

    private boolean isSendNewPasswordOnEmailOnDisplayed() {
        return sendNewPaswordOnEmailText.isDisplayed();
    }

    public PasswordOnYourEmailPage enterEmailOnField(String userEmail) {
        enterEmailField.sendKeys(userEmail);
        sendNewPasswordOnYourEmailButton.click();
        return new PasswordOnYourEmailPage(webDriver);
    }
}
