package pageObjects;

import actoinDriver.Action;
import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Scanner;

/**
 * Page Object Model for Login Page.
 */
public class LogInPage extends BaseClass {

    // Action driver for common UI actions
    Action action = new Action();

    // Web element for the login button
    @FindBy(xpath = "//span[contains(text(),'Login')]")
    private WebElement loginButton;
//    @FindBy(xpath = "//span[contains(text(),'Enter Email/Mobile number')]")
    @FindBy(xpath = "(//input[@type=\"text\"])[2]")
    private WebElement mobileNumberField;
    @FindBy(xpath = "//button[contains(text(),'Request OTP')]")
    private WebElement requestOTPButton;
    @FindBy(xpath = "//button[contains(text(),'Verify')]")
    private WebElement verifyButton;

    /**
     * Constructor - initializes web elements using PageFactory.
     */
    public LogInPage() {
        PageFactory.initElements(driver, this);
    }

    public void clickOnSignIn() {
        action.click(driver, loginButton);
    }
    public boolean enterMobileNumber(String mobileNumber) throws InterruptedException {
        // Check if the mobile number field is displayed
        if (action.isDisplayed(driver,mobileNumberField)) {
            // Click on the mobile number field
            action.click(driver, mobileNumberField);
            Thread.sleep(2000);
            // Enter the mobile number
            action.type(mobileNumberField, mobileNumber);
            return true;
        }
        // If the mobile number field is not displayed, return false
        return false;
    }
    public void clickOnContinueButton() {
        action.click(driver, requestOTPButton);
    }
    public void enterOTPManually(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the OTP received on your mobile number:");
        String otp = sc.nextLine();
        // Assuming there's a method to enter OTP in the UI, you would call it here
//        action.type()
    }
    public void clickOnVerifyButton() throws InterruptedException {
        Thread.sleep(9000);
        action.click(driver, verifyButton);
    }
    public void verifyBothText(String text1,String text2){
        String Str;
        if(text2==null){
            Str="three";
        }else
            Str = text2;
        System.out.println("The value of the str string is: " + Str);
        System.out.println("The value of the text1 string is: " + text1);
        System.out.println("The value of the text2 string is: " + text2);
    }
}
