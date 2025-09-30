package StepDefinitions;

import base.BaseClass;
import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.ProductSummaryPage;

public class ProductSummaryPageSteps extends BaseClass {

    ProductSummaryPage productSummaryPage = new ProductSummaryPage(DriverFactory.getDriver());
    @Then("user Verifies Product Summary Page")
    public void userVerifiesProductSummaryPage(){
        productSummaryPage.verifyProductSummaryPage();
    }

    @And("user Navigates To Product Summary Tab")
    public void userNavigatesToProductSummaryTab() {
        productSummaryPage.verifyUserNavigatesToProductSummaryTab();

    }
    @And("user Verifies Product Price {string}")
    public void userVerifiesProductPrice(String expectedProductPrice){
        productSummaryPage.verifyProductPrice(expectedProductPrice);
    }
    @And ("user Verifies Display Size {string} In Highlight Section")
    public void userVerifiesDisplaySizeInHighlightSection(String expectedDisplaySize){
        productSummaryPage.verifyDisplaySizeInHighlightSection(expectedDisplaySize);
    }
    @And("user Verifies ROM Size {string} In Highlight Section")
    public void userVerifiesROMSizeInHighlightSection(String expectedRomSize){
        productSummaryPage.verifyROMSizeInHighlightSection(expectedRomSize);

    }
    @And ("user Verifies Battery Type {string}")
    public void userVerifiesBatteryType(String expectedBatteryType){
        productSummaryPage.verifyBatteryType(expectedBatteryType);

    }
    @And("user Clicks On Read More Button")
    public void userClicksOnReadMoreButton(){
        productSummaryPage.clicksOnReadMoreButton();

    }


    @Then("user Verifies Operating System {string}")
    public void userVerifiesOperatingSystem(String operatingSystem) {
        productSummaryPage.verifyOperatingSystem(operatingSystem);

    }
    @And ("user Verifies Processor Brand {string}")
    public void userVerifiesProcessorBand(String expProcessorBrand){
    productSummaryPage.verifyProcessorBrand(expProcessorBrand);
    }
}
