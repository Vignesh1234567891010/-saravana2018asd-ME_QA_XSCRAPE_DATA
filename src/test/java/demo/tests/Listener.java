package demo.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import demo.Utils.DriverFactory;
import demo.Utils.ExtentManager;
import demo.Utils.ScreenShotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener, ISuiteListener {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public void onStart(ISuite suite){
        extent = ExtentManager.getInstance();
    }

    public void onFinish(ISuite suite){
        extent.flush();
    }

    public void onTestStart(ITestResult result){
        String testName = result.getMethod().getMethodName();
        ExtentTest test = extent.createTest(testName);
        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result){
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result){
        extentTest.get().log(Status.FAIL, result.getThrowable());

        WebDriver driver = DriverFactory.getDriver();
        if(driver != null){
            String screenShotPath = ScreenShotUtils.captureScreenShot(driver, result.getMethod().getMethodName());
            extentTest.get().addScreenCaptureFromPath(screenShotPath);
        }
    }

    public void onTestSkipped(ITestResult result){
        extentTest.get().log(Status.SKIP, result.getThrowable());

    }

    public static ExtentTest getExtentTest(){
        return extentTest.get();
    }
}
