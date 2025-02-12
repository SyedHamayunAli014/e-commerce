import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Basepage {
    public By userName =By.xpath("//input[@id='user-name']");
    public By password = By.xpath("//input[@id='password']");
    public By loginButton = By.xpath("(//input[@id='login-button'])[1]");



    public void loginFun(ExtentTest methodNode, String name, String pasword){
        step(methodNode,"Enter the userName");
        findTheElement(userName,10).sendKeys(name);
        findTheElement(password,10).sendKeys(pasword);
        findTheElement(loginButton,10).click();
    }
}
