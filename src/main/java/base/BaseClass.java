package base;

import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

    public static Properties prop;
    public static WebDriver driver;
    public void loadConfig() {
        prop = new Properties();
        String filePath = System.getProperty("user.dir") + "/configuration/config.properties";
        System.out.println("File path: " + filePath);

        try {
            FileInputStream ip = new FileInputStream(filePath);
            prop.load(ip);
            System.out.println("driver: " + driver);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void launchWebDriver() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        String browserName = prop.getProperty("browser");
        System.out.println("browserName: " + browserName);

        if (browserName.contains("Chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.contains("FireFox")) {
            driver = new FirefoxDriver();
        } else if (browserName.contains("IE")) {
            driver = new InternetExplorerDriver();
        }

        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));

    }


}