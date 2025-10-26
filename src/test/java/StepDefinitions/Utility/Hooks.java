package StepDefinitions.Utility;
// This class handles setup and teardown for each scenario in Cucumber
// It integrates Extent Reports + Screenshot handling + Driver cleanup

import com.aventstack.extentreports.*;  // Core Extent Reports classes
import com.aventstack.extentreports.reporter.ExtentSparkReporter; // For HTML reporting
import com.aventstack.extentreports.MediaEntityBuilder; // For attaching screenshots inline
import factory.DriverFactory;
import io.cucumber.java.*;  // Cucumber hooks annotations
import org.apache.commons.io.FileUtils; // For saving screenshot files
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {

    // ExtentReports main object (shared across all tests)
    private static ExtentReports extent;

    // Each scenario will have its own ExtentTest (ThreadLocal ensures parallel safety)
    private static ThreadLocal<ExtentTest> scenarioTest = new ThreadLocal<>();

    // ------------------------------------------------------------------------
    // 1) Runs once before ALL test scenarios
    // ------------------------------------------------------------------------
    @BeforeAll
    public static void beforeAll() {
        try {
            // Report storage location
            String reportDir = System.getProperty("user.dir") + "/reports/";
            String reportPath = reportDir + "extentReport.html";

            // Create "screenshots" folder if not already there
            new File(reportDir + "screenshots/").mkdirs();

            // Initialize HTML reporter
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setReportName("Cucumber Selenium Report"); // Display name in report
            reporter.config().setDocumentTitle("Automation Test Results"); // Browser tab title

            // Create ExtentReports instance & attach reporter
            extent = new ExtentReports();
            extent.attachReporter(reporter);

            // Add meta information
            extent.setSystemInfo("Tester", "Satheesh");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", "Chrome");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ------------------------------------------------------------------------
    // 2) Runs BEFORE each scenario
    // ------------------------------------------------------------------------
    @Before
    public void setUp(Scenario scenario) {
        // Create a new ExtentTest entry for this scenario
        ExtentTest test = extent.createTest(scenario.getName());

        // Store the ExtentTest inside ThreadLocal (parallel execution safe)
        scenarioTest.set(test);

        // Add log entry at start
        scenarioTest.get().info("Starting scenario: " + scenario.getName());
    }

    // ------------------------------------------------------------------------
    // 3) Runs AFTER each scenario
    // ------------------------------------------------------------------------
    @After
    public void tearDown(Scenario scenario) {
        // Get current WebDriver from DriverFactory
        WebDriver driver = DriverFactory.getDriver();

        try {
            // If scenario failed
            if (scenario.isFailed() && driver != null) {
                scenarioTest.get().fail("❌ Scenario Failed: " + scenario.getName());

                // Capture screenshot and get path
                String screenshotPath = takeScreenshot(scenario.getName(), driver);

                if (screenshotPath != null) {
                    // Attach screenshot INLINE inside Extent Report
                    scenarioTest.get().fail("Failure Screenshot",
                            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                }

                // Attach screenshot to Cucumber HTML report also
                attachScreenshotToCucumber(scenario, driver);

            }
            // If scenario passed
            else if (driver != null) {
                scenarioTest.get().pass("✅ Scenario Passed: " + scenario.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            // Always close the driver safely
            if (driver != null) {
                try {
                    driver.quit(); // Close browser
                } catch (Exception e) {
                    System.out.println("Error quitting driver: " + e.getMessage());
                } finally {
                    // Remove driver reference from ThreadLocal storage
                    DriverFactory.removeDriver();
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // 4) Runs once AFTER ALL test scenarios
    // ------------------------------------------------------------------------
    @AfterAll
    public static void afterAll() {
        if (extent != null) {
            // Write all test logs into the final HTML report
            extent.flush();
        }
    }

    // ------------------------------------------------------------------------
    // Utility: Capture screenshot & save to disk
    // ------------------------------------------------------------------------
    private String takeScreenshot(String scenarioName, WebDriver driver) {
        try {
            // Ensure driver supports screenshots
            if (!(driver instanceof TakesScreenshot)) return null;

            // Take screenshot and save as a File
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Create unique file name using scenario name + timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String safeScenarioName = scenarioName.replaceAll("[^a-zA-Z0-9]", "_");
            String destPath = System.getProperty("user.dir") + "/reports/screenshots/"
                    + safeScenarioName + "_" + timestamp + ".png";

            // Copy screenshot from temp folder → project "reports/screenshots/"
            FileUtils.copyFile(srcFile, new File(destPath));

            // Return screenshot file path
            return destPath;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ------------------------------------------------------------------------
    // Utility: Attach screenshot into Cucumber HTML report
    // ------------------------------------------------------------------------
    private void attachScreenshotToCucumber(Scenario scenario, WebDriver driver) {
        try {
            if (!(driver instanceof TakesScreenshot)) return;

            // Capture screenshot as byte array
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            // Attach to Cucumber HTML report
            scenario.attach(screenshotBytes, "image/png", "Failed Screenshot");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
