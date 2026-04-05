package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class NewCustomerPage {
    WebDriver driver;

    public NewCustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By NewCustomerLink = By.linkText("New Customer");
    By CustomerName = By.name("name");
    By GenderMale = By.xpath("//input[@value='m']");
    By Dob = By.id("dob");
    By Address = By.name("addr");
    By City = By.name("city");
    By State = By.name("state");
    By Pin = By.name("pinno");
    By Mobile = By.name("telephoneno");
    By Email = By.name("emailid");
    By Password = By.name("password");
    By submitBtn = By.name("sub");

    public void navigateToNewCustomer() {
        driver.findElement(NewCustomerLink).click();
    }

    public void fillCustomerForm(String name, String dob, String address, String city, String state, String pin, String mobile, String email, String password) {
        driver.findElement(CustomerName).sendKeys(name);
        driver.findElement(GenderMale).click();
        driver.findElement(Dob).sendKeys(dob);
        driver.findElement(Address).sendKeys(address);
        driver.findElement(City).sendKeys(city);
        driver.findElement(State).sendKeys(state);
        driver.findElement(Pin).sendKeys(pin);
        driver.findElement(Mobile).sendKeys(mobile);
        driver.findElement(Email).sendKeys(email);
        driver.findElement(Password).sendKeys(password);
    }

    public void submitForm() {
        driver.findElement(submitBtn).click();
    }

    public String getCustomerID() {
        return driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
    }

    public String getSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='heading3']")));
        return driver.findElement(By.xpath("//p[@class='heading3']")).getText();
    }
}
