package factory;

import base.BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    // ThreadLocal ensures each test thread gets its own WebDriver instance
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Get WebDriver instance.
     * If not already created, it initializes based on browser type.
     * Browser name is passed from config.properties via BaseClass.
     */
    public static WebDriver getDriver(String browser) {

        // Fallback to config.properties if browser is not provided
        if (browser == null && BaseClass.prop != null) {
            browser = BaseClass.prop.getProperty("browser");
        }

        if (browser == null) {
            throw new IllegalArgumentException("Browser is not specified (either argument or config.properties).");
        }

        // Initialize only once per thread
        if (driver.get() == null) {
            boolean isHeadless = Boolean.parseBoolean(BaseClass.prop.getProperty("headless", "false"));

            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (isHeadless) {
                        // Headless means browser runs without UI
                        chromeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");
                    }
                    driver.set(new ChromeDriver(chromeOptions));
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (isHeadless) {
                        firefoxOptions.setHeadless(true);
                    }
                    driver.set(new FirefoxDriver(firefoxOptions));
                    break;

                default:
                    throw new IllegalArgumentException("Browser not supported: " + browser);
            }
        }
        return driver.get();
    }

    /**
     * Get already initialized WebDriver (no new browser is created).
     */
    public static WebDriver getDriver() {
        WebDriver drv = driver.get();
        if (drv == null) {
            throw new IllegalStateException("WebDriver not initialized. Call getDriver(browser) first.");
        }
        return drv;
    }

    /**
     * Clean up WebDriver after test (quit + remove from ThreadLocal).
     */
    public static void removeDriver() {
        driver.remove();
    }
}
