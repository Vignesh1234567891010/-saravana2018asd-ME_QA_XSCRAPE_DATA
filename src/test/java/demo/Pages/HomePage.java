package demo.Pages;

import demo.wrappers.Wrappers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
    private final Wrappers action;

    public HomePage(WebDriver driver){
        super(driver);
        action = new Wrappers(driver);
    }

    private final By hockey_Team = By.xpath("//div[@class='page']//child::a[contains(text(),'Hockey')]");

    private final By Oscar_Winning = By.xpath("//div[@class='page']//child::a[contains(text(),'Oscar')]");

    private final String HOME_PAGE_URL = "https://www.scrapethissite.com/pages/";

    public void clickOnHockey(){
        WebElement hockey = action.getElement(hockey_Team);
        action.clickElement(hockey);
        action.waitForPageLoad();
    }

    public void clickOnOscar(){
        WebElement Oscar = action.getElement(Oscar_Winning);
        action.clickElement(Oscar);
        action.waitForPageLoad();
    }

    public void navigateToHomePage(){
        if(!driver.getCurrentUrl().equals(HOME_PAGE_URL)){
            action.navigateTo(HOME_PAGE_URL);
        }
    }
}
