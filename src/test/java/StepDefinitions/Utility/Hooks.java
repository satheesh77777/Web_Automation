package StepDefinitions.Utility;

import base.BaseClass; // Your base class containing WebDriver setup and common methods
import com.aventstack.extentreports.ExtentReports; // Main ExtentReports class to manage reports
import com.aventstack.extentreports.ExtentTest; // Represents individual test (scenario) in ExtentReports
import com.aventstack.extentreports.reporter.ExtentSparkReporter; // HTML reporter for ExtentReports
import io.cucumber.java.*; // Cucumber hooks (@Before, @After, Scenario, etc.)
import org.apache.commons.io.FileUtils; // To copy files (for saving screenshots)
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot; // To take screenshots with Selenium
import java.io.File;
import java.text.SimpleDateFormat; // For timestamp formatting
import java.util.Date;

public class Hooks extends BaseClass {

    // -------------------------------
    // ExtentReports objects
    // -------------------------------
    private static ExtentReports extent; // Single instance shared across all scenarios
    private static ThreadLocal<ExtentTest> scenarioTest = new ThreadLocal<>();
    /*
       ThreadLocal ensures that each thread (parallel execution) has its own ExtentTest object.
       This prevents multiple scenarios running at the same time from overwriting each other's logs.
    */

    // -------------------------------
    // Runs once before all scenarios
    // -------------------------------
    @BeforeAll
    public static void beforeAll() {
        // Path for the main ExtentReports HTML file
        String reportPath = System.getProperty("user.dir") + "/reports/extentReport.html";

        // Create ExtentSparkReporter which generates the HTML report
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Cucumber Selenium Report"); // Title shown in HTML report
        reporter.config().setDocumentTitle("Automation Test Results"); // Browser tab title

        // Initialize ExtentReports and attach reporter
        extent = new ExtentReports();
        extent.attachReporter(reporter);

        // Add system/environment info in the report
        extent.setSystemInfo("Tester", "Satheesh");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser", "Chrome");

        // Create screenshots folder if it doesn't exist
        new File(System.getProperty("user.dir") + "/reports/screenshots/").mkdirs();
        /*
           All screenshots will be saved here: <project-root>/reports/screenshots/
        */
    }

    // -------------------------------
    // Runs before each scenario
    // -------------------------------
    @Before
    public void setUp(Scenario scenario) throws InterruptedException {
        loadConfig(); // Load configuration (URL, browser settings, etc.)
        launchWebDriver(); // Launch WebDriver/browser

        // Create a test node in ExtentReports for this scenario
        ExtentTest test = extent.createTest(scenario.getName());
        scenarioTest.set(test); // Save test object in ThreadLocal for thread safety
        scenarioTest.get().info("Starting scenario: " + scenario.getName());
        // Logs scenario start in ExtentReports
    }

    // -------------------------------
    // Runs after each scenario
    // -------------------------------
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) { // If scenario failed
            scenarioTest.get().fail("Scenario Failed: " + scenario.getName()); // Log failure in ExtentReports

            // 1️⃣ Save screenshot to disk with timestamp
            String screenshotPath = takeScreenshotFileWithTimestamp(scenario.getName());

            // 2️⃣ Attach screenshot to ExtentReports
            if (screenshotPath != null) {
                try {
                    scenarioTest.get().addScreenCaptureFromPath(screenshotPath, "Failed Screenshot");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // 3️⃣ Attach screenshot to Cucumber report as byte array
            byte[] screenshotBytes = takeScreenshotBytes();
            scenario.attach(screenshotBytes, "image/png", "Failed Screenshot");

        } else {
            // Scenario passed → log pass in ExtentReports
            scenarioTest.get().pass("Scenario Passed: " + scenario.getName());
        }

        // Close browser after scenario
        if (driver != null) {
            driver.quit();
        }
    }

    // -------------------------------
    // Runs once after all scenarios
    // -------------------------------
    @AfterAll
    public static void afterAll() {
        extent.flush(); // Write all logs and screenshots to HTML report
    }

    // -------------------------------
    // Utility: Save screenshot with timestamp
    // -------------------------------
    private String takeScreenshotFileWithTimestamp(String scenarioName) {
        try {
            // Take screenshot as temporary file
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Add timestamp to filename
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            // Replace special characters in scenario name to make filename safe
            String safeScenarioName = scenarioName.replaceAll("[^a-zA-Z0-9]", "_");
            // Destination path for screenshot
            String destPath = System.getProperty("user.dir") + "/reports/screenshots/" + safeScenarioName + "_" + timestamp + ".png";

            // Copy screenshot to destination
            File destFile = new File(destPath);
            FileUtils.copyFile(srcFile, destFile);

            // Return file path for ExtentReports attachment
            return destPath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // -------------------------------
    // Utility: Screenshot as byte array for Cucumber
    // -------------------------------
    private byte[] takeScreenshotBytes() {
        // Return screenshot as bytes (for embedding in Cucumber report)
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
