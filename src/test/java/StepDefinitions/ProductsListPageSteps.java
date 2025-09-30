package StepDefinitions;

import base.BaseClass;
import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.ProductsListPage;

public class ProductsListPageSteps extends BaseClass {
    ProductsListPage productsListPage = new ProductsListPage(DriverFactory.getDriver());
    @And("User adds the product to the cart")
    public void userAddsTheProductToTheCart() throws InterruptedException {
        productsListPage.clickAddToCartButton();
    }

    @Then("user Verifies Products List Page")
    public void userVerifiesProductsListPage() {
        productsListPage.verifyPage();
        
    }


    @When("user Clicks On Product Tittle {string}")
    public void userClicksOnProductTittle(String productTittle) {
        productsListPage.clicksOnProduct(productTittle);

    }
}
