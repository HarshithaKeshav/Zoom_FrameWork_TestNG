package listeners;
import base.Base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentReport;

import java.io.IOException;

public class Listeners extends Base implements ITestListener{

    ExtentTest test;
    ExtentReports report = ExtentReport.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
        test= report.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult arg0) {
        // TODO Auto-generated method stub
        extentTest.get().log(Status.PASS, "Test Passed");

    }

    @Override
    public void onTestFailure(ITestResult result){
       extentTest.get().fail(result.getThrowable());
        WebDriver driver = null;
        String methodName = result.getMethod().getMethodName();
        try {
            driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch(Exception e){
            e.printStackTrace();
        }


        try {
            extentTest.get().addScreenCaptureFromPath(getScreenShot(methodName,driver), result.getMethod().getMethodName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext arg0) {
        // TODO Auto-generated method stub

        report.flush();

    }
}
