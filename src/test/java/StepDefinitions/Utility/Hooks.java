package StepDefinitions.Utility;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import factory.DriverFactory;
import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> scenarioTest = new ThreadLocal<>();

    // Initialize ExtentReports before all tests
    @BeforeAll
    public static void beforeAll() {
        try {
            String reportDir = System.getProperty("user.dir") + "/reports/";
            String reportPath = reportDir + "extentReport.html";

            // Ensure directories exist
            new File(reportDir + "screenshots/").mkdirs();

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setReportName("Cucumber Selenium Report");
            reporter.config().setDocumentTitle("Automation Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Satheesh");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", "Chrome");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Start scenario
    @Before
    public void setUp(Scenario scenario) {
        ExtentTest test = extent.createTest(scenario.getName());
        scenarioTest.set(test);
        scenarioTest.get().info("Starting scenario: " + scenario.getName());
    }

    // Tear down scenario
    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();

        try {
            if (scenario.isFailed() && driver != null) {
                scenarioTest.get().fail("Scenario Failed: " + scenario.getName());
                String screenshotPath = takeScreenshot(scenario.getName(), driver);
                if (screenshotPath != null) {
                    scenarioTest.get().addScreenCaptureFromPath(screenshotPath, "Failed Screenshot");
                }
                attachScreenshotToCucumber(scenario, driver);
            } else if (driver != null) {
                scenarioTest.get().pass("Scenario Passed: " + scenario.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Quit the driver safely before removing it
            if (driver != null) {
                try {
                    driver.quit();
                } catch (Exception e) {
                    System.out.println("Error quitting driver: " + e.getMessage());
                } finally {
                    DriverFactory.removeDriver();
                }
            }
        }
    }

    // Flush ExtentReports after all tests
    @AfterAll
    public static void afterAll() {
        if (extent != null) {
            extent.flush();
        }
    }

    // Capture screenshot and save with timestamp
    private String takeScreenshot(String scenarioName, WebDriver driver) {
        try {
            if (!(driver instanceof TakesScreenshot)) return null;

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String safeScenarioName = scenarioName.replaceAll("[^a-zA-Z0-9]", "_");
            String destPath = System.getProperty("user.dir") + "/reports/screenshots/"
                    + safeScenarioName + "_" + timestamp + ".png";

            FileUtils.copyFile(srcFile, new File(destPath));
            return destPath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Attach screenshot to Cucumber scenario
    private void attachScreenshotToCucumber(Scenario scenario, WebDriver driver) {
        try {
            if (!(driver instanceof TakesScreenshot)) return;
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotBytes, "image/png", "Failed Screenshot");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
