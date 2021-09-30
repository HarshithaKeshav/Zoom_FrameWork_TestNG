package testscripts;

import base.Base;
import org.testng.annotations.Test;

import java.io.IOException;

public class SignInPageTest extends Base {

    @Test
    public void signInPageNavigation() throws IOException {
        driver = initializeDriver();
        driver.get("https://zoom.us/signin");


    }
}
