import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePage extends Basepage {
    WebDriver driver;
    public HomePage(WebDriver driver) {

        this.driver = driver;
    }
    public By clickOnItem = By.xpath("//div[.='Sauce Labs Backpack']");
    public By addToCard =By.cssSelector(".btn_inventory");
    public By cardButton = By.id("shopping_cart_container");
    public By checkOut = By.id("checkout");
    public By firstName = By.id("first-name");
    public By lastName = By.id("last-name");
    public By portal = By.id("postal-code");
    public By continueOnInfo = By.id("continue");
    public By finish = By.id("finish");
    public By thankYouMassage = By.xpath("//h2[.='Thank you for your order!']");
    public By yourCardPrice = By.xpath("//div[@class='inventory_item_price']");
    public By PriceOnPayment = By.xpath("(//div[@class='summary_subtotal_label'])[1]");
    public By backToHome = By.id("back-to-products");
    public By click3Dots=By.id("react-burger-menu-btn");
    public By clickLogOut = By.id("logout_sidebar_link");
    public By SwagLabs =By.xpath("//div[.='Swag Labs']");


    public void clickOnBackPack (ExtentTest methodNode) {
        step(methodNode,"click on BackPackItem");
        findTheElement(clickOnItem,10).click();
    }
    public void clickOnAddToCard (ExtentTest methodNode){
        step(methodNode,"Click on add to card Button randomly");
            List<WebElement> list = findElements(addToCard,50);
            list.get(getRandomValue(list.size())).click();

        }
    public void clickCardOfBackPack(ExtentTest methodNode){
        step(methodNode,"click on add to card to back pack item");
        findTheElement(addToCard,50).click();
    }
    public void clickCardIcon(ExtentTest methodNode){
        step(methodNode,"click on view card button");
        findTheElement(cardButton,10).click();
    }
    public void clickCheckOut(ExtentTest methodNode){
        step(methodNode,"Click on checkout Button");
        findTheElement(checkOut,10).click();
    }
    public void clickFirstName (ExtentTest methodNode,String F){
        step(methodNode,"Enter first Name");
        findTheElement(firstName,10).sendKeys(F);
    }
    public void clickLastName (ExtentTest methodNode,String L){
        step(methodNode,"Enter Last name");
        findTheElement(lastName,10).sendKeys(L);
    }
    public void clickPortal (ExtentTest methodNode,String Z){
        step(methodNode,"Enter zip code");
        findTheElement(portal,10).sendKeys(Z);
    }
    public void clickContinue (ExtentTest methodNode){
        step( methodNode,"click continue Button");
        findTheElement(continueOnInfo,10).click();
    }
    public void clickFinish (ExtentTest methodNode){
        step(methodNode,"Click Finish Button");
        findTheElement(finish,10).click();
    }
    public boolean verifyThankYou (ExtentTest methodNode){
        step(methodNode,"Verify Thank you button");
        WebElement el =findTheElement(thankYouMassage,10);
        return el.isDisplayed();
    }
    public String getPrice (ExtentTest methodNode, int i){
        step(methodNode,"get the price of item on card");
        List<WebElement> element = findElements(yourCardPrice,10);
        return element.get(i).getText();
    }
    public double priceOnCard (ExtentTest methodNode){
        step(methodNode,"get the full price of items");
        String value = findTheElement(PriceOnPayment,10).getText();
        System.out.println(value);
        Pattern pattern = Pattern.compile("\\d+\\.\\d+");
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            String matchedNumber = matcher.group();
            return Double.parseDouble(matchedNumber);
        }
        else {
            return 0;
        }
    }
    public void  clickBackToHome (ExtentTest  methodNode){
        step(methodNode,"Click on Back Button");
        findTheElement(backToHome,10).click();
    }
    public void  click3DotsButton (ExtentTest  methodNode){
        step(methodNode,"Click on 3 Dots Button");
        findTheElement(click3Dots,10).click();
    }
    public void  clickLogout (ExtentTest  methodNode){
        step(methodNode,"Click on logOut Button");
        findTheElement(clickLogOut,10).click();
    }
    public boolean verifySwagLabs (ExtentTest  methodNode){
        step(methodNode,"verify SwagLabs logo on login page");
        WebElement el =findTheElement(SwagLabs,10);
        return el.isDisplayed();
    }
}




