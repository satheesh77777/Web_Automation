package pageObjects;

import actoinDriver.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductsListPage {

    Action action = new Action();
    WebDriver driver; // thread-safe driver

    @FindAll({
            @FindBy(xpath = "//div[@class='aRL84z']//a"),
            @FindBy(xpath = "//button[@class='QqFHMw cNEU5Q J9Kkbj _7Pd1Fp']")
    })
    private List<WebElement> addToCartButtons; // ✅ should be a list

    @FindBy(xpath = "//div[@class='EjOX7q']")
    private WebElement newTabButton;

//    public ProductsListPage() {
//        this.driver = DriverFactory.getDriver(); // ✅ get thread-safe driver
//        PageFactory.initElements(driver, this);
//    }
    public ProductsListPage(WebDriver driver) {
        this.driver = driver; // ✅ get thread-safe driver
        PageFactory.initElements(driver, this);
    }

    public WebElement productTitle(String productName) {
        String xpath = String.format("//div[text()='%s']", productName);
        return driver.findElement(By.xpath(xpath));
    }

    public void clickAddToCartButton() throws InterruptedException {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabs.size() > 1) {
            driver.switchTo().window(tabs.get(1));
        } else {
            System.out.println("New tab not found.");
            return;
        }

        // Click all matching Add to Cart buttons
        for (WebElement button : addToCartButtons) {
            if (action.isDisplayed(driver, button)) {
                action.click(driver, button);
                System.out.println("Add to Cart button clicked.");
                return;
            }
        }
        System.out.println("Add to Cart button is not displayed.");
    }

    public void verifyPage() {
        String pageTitle = action.getTitle(driver);
        System.out.println(pageTitle);
    }

    public void clicksOnProduct(String productTitle) {
        action.click(driver, productTitle(productTitle));
    }
}
