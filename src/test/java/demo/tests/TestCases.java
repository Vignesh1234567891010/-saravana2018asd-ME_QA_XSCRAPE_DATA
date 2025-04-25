package demo.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.Pages.HockeyTeamPage;
import demo.Pages.HomePage;
import demo.Pages.OscarMoviePage;
import demo.Utils.DriverFactory;
import demo.wrappers.Wrappers;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
// import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    protected WebDriver driver;
    private Wrappers action;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @Parameters("browser")
    @BeforeTest(alwaysRun = true)
    public void startBrowser(@Optional("chrome") String browser)
    {


        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        DriverFactory.setDriver(browser);
        driver = DriverFactory.getDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.scrapethissite.com/pages/");

    }

    @Test
    public void testCase01(){

        System.out.println("Start Test Case: TestCase01");
        Listener.getExtentTest().info("Start Test Case: TestCase01");
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        homePage.clickOnHockey();

        HockeyTeamPage hockeyTeamPage = new HockeyTeamPage(driver);
        Assert.assertTrue(hockeyTeamPage.validateHockeyPage("Hockey"), "Unable to navigate to Hockey Team Page");
        Listener.getExtentTest().info("Test Step: Successfully Navigated to Hockey Team Page");
        System.out.println("Test Step: Successfully Navigated to Hockey Team Page");

        List<Map<String,Object>> dataList = hockeyTeamPage.get_hockey_team_data();

        File dir = new File(System.getProperty("user.dir") + "/src/test/resources/test_output");
        if(!dir.exists()){
            dir.mkdir();
        }
        File outputFile = new File(dir, "team_data.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, dataList);
        }catch (IOException e){
            e.printStackTrace();
        }

        Assert.assertTrue(outputFile.exists(), "JSON File was not created");
        System.out.println("Test Step: JSON File Was Created Successfully");
        Listener.getExtentTest().info("Test Step: JSON File Was Created Successfully");

        Assert.assertTrue(outputFile.length() > 0,"JSON File is Empty");
        System.out.println("Test Step: Successfully JSON File contains Content");
        Listener.getExtentTest().info("Test Step: Successfully JSON File contains Content");

        homePage.navigateToHomePage();
        System.out.println("End Test: TestCase01");
        Listener.getExtentTest().info("End Test: TestCase01");
        System.out.println();



    }

    @Test
    public void testCase02(){

        System.out.println("Start Test Case: TestCase02");
        Listener.getExtentTest().info("Start Test Case: TestCase02");
        HomePage homePage = new HomePage(driver);
        homePage.navigateToHomePage();
        homePage.clickOnOscar();

        OscarMoviePage oscarMoviePage = new OscarMoviePage(driver);
        Assert.assertTrue(oscarMoviePage.validatePage("Oscar"),"Unbale to navigate to Oscar Page");
        System.out.println("Test Step: Successfully navigated to Oscar Page");
        Listener.getExtentTest().info("Test Step: Successfully navigated to Oscar Page");

        List<Map<String, Object>> movieData = oscarMoviePage.get_oscar_movie_data();
        File dir = new File(System.getProperty("user.dir") + "/src/test/resources/test_output");
        if (!dir.exists()){
            dir.mkdir();
        }

        File outPutFile = new File(dir,"movie_oscar_data.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(outPutFile, movieData);
        }catch (IOException e){
            e.printStackTrace();
        }

        Assert.assertTrue(outPutFile.exists(), "JSON File was not created");
        System.out.println("Test Step: JSON File Was Created Successfully");
        Listener.getExtentTest().info("Test Step: JSON File Was Created Successfully");

        Assert.assertTrue(outPutFile.length() > 0, "JSON File is Empty");
        System.out.println("Test Step: Successfully JSON File contains Content");
        Listener.getExtentTest().info("Test Step: Successfully JSON File contains Content");

        homePage.navigateToHomePage();
        System.out.println("End Test: TestCase02");
        Listener.getExtentTest().info("End Test: TestCase02");
        System.out.println();
    }

    @AfterTest(alwaysRun = true)
    public void endTest()
    {
        DriverFactory.quitDriver();

    }
}