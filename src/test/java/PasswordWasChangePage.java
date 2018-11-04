import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordWasChangePage {
    private WebDriver webDriver;

    @FindBy(className = "content__header")
    private WebElement paswwordWasChandeText;

    public PasswordWasChangePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPasswordWasChangePageLoaded() {
        return webDriver.getCurrentUrl().contains("checkpoint/rp/password-reset-submit")
                && webDriver.getTitle().contains("Вы изменили свой пароль. | LinkedIn")
                && isPasswordWasChangeTextOnDisplayed();
    }

    private boolean isPasswordWasChangeTextOnDisplayed() {
        return paswwordWasChandeText.isDisplayed();
    }
}
