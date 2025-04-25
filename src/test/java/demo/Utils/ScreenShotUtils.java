package demo.Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotUtils {
    public static String captureScreenShot(WebDriver driver, String testName){
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename = testName + "_" + timeStamp + ".png";
        String screenShotPath = "/Reports/ScreenShots/" + filename;
        try {
            File destFile = new File(System.getProperty("user.dir") + "/" + screenShotPath);
            destFile.getParentFile().mkdirs();
            Files.copy(srcFile.toPath(), destFile.toPath());
        }catch (IOException e){
            e.printStackTrace();
        }
        return screenShotPath;
    }
}
