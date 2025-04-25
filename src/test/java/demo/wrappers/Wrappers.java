package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    private final WebDriver driver;
    private final WebDriverWait wait;

    public Wrappers(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(15));
    }

    public WebElement getElement(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement getElement(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public List<WebElement> getElements(By locator){
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> getElements(WebElement element){
        return wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public void clickElement(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void clickElement(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public String getText(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public String getText(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    public void navigateTo(String Url){
        driver.get(Url);
    }

    public void waitForPageLoad(){
        wait.until(driver -> ((JavascriptExecutor) driver).
                executeScript("return document.readyState").equals("complete"));
    }

    public void waitFor(int millis){
        try {
            Thread.sleep(millis);
        }catch (InterruptedException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    public void scrollToElement(WebElement element){
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);",element);
        waitFor(700);
    }

    public void scrollToElement(By locator){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);",element);
        waitFor(700);
    }

    public void scrollDown(int pixel){
        ((JavascriptExecutor) driver).
                executeScript("window.scrollBy(0, "+pixel+")");
        waitFor(700);
    }

    public void pressEnter(){
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
    }

    public boolean isElementPresented(WebElement element){
        try {
            return element.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isElementPresented(By locator){
        try {
            return driver.findElement(locator).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public void waitForVisibility(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
