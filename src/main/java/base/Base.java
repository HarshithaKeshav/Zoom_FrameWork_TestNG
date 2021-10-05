package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public WebDriver driver;
    public Properties properties;
    String ProjDir = System.getProperty("user.dir");

    public WebDriver initializeDriver() throws IOException {
         properties = new Properties();

        FileInputStream fis = new FileInputStream(ProjDir+"/src/main/resources/data.properties");
        properties.load(fis);

        //To send parameters through maven command. mvn test -Dbrowser=chrome
        //String browserName = System.getProperty("browser");

        //To send parameters through data.properties.
        String browserName = properties.getProperty("browser");

        if (browserName.contains("chrome")){
            System.setProperty("webdriver.chrome.driver", ProjDir+"/src/main/resources/drivers/chromedriver");
            ChromeOptions options = new ChromeOptions();

            if (browserName.contains("headless")){
                options.addArguments("--headless");
            }

             driver = new ChromeDriver(options);
        }
        else if (browserName.contains("firefox")){
            System.setProperty("webdriver.gecko.driver", ProjDir+"/src/main/resources/drivers/geckodriver");
             driver = new FirefoxDriver();
        }
        else {
             driver = new ChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }


    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
      TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        String destinationFile = ProjDir+"/src/main/java/reports/"+testCaseName+".png";
        FileUtils.copyFile(sourceFile, new File(destinationFile));
        return destinationFile;
    }
}
