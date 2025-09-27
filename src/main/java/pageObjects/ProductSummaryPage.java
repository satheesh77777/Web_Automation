package pageObjects;

import actoinDriver.Action;
import base.BaseClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class ProductSummaryPage extends BaseClass {
    Action action = new Action();

    @FindBy(xpath = "//div[contains(text(),'Total Payable')]")
    private WebElement verifyAmount;
    @FindBy(xpath = "//button[contains(text(),'CONTINUE')]")
    private WebElement continueButton;

    @FindBy(xpath = "//a[text()='2 TB']")
    private WebElement storageSize;

    @FindBy(xpath = "//div[@class='Nx9bqj CxhGGd']")
    private WebElement productPrice;

    @FindBy(xpath = "//div[text()='Highlights']//..//li[2]")
    private WebElement displaySize;

    @FindBy(xpath = "//td[text()='Battery Type']//following-sibling::td//ul//li")
    private WebElement batteryType;

    @FindBy(xpath = "//button[text()='Read More']")
    private WebElement readMoreButton;

    @FindBy(xpath = "//td[text()='Operating System']//following-sibling::td")
    private WebElement operatingSystem;

    @FindBy(xpath = "//td[text()='Processor Brand']//following-sibling::td")
    private WebElement processorBrand;



    private String romSize(String romSize){
        String rom = "//div[@class='xFVion']//li[contains(text(),'"+romSize+"')]";
        return rom;
    }




    public ProductSummaryPage(){
        PageFactory.initElements(driver,this);
    }
public void verifyAmount(){
        action.isDisplayed(driver,verifyAmount);
}
public void clickContinueButton(){
        action.click(driver,continueButton);
}
public void verifyProductSummaryPage(){
      String summaryPage = action.getTitle(driver);
      System.out.println(summaryPage);
}

public void verifyUserNavigatesToProductSummaryTab(){
    // Switch to the new tab
    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

    if (tabs.size() > 1) {
        driver.switchTo().window(tabs.get(1));
        System.out.println("new Tab Found Successfully.");

    } else {
        System.out.println("new Tab  Not Found.");

    }
    }

    public void verifyProductPrice(String expectedProductPrice){
        String actualProductPrice = productPrice.getText();
        productPrice.isDisplayed();
        System.out.println("actual product price :"+actualProductPrice);
        System.out.println("expected product price :"+expectedProductPrice);
        Assert.assertEquals(actualProductPrice, expectedProductPrice, "Product price  does not match!");

    }
    public void verifyDisplaySizeInHighlightSection(String expectedDisplaySize){
        String actualDisplaySize =displaySize.getText();
        System.out.println("actual display size :"+actualDisplaySize);
        System.out.println("expected display size :"+expectedDisplaySize);
        Assert.assertTrue(actualDisplaySize.contains(expectedDisplaySize),"Display size does not exist in actual display");
    }

    public void verifyROMSizeInHighlightSection(String expectedRomSize){
        String actualRomSize = driver.findElement(By.xpath(romSize(expectedRomSize))).getText();
        System.out.println("Actual Rom size :"+actualRomSize);
        Assert.assertTrue(actualRomSize.contains(expectedRomSize),"ROM Size does not match");


    }
    public void verifyBatteryType(String expectedBatteryType){
        String actualBatteryType = batteryType.getText();
        System.out.println("actual battery type :"+actualBatteryType);
        Assert.assertEquals(actualBatteryType,expectedBatteryType,"Battery Type does not match");

    }

    public void clicksOnReadMoreButton(){
        action.click(driver,readMoreButton);

    }
    public void verifyOperatingSystem(String expOperatingSystem){
        String actualOperatingSystem = operatingSystem.getText();
        Assert.assertEquals(actualOperatingSystem,expOperatingSystem,"Operating System does not match");

    }

    public void verifyProcessorBrand(String expProcessorBrand){
        String actualProcessorSize = processorBrand.getText();
        Assert.assertEquals(actualProcessorSize,expProcessorBrand,"processor brand name does not match");
    }


}


