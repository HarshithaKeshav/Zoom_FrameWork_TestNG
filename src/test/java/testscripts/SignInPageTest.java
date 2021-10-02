package testscripts;

import base.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.SignInPage;

import java.io.IOException;

public class SignInPageTest extends Base {

    public WebDriver driver;

    private static Logger log = LogManager.getLogger(SignInPageTest.class.getName());
    @BeforeTest
    public void setUp() throws IOException {
        driver=initializeDriver();
        log.info("Initialize the driver");
        driver.get(properties.getProperty("url"));
        log.info("Launch the url");

    }

    @Test(dataProvider = "getData")
    public void signInTest(String username, String password){
        SignInPage signInPage = new SignInPage(driver);
        signInPage.enterEmail(username);
        log.debug("Enter the username");
        signInPage.enterPassword(password);
        log.debug("Enter the password");
        signInPage.clickSignIn();
        log.debug("Click on the sign in button");
    }

    @DataProvider
    public Object[][] getData(){
        Object[][] data = new Object[2][2];
        data[0][0] = "abc@gmail.com";
        data[0][1] = "abc123";
        data[1][0] =  "xyz@gmail.com";
        data[1][1] =  "xy123";
        return data;
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
        log.info("Quit the browser");
    }


}
