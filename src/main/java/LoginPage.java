import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends Basepage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public By userName =By.xpath("//input[@id='user-name']");
    public By password = By.xpath("//input[@id='password']");
    public By loginButton = By.xpath("(//input[@id='login-button'])[1]");
    public By swagLab =By.xpath("//div[@class='app_logo']");

    public void loginFun(ExtentTest methodNode,String name, String Password){
        step(methodNode,"Enter InValid Phone Number");
        findTheElement(userName,10).sendKeys(name);
        findTheElement(password,10).sendKeys(Password);

    }
    public void loginCorrect(ExtentTest methodNode, String name, String Password){
        step(methodNode,"Enter the userName");
        WebElement el = findTheElement(userName,10);
        clearTextField(el);
        el.sendKeys(name);
        WebElement element =findTheElement(password,10);
        clearTextField(element);
        element.sendKeys(Password);
        findTheElement(loginButton,10).click();
        findTheElement(swagLab,10).isDisplayed();
    }
}
