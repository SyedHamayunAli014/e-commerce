package webRobo.eyesOnTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EyesReportsManager {
    private static ExtentReports extentReports;

    public static Map<String, String> captureScreenshot(WebDriver driver, String methodName) {

        String screenshotDir = System.getProperty("user.dir") + File.separator + "reports";
        String screenshotPath = screenshotDir + File.separator + methodName + "_" + System.currentTimeMillis() + ".png";
        Map<String, String> screenshotDetails = new HashMap<>();

        try {
            // Capture the screenshot as a file
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File(screenshotPath));
            System.out.println("Screenshot saved at: " + screenshotPath);

            // Capture the screenshot as a Base64 string
            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            screenshotDetails.put("localPath", screenshotPath);
            screenshotDetails.put("base64", base64Screenshot);
            System.out.println("Screenshot saved locally at: " + screenshotPath);

            return screenshotDetails;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
