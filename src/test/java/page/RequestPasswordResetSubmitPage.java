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
        GMailService gMailService = new GMailService();
        String messageSubject = "Сергей, данное сообщение содержит ссылку для изменения пароля";
        String messageTo = "uu08474@gmail.com";
        String messageFrom = "LinkedIn <security-noreply@linkedin.com>";
        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 200);

        String resetPaswordLink = StringUtils.substringBetween(
                message,
                "Чтобы изменить пароль в LinkedIn, нажмите <a href=\"",
                "\" style").replace("&amp;", "&");
        System.out.println("Content:"+resetPaswordLink);

        webDriver.get(resetPaswordLink);
        return new SetNewPasswordPage(webDriver);
    }
}
