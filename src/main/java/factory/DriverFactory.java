package factory;

import base.BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Initialize driver with browser from config if null
    public static WebDriver getDriver(String browser) {
        if (browser == null) {
            // fallback to browser from config.properties
            if (BaseClass.prop != null) {
                browser = BaseClass.prop.getProperty("browser");
            } else {
                throw new IllegalArgumentException("Browser is not specified and config is not loaded.");
            }
        }

        if (driver.get() == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver());
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                    break;
                default:
                    throw new IllegalArgumentException("Browser not supported: " + browser);
            }
        }
        return driver.get();
    }

    // Get driver without passing browser (must be initialized)
    public static WebDriver getDriver() {
        WebDriver drv = driver.get();
        if (drv == null) {
            throw new IllegalStateException("WebDriver has not been initialized. Call getDriver(browser) first.");
        }
        return drv;
    }

    // Remove driver from ThreadLocal
    public static void removeDriver() {
        driver.remove();
    }
}
