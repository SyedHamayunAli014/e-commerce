package webRobo.eyesOnTest;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Eyes implements ITestListener {
    private ExtentReports extentReports = new ExtentReports();
    private final Map<String, ExtentTest> classNodes = new HashMap<>();
    private ExtentTest methodNode;
    private static ExtentTest currentMethodNode;
    private WebDriver driver;
    private final Map<String, Integer> methodCountByClass = new HashMap<>(); // To track method count per class



    String htmlBlueTag = "<span style='color:blue;'>";
    String htmlTagClose = "</span>";

    @Override
    public void onStart(ITestContext context) {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String reportPath = Paths.get("reports", "RoboTestReport_" + timestamp + ".html").toString();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

    }

    @Override
    public void onTestStart(ITestResult result) {
        ITestContext context = result.getTestContext();
        this.driver = (WebDriver) context.getAttribute("Driver");
        String className = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();
        String methodDescription = result.getMethod().getDescription();

        ExtentTest classNode = classNodes.computeIfAbsent(className, k -> extentReports.createTest(className));

        methodNode = classNodes.get(className).createNode(result.getMethod().getDescription());
        currentMethodNode = methodNode; // Assign to currentMethodNode

        methodNode.log(Status.INFO, htmlBlueTag + "Starting Test: " + htmlTagClose +
                (methodDescription.isEmpty() ? methodName : methodDescription));
        System.out.println(htmlBlueTag + "Starting Test: " + htmlTagClose +
                (methodDescription.isEmpty() ? methodName : methodDescription));

        methodCountByClass.merge(className, 1, Integer::sum);
        System.out.println("Method count for class " + className + ": " + methodCountByClass.get(className));

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String greenTick = "✅";
        methodNode.log(Status.PASS, greenTick + " - " + result.getMethod().getDescription());
        System.out.println(greenTick + " - " + result.getMethod().getDescription());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        Throwable throwable = result.getThrowable();

        if (throwable != null) {
            String exceptionType = throwable.getClass().getSimpleName();
            String errorMessage = throwable.getMessage();

//            if (errorMessage != null && errorMessage.length() > 100) {
//                errorMessage = errorMessage.substring(0, 300) + "...";
//            }

            methodNode.log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName()
                    + "<br><b style='color:red;'>Exception: " + exceptionType + "</b>"
                    + "<br>Error: " + errorMessage);
            System.out.println("Test Failed: " + result.getMethod().getMethodName()
                    + "\nException: " + exceptionType
                    + "\nError: " + errorMessage);
        } else {
            methodNode.log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
            System.out.println("Test Failed: " + result.getMethod().getMethodName());
        }

        ITestContext context = result.getTestContext();
        this.driver = (WebDriver) context.getAttribute("Driver");
        if (driver instanceof TakesScreenshot) {
            Map<String, String> screenshotDetails = EyesReportsManager.captureScreenshot(driver,
                    result.getMethod().getMethodName());
            System.out.println("Driver supports TakesScreenshot");
            if (screenshotDetails != null) {

                String base64Screenshot = screenshotDetails.get("base64");

                if (base64Screenshot != null) {

                    methodNode.addScreenCaptureFromBase64String(base64Screenshot, "Screenshot on Failure: " +
                            result.getMethod().getDescription());
                }
            } else {
                methodNode.log(Status.FAIL, "Failed to capture screenshot.");
                System.out.println("Driver does not support TakesScreenshot");
            }
        }

        if (throwable != null) {
            throwable.printStackTrace();
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        methodNode.log(Status.SKIP, result.getMethod().getDescription());
        System.out.println("Test Skipped: " + result.getMethod().getDescription());
    }

    @Override
    public void onFinish(ITestContext context) {
        methodCountByClass.forEach((className, methodCount) -> {
            ExtentTest classNode = classNodes.get(className);
            if (classNode != null) {
                classNode.getModel().setName(className + " (Total Tests: " + methodCount + ")");
            }
        });
        System.out.println("\nTest Execution Summary:");
        methodCountByClass.forEach((className, methodCount) ->
                System.out.println("Class: " + className + " - Total Methods: " + methodCount)
        );
        extentReports.flush();

    }

    public static ExtentTest getCurrentMethodNode() {
        return currentMethodNode;
    }

}
