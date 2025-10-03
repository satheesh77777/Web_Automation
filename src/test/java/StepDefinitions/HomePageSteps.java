package StepDefinitions;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import base.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.HomePage;

public class HomePageSteps extends BaseClass {
    HomePage home;

    @Given("user Navigates To Flipkart Homepage")
    public void userNavigatesToFlipkartHomepage() {
        loadConfig();
        launchWebDriver();// make sure driver is initialized here
        home = new HomePage(DriverFactory.getDriver()); // now driver is ready

    }

    @And("user Searches For Product {string}")
    public void userSearchesFor(String productName) throws InterruptedException {
        home.SearchProduct(productName); // updated method name
        // Use explicit wait instead of Thread.sleep if possible
        Thread.sleep(2000);
    }

    @When("user Clicks On Search Icon")
    public void userClicksOnSearchIcon() {
        home.clicksSearchIcon(); // updated method name
    }


}
