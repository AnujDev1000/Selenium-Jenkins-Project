package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

public class BaseTest {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public ConfigReader config = new ConfigReader();

    @BeforeMethod
    public void setUp() {
        String browser = config.getBrowser();
        if (browser.equalsIgnoreCase("chrome")) {
             WebDriverManager.chromedriver().setup();
             driver.set(new ChromeDriver());
        }
        getDriver().manage().window().maximize();
        getDriver().get(config.getUrl());
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            getDriver().quit();
        }
    }
}
