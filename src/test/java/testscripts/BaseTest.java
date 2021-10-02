package testscripts;

import base.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;

public class BaseTest extends Base {
    public WebDriver driver;
    private static Logger log = LogManager.getLogger(BaseTest.class.getName());

    @BeforeTest
    public void signInPageNavigation() throws IOException {
        driver = initializeDriver();
        log.info("Initialize the driver from the base class");
    }

    @Test
    public void test(){
        driver.get(properties.getProperty("url"));
        log.debug("Launch the URL");
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        log.info("Closing the browser");
    }

}
