package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessfulPasswordResetPage extends BasePage{
    @FindBy(id="reset-password-submit-button")
    private WebElement goHomeButton;

    /**
     * @param webDriver
     * Method which initiate web driver in this class and initiate web elements
     */
    public SuccessfulPasswordResetPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * @return
     * Method which test is page loaded or not
     */
    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("/checkpoint/rp/password-reset-submit")
                && webDriver.getTitle().contains("Вы изменили свой пароль. | LinkedIn")
                && isGoHomeButtonOnDisplayed();
    }

    /**
     * @return
     * Method which show button on display or not
     */
    private boolean isGoHomeButtonOnDisplayed() {
        return goHomeButton.isDisplayed();
    }

    /**
     * @return
     * Method which enter go home button
     */
    public HomePage clickOnGoToHomeButton(){
        goHomeButton.click();
        return new HomePage(webDriver);
    }
}
