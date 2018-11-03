import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RequestPage {
    private WebDriver webDriver;

    public RequestPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }


    public boolean isRequestPageLoaded() {
        return webDriver.getCurrentUrl().contains("uas/request-password")
                && webDriver.getTitle().contains("Изменить пароль | LinkedIn")
                && searchBar.isDisplayed();
    }
}
