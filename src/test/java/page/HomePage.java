package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavItem;

    @FindBy(xpath = "//*[@type='search-icon']")
    private WebElement searchLoop;

    @FindBy(xpath = "//input[@placeholder='Поиск']")
    private WebElement searchField;

    /**
     * @param webDriver
     * Method which initiate web driver in this class and initiate web elements
     */
    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * @return
     * Method which test is page loaded or not
     */
    public boolean isPageLoaded() {
        waitUntilElementIsVisible(profileNavItem, 5);
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/feed/")
                && webDriver.getTitle().contains("LinkedIn")
                && isProfileNavItemOnDisplayed();
    }

    /**
     * @return
     * Method which show element on display or not
     */
    public boolean isProfileNavItemOnDisplayed() {
        return profileNavItem.isDisplayed();
    }

    /**
     * @param searchTerm
     * @return
     * Method which enter keys in search field and click search field
     */
    public SearchPage search(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.RETURN);
        return new SearchPage(webDriver);
    }


}
