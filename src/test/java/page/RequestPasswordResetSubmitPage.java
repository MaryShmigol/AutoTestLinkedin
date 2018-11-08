package page;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;


public class RequestPasswordResetSubmitPage extends BasePage{

    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;


    public RequestPasswordResetSubmitPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("checkpoint/rp/request-password-reset-submit")
                && webDriver.getTitle().contains("изменения пароля. | LinkedIn")
                && isResendLinkButtonOnDisplayed();
    }

    private boolean isResendLinkButtonOnDisplayed() {
        return resendLinkButton.isDisplayed();
    }

    public SetNewPasswordPage navigateToLinkFromEmail(){

        String messageSubject = "Сергей, данное сообщение содержит ссылку для изменения пароля";
        String messageTo = "uu08474@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";
        GMailService gMailService = new GMailService();

        String message = gMailService.waitMessage(messageSubject,messageTo, messageFrom, 300);

        String resetPasswordLink = StringUtils.substringBetween(message,
                "нажмите <a href="+'"',
                '"'+" style=").replace("&amp;","&");
        webDriver.get(resetPasswordLink);
        return new SetNewPasswordPage(webDriver);


    }

}
