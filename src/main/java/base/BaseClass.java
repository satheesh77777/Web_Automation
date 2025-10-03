package base;
// This class is placed in the "base" package.
// It acts as the foundation for configuration management and driver launching.

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

    // This Properties object will hold all key-value pairs from config.properties file
    // Example: browser=chrome, url=https://example.com
    public static Properties prop;

    // ------------------------------------------------------------------------
    // Method 1: Load configuration from config.properties
    // ------------------------------------------------------------------------
    public void loadConfig() {
        // Create an empty Properties object
        prop = new Properties();

        // Build the path for config.properties file dynamically
        // System.getProperty("user.dir") → gives project root folder
        // Example: C:\Users\Satheesh\IdeaProjects\CucumberFramework\configuration\config.properties
        String filePath = System.getProperty("user.dir") + "/configuration/config.properties";

        // Try-with-resources ensures FileInputStream will be closed automatically
        try (FileInputStream ip = new FileInputStream(filePath)) {

            // Load the properties file into the 'prop' object
            prop.load(ip);

        } catch (IOException e) {
            // If the file is missing or can't be read, print error details
            e.printStackTrace();
        }
    }

    // ------------------------------------------------------------------------
    // Method 2: Launch WebDriver based on browser type in config.properties
    // ------------------------------------------------------------------------
    public static WebDriver launchWebDriver() {

        // First check: Did we load the config file?
        // If not, throw an error to remind user to call loadConfig() before this method
        if (prop == null) {
            throw new IllegalStateException("Properties not loaded. Call loadConfig() first.");
        }

        // Read browser type from config.properties (e.g., "chrome" or "firefox")
        String browserName = prop.getProperty("browser");

        // Call DriverFactory to initialize and return WebDriver for that browser
        WebDriver driver = DriverFactory.getDriver(browserName);

        // Common setup for all browsers
        driver.manage().window().maximize(); // maximize the browser window
        driver.get(prop.getProperty("url")); // open the application URL from config.properties

        // Return the driver so test classes or step definitions can use it
        return driver;
    }



    // ------------------------------------------------------------------------
    // Method 3: Quit WebDriver
    // ------------------------------------------------------------------------
    public static void quitDriver() {

        // Get current thread’s WebDriver instance from DriverFactory
        WebDriver driver = DriverFactory.getDriver();

        // If driver exists (not null), quit it
        if (driver != null) {
            driver.quit(); // close the browser and end session

            // Also remove the driver from ThreadLocal in DriverFactory
            // This prevents memory leaks in parallel execution
            DriverFactory.removeDriver();
        }
    }
}
