package demo.Pages;

import demo.wrappers.Wrappers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class BasePage {
    protected final WebDriver driver;
    protected final Wrappers action;

    private static final int TIMEOUT = 15;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.action = new Wrappers(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT),this);
    }
}
