import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;


public class SearchPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//li[contains(@class,'search-result__occluded-item')]")
    private List<WebElement> searchResult;

    @FindBy(xpath = "//h3[contains(@class,'search-results__total')]")
    private WebElement isResult;

    @FindBy(xpath = "//*[@type='search-icon']")
    private WebElement searchLoop;


    public SearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public List<String> getSearchResults() {
        List<String> searchResultList = new ArrayList();
        for (WebElement searchResult : searchResult) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", searchResult);

            String searchResultText = searchResult.getText();
            searchResultList.add(searchResultText);
        }
        System.out.println(searchResultList);
        System.out.println(searchResultList.size());
        return searchResultList;
    }
    public boolean isPageLoaded() {
    return webDriver.getCurrentUrl().equals("https://www.linkedin.com/search/results/all/?keywords=hr&origin=GLOBAL_SEARCH_HEADER")
                         && webDriver.getTitle().contains("(1) 'hr' | Поиск | LinkedIn")
                         && isSearchLoopOnDisplayed();
     }

    public boolean isSearchLoopOnDisplayed() {
                   return searchLoop.isDisplayed();
    }

    public boolean isSearchPageLoaded() {
        return isResult.isDisplayed();
    }
}











