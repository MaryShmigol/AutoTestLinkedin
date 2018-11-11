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

    /**
     * @param webDriver
     * Method which initiate web driver in this class and initiate web elements
     */
    public RequestPasswordResetPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * @return
     * Method which test is page loaded or not
     */
    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("uas/request-password")
                && webDriver.getTitle().contains("Изменить пароль | LinkedIn")
                && isEmailFieldOnDisplayed();
    }

    /**
     * @return
     * Method which show Email Field is visible
     */
    private boolean isEmailFieldOnDisplayed() {
        return emailSumbitButton.isDisplayed();
    }

    /**
     * @param userEmail
     * @return
     * Method which enter and submit our email
     */
    public RequestPasswordResetSubmitPage submitUserEmail(String userEmail){
        gMailService = new GMailService();
        gMailService.connect();
        emailField.sendKeys(userEmail);
        emailSumbitButton.click();
        return new RequestPasswordResetSubmitPage(webDriver);
    }


}
