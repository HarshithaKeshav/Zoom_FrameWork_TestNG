package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
    static ExtentReports report;

    public static ExtentReports getReportObject(){
        String path = System.getProperty("/Users/harshithakeshav/IdeaProjects/Zoom_FrameWork_TestNG/src/main/java/reports")+"/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        report = new ExtentReports();
        report.attachReporter(reporter);
        report.setSystemInfo("Tester", "Harshitha");

        return report;
    }

}
