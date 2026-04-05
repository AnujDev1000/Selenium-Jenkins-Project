package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    public static ExtentReports extent;
    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/extent-report.html");
            spark.config().setReportName("Selenium Jenkins Project - Test Report");
            spark.config().setDocumentTitle("Test Results");
            spark.config().setOfflineMode(true);
            spark.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);
            spark.config().setEncoding("utf-8");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("User", "Jenkins");
        }
        return extent;
    }
}
