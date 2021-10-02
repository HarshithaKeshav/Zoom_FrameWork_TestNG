package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public WebDriver driver;
    public Properties properties;

    public WebDriver initializeDriver() throws IOException {
         properties = new Properties();
        FileInputStream fis = new FileInputStream("/Users/harshithakeshav/IdeaProjects/Zoom_FrameWork_TestNG/src/main/resources/data.properties");
        properties.load(fis);
        String browserName = properties.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "/Users/harshithakeshav/Downloads/chromedriver");
             driver = new ChromeDriver();
        }
        else if (browserName.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.firefox.driver", "/Users/harshithakeshav/Downloads/geckodriver");
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
        String destinationFile = System.getProperty("user.dir")+"/src/main/java/reports/"+testCaseName+".png";
        FileUtils.copyFile(sourceFile, new File(destinationFile));
        return destinationFile;
    }
}
