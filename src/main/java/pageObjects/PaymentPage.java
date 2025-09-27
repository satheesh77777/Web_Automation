package pageObjects;

import actoinDriver.Action;
import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends BaseClass {

    Action action = new Action();

    @FindBy(xpath = "//*[contains(text(),'Cash on Delivery')]")
    private WebElement modeOffPayment;

    @FindBy(xpath = "//button[@class=\"QqFHMw DIy-fT _7Pd1Fp\"]A")
    private WebElement confirmOrderButton;

    public PaymentPage() {
        PageFactory.initElements(driver, this);
    }

        public void clickOnCashOnDeliveryButton(){
            action.click(driver,modeOffPayment);
        }
        public void clickOnConfirmOrderButton() throws InterruptedException {
        Thread.sleep(10000);
        action.click(driver,confirmOrderButton);

        }

    }

