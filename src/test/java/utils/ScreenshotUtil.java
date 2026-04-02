package utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import base.BaseTest;

public class ScreenshotUtil {
    public static String captureScreenshot(String testName) {
        WebDriver driver = BaseTest.getDriver();

        String path = System.getProperty("user.dir") + "/target/screenshots/" + testName + ".png";
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();

            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
}
