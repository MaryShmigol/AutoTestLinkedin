package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccesfulPasswordResetPage extends BasePage{
    @FindBy(id="reset-password-submit-button")
    private WebElement goHomeButton;

    public SuccesfulPasswordResetPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("/checkpoint/rp/password-reset-submit")
                && webDriver.getTitle().contains("Вы изменили свой пароль. | LinkedIn")
                && isGoHomeButtonOnDisplayed();
    }

    private boolean isGoHomeButtonOnDisplayed() {
        return goHomeButton.isDisplayed();
    }
    public HomePage clickOnGoToHomeButton(){
        goHomeButton.click();
        return new HomePage(webDriver);
    }
}
