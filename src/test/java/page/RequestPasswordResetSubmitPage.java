package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class RequestPasswordResetSubmitPage extends BasePage{

    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement textLinkButton;


    /**
     * @param webDriver
     * Method which initiate web driver in this class and initiate web elements
     */
    public RequestPasswordResetSubmitPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * @return
     * Method which test is page loaded or not
     */
    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("checkpoint/rp/request-password-reset-submit")
                && webDriver.getTitle().contains("изменения пароля. | LinkedIn")
                && isResendLinkButtonOnDisplayed();
    }

    /**
     * @return
     * Method which show textLinkButton is visible
     */
    private boolean isResendLinkButtonOnDisplayed() {
        return textLinkButton.isDisplayed();
    }

    /**
     * @return
     * Method which get link from Linkedin message
     */
    public SetNewPasswordPage navigateToLinkFromEmail(){

        String messageSubject = "Сергей, данное сообщение содержит ссылку";
        String messageTo = "uu08474@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject,messageTo, messageFrom, 60);
        System.out.println("Content:"+ message);
        String resetPasswordLink = StringUtils.substringBetween(
                message, "\"Чтобы изменить пароль в LinkedIn, нажмите <a href=\\\"\", \">здесь</a>\"",
                "\"").replace("amp;", "");
        System.out.println(resetPasswordLink);
        webDriver.get(resetPasswordLink);

        return new SetNewPasswordPage(webDriver);

    }

}
