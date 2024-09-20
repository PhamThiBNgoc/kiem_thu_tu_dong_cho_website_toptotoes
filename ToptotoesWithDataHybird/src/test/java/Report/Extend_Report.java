package Report;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.Status;

public class Extend_Report {
    public static ExtentReports extent;
    public static ExtentTest test;

    public static void startReport() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public static void startTest(String testName) {
        if (extent == null) {
            startReport();
        }
        test = extent.createTest(testName);
    }

    public static void logPass(String message) {
        test.log(Status.PASS, message);
    }

    public static void logFail(String message) {
        test.log(Status.FAIL, message);
    }

    public static void attachScreenshot(String screenshotPath) {
        test.addScreenCaptureFromPath(screenshotPath);
    }

    public static void endReport() {
    	if (extent != null) {
            extent.flush();
        }
    }
}
