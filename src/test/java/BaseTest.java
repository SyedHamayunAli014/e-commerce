import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentReports extentReports;
    public static WebDriverWait wait;
    public static JavascriptExecutor js;
    public LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        // Extent Reports Setup
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        // WebDriver Setup

            // WebDriver Setup
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP FOLIO\\chrome-win64\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://www.saucedemo.com/");

            // Initialize LoginPage and set the driver
            loginPage = new LoginPage();
//            Basepage.setDriver(driver);



        loginPage = new LoginPage();
        Basepage.setDriver(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        // Flush Extent Reports
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
