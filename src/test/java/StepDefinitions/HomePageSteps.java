package StepDefinitions;

import io.cucumber.java.en.And;
import base.BaseClass;
import pageObjects.HomePage;

public class HomePageSteps extends BaseClass{
//    LogInPage logInPage1 = new LogInPage();

    HomePage homePage = new HomePage();

    @And("user Searches For Product {string}")
    public void userSearchesFor(String productName) throws InterruptedException {
        homePage.SearchProduct(productName);
        Thread.sleep(3000);
    }

    @And("user Clicks On Search Icon")
    public void userClicksOnSearchIcon() {
        homePage.clicksSearchIcon();
    }

    
}
