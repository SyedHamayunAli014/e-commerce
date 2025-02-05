import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Test;
import com.applitools.eyes.selenium.Eyes;
import test.eyesOnTest.Eyes;



public class LoginTest extends BaseTest{
    @Test(description = "Verify Restaurant details and components", groups = {"onlineQR", "online"})
    public void loginFunction()  {
        ExtentTest step = Eyes.getCurrentMethodNode();
        LoginTest.loginFunction (step,"standard_user","secret_sauce");
    }
}
