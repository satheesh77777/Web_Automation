package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    // Thread-safe WebDriver for parallel execution
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Get driver (initialize if null)
    public static WebDriver getDriver(String browser) {
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

    // Get driver without browser (already initialized)
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Remove driver from ThreadLocal (cleanup)
    public static void removeDriver() {
        driver.remove();
    }
}
