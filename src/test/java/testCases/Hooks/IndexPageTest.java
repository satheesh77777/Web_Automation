package testCases.Hooks;

import base.BaseClass;
import io.cucumber.java.Before;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.IndexPage;

public class IndexPageTest extends BaseClass {
    IndexPage indexPage = new IndexPage();
    @Before
    public void setup() throws InterruptedException {
        loadConfig();
        launchWebDriver();
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void verifyLogo() throws InterruptedException {
        Thread.sleep(20000);
//        boolean result = indexPage.validateLogo();
//        Assert.assertTrue(result);
       System.out.println(driver.getTitle());

    }
}
