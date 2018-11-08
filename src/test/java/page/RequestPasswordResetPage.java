package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

import static java.lang.Thread.sleep;

public class RequestPasswordResetPage extends BasePage{

    @FindBy(id="username")
    private WebElement emailField;

    @FindBy(className = "form__submit")
    private WebElement emailSumbitButton;

    public RequestPasswordResetPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("uas/request-password")
                && webDriver.getTitle().contains("Изменить пароль | LinkedIn")
                && isEmailFieldOnDisplayed();
    }

    private boolean isEmailFieldOnDisplayed() {
        return emailField.isDisplayed();
    }

    public RequestPasswordResetSubmitPage submitUserEmail(String userEmail){
        GMailService gMailService = new GMailService();
        gMailService.connect();
        emailField.sendKeys(userEmail);
        emailSumbitButton.click();
        return new RequestPasswordResetSubmitPage(webDriver);
    }


}
