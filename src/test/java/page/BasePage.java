package page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GMailService;

public abstract class BasePage {
    WebDriver webDriver;
    static GMailService gMailService;

    /**
     * @param webElement
     * @param i
     * Method which wait until element will bi clickable
     */
    public void waitUntilElementIsClickable(WebElement webElement, int i){
        WebDriverWait wait = new WebDriverWait(webDriver,5);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /**
     * @param webElement
     * @param timeOutInSeconds
     * Method which wait until element will bi visible
     */
    public void waitUntilElementIsVisible (WebElement webElement, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * @return
     *Method which test is page loaded or not in each Page Object
     */
    public abstract boolean isPageLoaded();
}
