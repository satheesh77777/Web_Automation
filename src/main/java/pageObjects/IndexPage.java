package pageObjects;

import actoinDriver.Action;
import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage extends BaseClass {
    Action action= new Action();
    @FindBy(xpath = "//span[@id='nav-link-accountList-nav-line-1']")
    WebElement signInButton;
    @FindBy(xpath = "//div[@id='nav-logo']")
    WebElement amazonLogo;
    @FindBy(xpath = "//input[@id='nav-search-submit-button']")
    WebElement searchButton;
    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    WebElement searchProductBox;
    public IndexPage(){
        PageFactory.initElements(driver,this);

    }
    public LogInPage clickOnSignIn(){
        action.click(driver,signInButton);
        return new LogInPage();
    }
    public boolean validateLogo(){
        return action.isDisplayed(driver,amazonLogo);

    }
    public String getTittle(){
       String amazonTittle = driver.getTitle();
       return amazonTittle;
    }



}
