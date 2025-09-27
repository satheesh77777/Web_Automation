package pageObjects;

import actoinDriver.Action;
import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeliveryAddressPage extends BaseClass {
    Action action = new Action();

    @FindBy(xpath = "//input[@name='name']")
    WebElement nameFld;
    @FindBy(xpath = "//input[@name='phone']")
    WebElement phoneFld;
    @FindBy(xpath = "//input[@name='pincode']")
    WebElement pinCodeFld;
    @FindBy(xpath = "//input[@name='addressLine2']")
    WebElement localityFld;
    @FindBy(xpath = "//input[@name='addressLine1']")
    WebElement addressFld;
    @FindBy(xpath = "//input[@name='city']")
    WebElement cityFld;
    @FindBy(xpath = "//input[@name='state']")
    WebElement stateFld;
    @FindBy(xpath = "//span[text()='Home (All day delivery)']")
    WebElement addressTypeButton;
    @FindBy(xpath = "//div[@class='VTUEC-']//span[contains(text(),'9666837134')]")
    WebElement deliveryAddressButton;
    @FindBy(xpath = "//button[text()='Deliver Here']")
    WebElement deliveryHereButton;

    public DeliveryAddressPage()
    {
        PageFactory.initElements(driver, this);
    }
    public void selectRaviAddress() throws InterruptedException {
        Thread.sleep(5000);
        action.click(driver,deliveryAddressButton);

    }
    public void clickDeliveryHereButton(){
        action.click(driver,deliveryHereButton);
    }




}
