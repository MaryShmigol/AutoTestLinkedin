package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class HomePage extends BasePage{

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

    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/feed/")
                && webDriver.getTitle().contains("LinkedIn")
                && isProfileNavItemOnDisplayed();
    }

    public boolean isProfileNavItemOnDisplayed() {
        return profileNavItem.isDisplayed();
    }
    public SearchPage search(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.RETURN);
        return new SearchPage(webDriver);
    }


}
