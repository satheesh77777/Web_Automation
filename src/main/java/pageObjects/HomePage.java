package pageObjects;

import actoinDriver.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {
    Action action = new Action();
    WebDriver driver; // instance driver from DriverFactory

    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchButton;

    @FindBy(xpath = "//textarea[@title='Search']")
    WebElement googleSearchBox;

    @FindBy(xpath = "//input[@name='q']")
    WebElement searchProductBox;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.action = new Action();
        PageFactory.initElements(driver, this);
    }
    public void flipkartHomepage(){

    }

    public void SearchProduct(String productName) throws InterruptedException {
        Thread.sleep(2000); // better use explicit wait instead of Thread.sleep
action.click(driver,searchProductBox);
        action.type(searchProductBox,productName);
    }
    public void clicksSearchIcon(){
        action.click(driver,searchButton);
    }


}
