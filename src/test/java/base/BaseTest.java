package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

public class BaseTest {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>(); // Use ThreadLocal to manage WebDriver instances for parallel execution
    public ConfigReader config = new ConfigReader(); 

    @BeforeMethod
    public void setUp() {
        String browser = System.getProperty("browser")
                != null ? System.getProperty("browser") : config.getBrowser(); // Allow override via system property
        // System.out.println("Selected browser: " + browser); // Debugging line
        // Initialize WebDriver based on the selected browser
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;  
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new org.openqa.selenium.firefox.FirefoxDriver());
                break;  
            case "edge":
                WebDriverManager.edgedriver().setup();  
                driver.set(new org.openqa.selenium.edge.EdgeDriver());
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);      //
        }
        getDriver().manage().window().maximize();
        getDriver().get(config.getUrl());
    }

    public static WebDriver getDriver() {
        return driver.get(); // Return the WebDriver instance for the current thread
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            getDriver().quit();
        }
    }
}
