import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangePasswordPage {
    private WebDriver webDriver;

    @FindBy(className = "content__header")
    private WebElement changePasswordText;

    @FindBy(id = "newPassword")
    private WebElement newPaswordField;

    @FindBy(id = "confirmPassword")
    private WebElement confirmNewPasswordField;

    @FindBy(id = "reset-password-submit-button")
    private WebElement createNewPaswordButton;

    public ChangePasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isChangePasswordPageLoaded() {
        return webDriver.getCurrentUrl().contains("checkpoint/rp/password")
                && webDriver.getTitle().contains("Изменить пароль | LinkedIn")
                && isChangePasswordTextOnDisplayed();
    }

    private boolean isChangePasswordTextOnDisplayed() {
        return changePasswordText.isDisplayed();
    }

    public PasswordWasChangePage changePassword(String newPassword, String confirmNewPassword) {
        newPaswordField.sendKeys(newPassword);
        confirmNewPasswordField.sendKeys(confirmNewPassword);
        createNewPaswordButton.click();
        return new PasswordWasChangePage(webDriver);
    }
}
