package demo.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance(){
        if (extent == null){
            String reportDir = System.getProperty("user.dir")+"/Reports";
            File directory = new File(reportDir);

            if(!directory.exists()){
                directory.mkdirs();
            }

            String reportPath = reportDir + "/ExtentReport.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setReportName("AUTOMATE SCRAPE DATA");
            reporter.config().setDocumentTitle("Extent Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Vignesh");
        }
        return extent;
    }
}
