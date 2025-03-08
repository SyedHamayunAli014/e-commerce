
import com.aventstack.extentreports.ExtentTest;
import eyesOnTest.Eyes;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginFunction() {
        ExtentTest step = Eyes.getCurrentMethodNode();
        login.loginFun( step,"111111", "555555");
        login.loginCorrect( step,"standard_user", "secret_sauce");
    }
}