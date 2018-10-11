import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;
import static java.lang.Thread.sleep;


public class BadCodeExemple {
    public static void main(String args[]) throws InterruptedException {
        String searchTerm = "selenium";

        WebDriver webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://www.google.com/");
        WebElement searchPage = webDriver.findElement(By.xpath("//*[@id='lst-ib']"));
        searchPage.sendKeys(searchTerm);
        searchPage.sendKeys(Keys.ENTER);

        sleep(3000);

        List<WebElement> searchResults = webDriver.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));
        System.out.print("Search result count: " + searchResults.size());

        for (WebElement searchResult : searchResults) {
            String searchResultText  = searchResult.getText();
            System.out.println(searchResult.getText());

            if (searchResultText.toLowerCase().contains(searchTerm.toLowerCase())) {
                System.out.println("searchTerm  " + searchTerm + "  was found");
            } else {
                System.out.println("searchTerm  " + searchTerm + "  was not found");
            }
        }
    }
}

