import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver webDriver;

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavItem;

    @FindBy(xpath = "//*[@type='search-icon']")
    private WebElement searchLoop;

    @FindBy(xpath = "//input[@placeholder='Поиск']")
    private WebElement searchField;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isHomePageLoaded() {
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && webDriver.getTitle().contains("LinkedIn")
                && isProfileNavItemOnDisplayed();
    }

    public boolean isProfileNavItemOnDisplayed() {
        return profileNavItem.isDisplayed();
    }


    public SearchPage searchByTerm(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchLoop.click();
        return new SearchPage(webDriver);
    }


}
