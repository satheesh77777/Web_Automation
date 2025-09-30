package base;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

    public static Properties prop;

    // Load configuration from config.properties
    public void loadConfig() {
        prop = new Properties();
        String filePath = System.getProperty("user.dir") + "/configuration/config.properties";
        try (FileInputStream ip = new FileInputStream(filePath)) {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Launch WebDriver based on browser type
    public static WebDriver launchWebDriver() {
        if (prop == null) {
            throw new IllegalStateException("Properties not loaded. Call loadConfig() first.");
        }
        String browserName = prop.getProperty("browser");
        WebDriver driver = DriverFactory.getDriver(browserName);

        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
        return driver;
    }

    // Quit driver
    public static void quitDriver() {
        WebDriver driver = DriverFactory.getDriver();
        if (driver != null) {
            driver.quit();
            DriverFactory.removeDriver();
        }
    }
}
