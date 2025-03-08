import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


public class Basepage {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static JavascriptExecutor js;
    public static Actions actions;
    public static Random random;

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final Random RANDOM = new Random();



//    public void WebdashBasePage() {
//
//    }

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
    }




    public static String generateRandomWord(int length) {
        StringBuilder word = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(ALPHABET.length());
            word.append(ALPHABET.charAt(index));
        }

        return word.toString();
    }
    static String getDriverPath() {
        // Example 1: Use WebDriverManager to set up and get the path dynamically
        WebDriverManager.chromedriver().setup();
        String chromeDriverPath = System.getProperty("webdriver.chrome.driver");
        System.out.println("ChromeDriver Path: " + chromeDriverPath);
        return chromeDriverPath;
    }

        public static String generateRandomColor() {
        // Generate random values for Red, Green, and Blue (each ranging from 0 to 255)
        int red = RANDOM.nextInt(256);
        int green = RANDOM.nextInt(256);
        int blue = RANDOM.nextInt(256);

        // Convert the RGB values to a hexadecimal string and format it as #RRGGBB
        return String.format("#%02x%02x%02x", red, green, blue);
    }


    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void testSteps(String stepDescription) {
        logger.info("Step: " + stepDescription);
    }

    public void step(ExtentTest methodNode, String val) {
        testSteps(val);

        if (methodNode != null) {
            methodNode.log(Status.INFO, val);
        } else {
            System.err.println("Warning: MethodNode is null, unable to log to ExtentReports");
        }

    }

    private FluentWait<WebDriver> configureFluentWait(int timeInSeconds, int pollyingInMillis) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeInSeconds))
                .pollingEvery((Duration.ofMillis(pollyingInMillis)))
                .ignoring((NoSuchElementException.class));
    }

    public WebElement fluentwaitUntilClickable(WebElement locator, int timeInSeconds, int pollingInMillis) {
        return configureFluentWait(timeInSeconds, pollingInMillis)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement fluentwaitUntilVisible(By locator, int timeInSeconds, int pollingInMillis) {
        return configureFluentWait(timeInSeconds, pollingInMillis)
                .until(visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        try {
            return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {

            System.out.println("Timeout: Element not found or not visible within " + timeoutInSeconds + " seconds: " + locator);
            return null;
        }
    }

    public WebElement waitForElementPresence(By locator, int timeoutInSeconds) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return explicitWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void pageLoadWait(int timeoutInSeconds) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeoutInSeconds));
    }

    public void waitImplicit() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

    }

    public WebElement findTheElement(By locator, int timeoutInSeconds) {
        try {
            return waitForElementToBeVisible(locator, timeoutInSeconds);
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + locator.toString());
            return null;
        }
    }

    public List<WebElement> findElements(By locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (TimeoutException e) {
            System.out.println("Elements not found within timeout: " + locator.toString());
            return Collections.emptyList();  // Return an empty list if no elements are found
        }
    }


    public void clearTextField(WebElement element) {

        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.BACK_SPACE);
    }

    public void removeCategories(ExtentTest methodNode, By parentCategory, List<String> categoriesArray) {
        if (driver == null) {
            throw new IllegalStateException("Driver must be initialized before calling this method.");
        }

        step(methodNode, "Remove all assigned categories");

        WebElement categoriesHover = findTheElement(parentCategory, 10);

        // Hover over the element
        actions.moveToElement(categoriesHover).perform();
        actions.click().perform();

        int length = categoriesArray.size();

        for(int i = 0; i < length; i++) {
            actions.sendKeys(Keys.BACK_SPACE).perform();
        }

    }
    public  String generateRandomNumberString(int length) {
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder randomNumberString = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(numbers.length());
            randomNumberString.append(numbers.charAt(index));
        }
        return randomNumberString.toString();
    }
    public double calculateTax(String totalAmountStr, String taxPercentageStr) {
        try {
            // Parse the input strings to double
            double totalAmount = Double.parseDouble(totalAmountStr);
            double taxPercentage = Double.parseDouble(taxPercentageStr);

            // Calculate tax
            double tax = (totalAmount * taxPercentage) / 100;

            // Round off the tax value
            return roundOff(tax);
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Please provide numeric values.");
            return 0.0; // Return a default value or handle it appropriately
        }
    }

    public double roundOff(double amount) {
        String[] parts=Double.toString(amount).split("\\.");
        if(parts.length>1&&parts[1].length()>2&&parts[1].charAt(2) == '5') {
            char secondDecimalPlace = Double.toString(amount).split("\\.")[1].charAt(1);
            if (Character.getNumericValue(secondDecimalPlace) % 2 == 0) {
                return Math.floor(amount * 100) / 100.0;
            } else {
                return Math.ceil(amount * 100) / 100.0;
            }
        } else {
            return Math.round(amount * 100) / 100.0;
        }
    }
    public static int getRandomValue(int size){
        if (size <= 0) {
            return -1;
        }
        random = new Random();
        return random.nextInt(size);
    }



}

