package pageObjects;

import actoinDriver.Action;
import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class PlaceOrderPage extends BaseClass {

    // Action driver for common UI actions
    Action action = new Action();

    @FindBy(xpath = "//span[text()='Place Order']")
    private WebElement PlaceOrderButton;
    public  PlaceOrderPage() {
        // Initialize the PageFactory elements

        PageFactory.initElements(driver, this);
    }
    public void clickPlaceOrderButton() throws InterruptedException {
        System.out.println(action.getTitle(driver));

Thread.sleep(5000);
        action.click(driver,PlaceOrderButton);
//driver.findElement(By.xpath("//span[text()='Place Order']")).click();
        System.out.println(action.getTitle(driver));

    }
}
