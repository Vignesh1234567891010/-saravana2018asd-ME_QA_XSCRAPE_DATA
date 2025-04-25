package demo.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.logging.Level;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory(){

    }

    public static WebDriver getDriver(){
        return driver.get();
    }

    public static void setDriver(String browser){
        if (driver.get() == null) {
            System.setProperty("java.util.logging.config.file", "logging.properties");
            LoggingPreferences logs = new LoggingPreferences();
            logs.enable(LogType.BROWSER, Level.ALL);
            logs.enable(LogType.DRIVER, Level.ALL);

            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setCapability("goog:loggingPrefs", logs);
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");
                    driver.set(new ChromeDriver(chromeOptions));
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setCapability("goog:loggingPrefs", logs);
                    driver.set(new EdgeDriver(edgeOptions));
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setLogLevel(FirefoxDriverLogLevel.INFO); // You can use DEBUG or TRACE too
                    driver.set(new FirefoxDriver(firefoxOptions));
                    break;

                default:
                    throw new IllegalArgumentException("Browser not supported: " + browser);
            }
        }
    }

    public static void quitDriver(){
        if(getDriver() != null){
            getDriver().quit();
            driver.remove();
        }
    }
}
