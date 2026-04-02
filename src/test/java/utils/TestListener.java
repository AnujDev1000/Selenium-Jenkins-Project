package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;

public class TestListener implements ITestListener {
    ExtentReports extent =  ExtentManager.getInstance();
    ExtentTest test;

    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getName());
        ExtentTestManager.setTest(test);
    }

    public void onTestSuccess(ITestResult result) {
        String path = ScreenshotUtil.captureScreenshot(result.getName());
        
        try {
            ExtentTestManager.getTest().addScreenCaptureFromPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onTestFailure(ITestResult result) {
        String screenshotPath = ScreenshotUtil.captureScreenshot(result.getName());

        ExtentTestManager.getTest().fail(result.getThrowable());
        try {
            ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }

}
