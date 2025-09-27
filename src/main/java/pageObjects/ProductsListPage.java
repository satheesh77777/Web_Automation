package pageObjects;

import actoinDriver.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

import static base.BaseClass.driver;

public class ProductsListPage {

    Action action = new Action();

    @FindAll({
            @FindBy(xpath = "//div[@class='aRL84z']//a"),
            @FindBy(xpath = "//button[@class='QqFHMw cNEU5Q J9Kkbj _7Pd1Fp']")
    })
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[@class='EjOX7q']")
    private WebElement newTabButton;


    public WebElement productTittle(String productName) {
        String xpath = String.format("//div[text()='"+productName+"']");
        return driver.findElement(By.xpath(xpath));
    }




    /**
     * Constructor - initializes web elements using PageFactory.
     */
    public ProductsListPage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks the Add to Cart button in a new tab.
     */


    public void clickAddToCartButton() throws InterruptedException {
        // Switch to the new tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) {
            driver.switchTo().window(tabs.get(1));
        } else {
            System.out.println("New tab not found.");
            return;
        }

        // Wait and then try to click the Add to Cart button

        if (action.isDisplayed(driver, addToCartButton)) {
            action.click(driver, addToCartButton);
            System.out.println("Add to Cart button is displayed and clicked.");
        } else {
            System.out.println("Add to Cart button is not displayed.");
        }
    }
    public void verifyPage(){
    String PageTittle = action.getTitle(driver);
    System.out.println(PageTittle);
    }

    public void clicksOnProduct(String productTittle){
        action.click(driver,productTittle(productTittle));

    }
}
