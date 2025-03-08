import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.net.MalformedURLException;
import java.net.URL;


public class BaseTest {
    WebDriver driver;
    public static WebDriverWait wait;
    public static JavascriptExecutor js;
    public LoginPage login;
    public HomePage homePage;

    @BeforeMethod
    public void setUp() {
//        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
//        ExtentReports extentReports = new ExtentReports();
//        extentReports.attachReporter(sparkReporter);

    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://www.saucedemo.com/");



        login = new LoginPage(driver);
        homePage =new HomePage(driver);
        Basepage.setDriver(driver);

        
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }


    }
}
