import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordOnYourEmailPage {
    private WebDriver webDriver;

    @FindBy(className = "content__header")
    private WebElement passwordOnYourEmailText;

    public PasswordOnYourEmailPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPasswordOnYourEmailPageLoaded() {
        return webDriver.getCurrentUrl().contains("checkpoint/rp/request-password-reset-submit")
                && webDriver.getTitle().contains(" для изменения пароля. | LinkedIn")
                && isPasswordOnYourEmailTextOnDisplayed();
    }

    private boolean isPasswordOnYourEmailTextOnDisplayed() {
        return passwordOnYourEmailText.isDisplayed();
    }
}
