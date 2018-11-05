package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordOnYourEmailPage {
    private WebDriver webDriver;

    //@FindBy(className = "content__header")
    //private WebElement passwordOnYourEmailText;

    @FindBy(xpath = "//*[@class = 'content__header']")
    private WebElement passwordOnYourEmailText;

    public PasswordOnYourEmailPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return // webDriver.getCurrentUrl().contains("checkpoint/rp/request-password")
                //&& webDriver.getTitle().contains("Проверьте, получили ли вы сообщение эл. почты со ссылкой для изменения пароля. | LinkedIn")
                 isPasswordOnYourEmailTextOnDisplayed();
    }
    private boolean isPasswordOnYourEmailTextOnDisplayed() {
        return passwordOnYourEmailText.isDisplayed();
    }

    public ChangePasswordPage navigateToLinkFromEmail() {
        webDriver.navigate().to("");
        return new ChangePasswordPage(webDriver);
    }
}
