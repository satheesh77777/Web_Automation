package StepDefinitions;

import base.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.LogInPage;

public class LogInPageSteps extends BaseClass {
    LogInPage logInPage = new LogInPage();
    @Given("user Navigates To Flipkart Homepage")
    public void userNavigatesToFlipkartHomepage() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println(driver.getTitle());

    }
    @Given("User clicks on the sign-in button")
    public void userClicksOnTheLogInButton() throws InterruptedException {
        logInPage.clickOnSignIn();
    }

    @Then("User enters {string}")
    public void userEnters(String mobileNumber) throws InterruptedException {
        logInPage.enterMobileNumber(mobileNumber);
        Thread.sleep(3000);
    }

    @And("User clicks on requestOTP button")
    public void userClicksOnContinueButton() {
        logInPage.clickOnContinueButton();

    }

    @Then("User clicks on verify button")
    public void userClicksOnVerifyButton() throws InterruptedException {
        logInPage.clickOnVerifyButton();
    }

    @And("User verifies {string}")
    public void userVerifies(String arg0,String arg1) {
        logInPage.verifyBothText(arg0,arg1);
    }

    @And("User verifies {string} and {string}")
    public void userVerifiesAndTwo(String arg0, String arg1) {
        logInPage.verifyBothText(arg0,arg1);
    }
}
