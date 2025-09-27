package pageObjects;

import actoinDriver.Action;
import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static base.BaseClass.driver;

public class SearchResultPage extends BaseClass {
    // Action driver for common UI actions
    Action action = new Action();

    // PageFactory elements for the Search Result Page
    // Add @FindBy annotations for the elements you want to interact with


    @FindBy(xpath = "//div[contains(text(),'4★ & above')]")
    private static WebElement ratingButton;
    @FindBy(xpath = "//a[contains(text(),'Adrenex by Flipkart R201 Combo')]")
    private static WebElement selectProductWithName;


    /**
     * Constructor - initializes web elements using PageFactory.
     */
    public SearchResultPage() {
// Initialize the PageFactory elements
        PageFactory.initElements(driver, this);
        // Initialize elements if needed
    }

    public void selectCustomerRating(String ProductRating) throws InterruptedException {
// Check if the rating button is displayed
        if (action.isDisplayed(driver, ratingButton)) {
            Thread.sleep(2000);
            // Click on the rating button
            action.click(driver, ratingButton);
            System.out.println("Rating button is displayed and clicked.");
        } else {
            System.out.println("Rating button is not displayed.");
        }
    }

    public void selectProductWithName(String ProductName) throws InterruptedException {
// Check if the product with the specified name is displayed
        if (action.isDisplayed(driver, selectProductWithName)) {
            Thread.sleep(2000);
            // Click on the product with the specified name
            action.click(driver, selectProductWithName);
            System.out.println("Product with name " + ProductName + " is displayed and clicked.");
        } else {
            System.out.println("Product with name " + ProductName + " is not displayed.");

        }
    }


}
