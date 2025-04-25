package demo.Pages;

import demo.wrappers.Wrappers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Instant;
import java.util.*;

public class HockeyTeamPage extends BasePage{
    private final Wrappers action;

    public HockeyTeamPage(WebDriver driver){
        super(driver);
        action = new Wrappers(driver);
    }

    private final By pageTitle = By.xpath("//h1[contains(text(),'Hockey')]");

    private final By tableRows = By.xpath("//table//tr[@class='team']");

    private final By tableCols = By.xpath(".//td");

    private final By nextButton = By.xpath("//a[@aria-label='Next']");

    public boolean validateHockeyPage(String text){
        WebElement titleElement = action.getElement(pageTitle);
        return action.isElementPresented(titleElement) && action.getText(titleElement).contains(text);
    }

    public List<Map<String, Object>> get_hockey_team_data(){
        List<Map<String, Object>> dataList = new ArrayList<>();

        for(int i=0;i<4;i++){
            List<WebElement> rows = action.getElements(tableRows);

            for (WebElement row : rows){
                List<WebElement> cols = row.findElements(tableCols);
                if(cols.size() >= 3){
                    String teamName = action.getText(cols.get(0)).trim();
                    String year = action.getText(cols.get(1)).trim();
                    String winPercentageStr = action.getText(cols.get(5)).trim();

                    try {
                        double winPercentage = Double.parseDouble(winPercentageStr);
                        if(winPercentage < 0.40){
                            Map<String, Object> teamData = new HashMap<>();
                            teamData.put("epochTime", Instant.now().getEpochSecond());
                            teamData.put("teamName", teamName);
                            teamData.put("year", year);
                            teamData.put("winPercent",winPercentage);
                            dataList.add(teamData);
                        }
                    }catch (NumberFormatException e){
                        System.out.println("Skipping invalid win %"+winPercentageStr);
                    }
                }
            }
            if(!action.isElementPresented(nextButton)){
                break;
            }else {
                action.clickElement(nextButton);
                action.waitForPageLoad();
            }
        }
        return dataList;
    }
}
