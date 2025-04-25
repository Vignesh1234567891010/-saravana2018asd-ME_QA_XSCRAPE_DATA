package demo.Pages;

import demo.wrappers.Wrappers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OscarMoviePage extends BasePage {

    private final Wrappers action;

    public OscarMoviePage(WebDriver driver){
        super(driver);
        action = new Wrappers(driver);
    }

    private final By pageTitle = By.xpath("//h1[contains(text(),'Oscar')]");

    private final By yearLocator = By.xpath("//a[@class='year-link']");

    private final By table = By.tagName("table");

    private final By tableRow = By.xpath("//table//tr[@class='film']");

    private final By tableCol = By.xpath(".//td");

    private final By isWinnerLocator = By.tagName("i");

    public boolean validatePage(String text){
        WebElement pageTitleElement = action.getElement(pageTitle);
        return action.isElementPresented(pageTitleElement) && action.getText(pageTitleElement).contains(text);
    }

    public List<Map<String, Object>> get_oscar_movie_data(){
        List<Map<String, Object>> movieData = new ArrayList<>();

        List<WebElement> years = action.getElements(yearLocator);
        for (WebElement year : years){
            String yearText = action.getText(year);
            action.clickElement(year);
            action.waitForVisibility(table);

            List<WebElement> rows = action.getElements(tableRow);
            int count = 0;
            for(WebElement row : rows){
                if(count >= 5){
                    break;
                }
                List<WebElement> cols = row.findElements(tableCol);
                String title = action.getText(cols.get(0)).trim();
                String nomination = action.getText(cols.get(1)).trim();
                String awards = action.getText(cols.get(2)).trim();
                boolean isWinner = !cols.get(3).findElements(By.tagName("i")).isEmpty();

                Map<String, Object> movieMap = new HashMap<>();
                movieMap.put("epochTime", Instant.now().getEpochSecond());
                movieMap.put("Year", yearText);
                movieMap.put("Title", title);
                movieMap.put("Nomination", nomination);
                movieMap.put("Awards", awards);
                movieMap.put("isWinner", isWinner);

                movieData.add(movieMap);
                count++;
            }
        }
        return movieData;
    }
}
