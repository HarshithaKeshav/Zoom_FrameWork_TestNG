package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
    public WebDriver driver;

    @FindBy(id = "email")
    WebElement email_address;

    @FindBy(id = "password")
    WebElement enter_password;

    @FindBy(xpath = "//div[@class='signin']/button")
    WebElement sign_in;

    public SignInPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void enterEmail(String username){
        email_address.clear();
        email_address.sendKeys(username);
    }

    public void enterPassword(String password){
        enter_password.clear();
        enter_password.sendKeys(password);
    }

    public void clickSignIn(){
        sign_in.click();
    }


}
