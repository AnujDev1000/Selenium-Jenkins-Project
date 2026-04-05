package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    public static ExtentReports extent;
    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/Extent-Reports/index.html");
            spark.config().setReportName("Selenium Jenkins Project - Test Report");
            spark.config().setDocumentTitle("Test Results");
            spark.config().setOfflineMode(true);
            spark.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}
