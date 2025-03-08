import com.aventstack.extentreports.ExtentTest;
import eyesOnTest.Eyes;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends  BaseTest{
    @Test(description = "Verify add to card  and continue the order",enabled = false)
    public void ClickRandomItem(){
        ExtentTest step = Eyes.getCurrentMethodNode();
        login.loginCorrect( step,"standard_user", "secret_sauce");
        homePage.clickOnAddToCard(step);
        homePage.clickOnBackPack(step);
        homePage.clickCardOfBackPack(step);
        homePage.clickCardIcon(step);
        homePage.clickCheckOut(step);
        homePage.clickFirstName(step,"syed");
        homePage.clickLastName(step,"Humayun");
        homePage.clickPortal(step,"4242");
        homePage.clickContinue(step);
        homePage.clickFinish(step);
        boolean el = homePage.verifyThankYou(step);
        Assert.assertTrue(el,"Thank you messgae is not display");
    }
    @Test(description = "verify the price of item on checkout")
    public void verifyItemPrice() throws InterruptedException {
        ExtentTest step = Eyes.getCurrentMethodNode();
        login.loginCorrect(step, "standard_user", "secret_sauce");
        homePage.clickOnAddToCard(step);
        homePage.clickOnBackPack(step);
        homePage.clickCardOfBackPack(step);
        homePage.clickCardIcon(step);
        String firstItemPrice = homePage.getPrice(step, 0);
        System.out.println(firstItemPrice);
        String secondItemPrice = homePage.getPrice(step, 1);
        System.out.println(secondItemPrice);
        firstItemPrice = firstItemPrice.substring(1);
        secondItemPrice = secondItemPrice.substring(1);
        double totalPrice = Double.parseDouble(firstItemPrice) + Double.parseDouble(secondItemPrice);
        homePage.clickCheckOut(step);
        homePage.clickFirstName(step,"syed");
        homePage.clickLastName(step,"Humayun");
        homePage.clickPortal(step,"4242");
        homePage.clickContinue(step);
        double cardPayment = homePage.priceOnCard(step);
        Assert.assertEquals(cardPayment,totalPrice,"value is not correct" );
        homePage.clickFinish(step);
        homePage.clickBackToHome(step);
        homePage.click3DotsButton(step);
        homePage.clickLogout(step);
        boolean  el = homePage.verifySwagLabs(step);
        Assert.assertTrue(el,"Swag Lab logo is not display on login screen");
    }
}

