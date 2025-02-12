import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;




public class LoginTest extends BaseTest {

    @Test(description = "Test Cases for login function")
    public void loginFunction() {
        ExtentTest test = extentReports.createTest("Login Test");
        loginPage.loginFun(test, "standard_user", "secret_sauce");

    }
}