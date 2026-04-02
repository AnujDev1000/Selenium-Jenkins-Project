package tests;


import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.NewCustomerPage;
import utils.ConfigReader;
import utils.ExtentTestManager;

public class NewCustomerTest extends BaseTest {
    @Test
    public void testCreateNewCustomer() {
        ConfigReader config = new ConfigReader();
        
        LoginPage loginPage = new LoginPage(getDriver());
        System.out.println(getDriver());
        loginPage.login(config.getUsername(), config.getPassword());
        ExtentTestManager.getTest().info("Logged in successfully with username: " + config.getUsername());

        NewCustomerPage newCustomerPage = new NewCustomerPage(getDriver());
        newCustomerPage.navigateToNewCustomer();

        String email = "test" + System.currentTimeMillis() + "@example.com";
        newCustomerPage.fillCustomerForm(
            "John",
            "12-04-1999",
            "street01",
            "newyourk",
            "NY",
            "411057",
            "9999999999",
            email,
            "pass123"
        );
        newCustomerPage.submitForm();

        String successMessage = newCustomerPage.getSuccessMessage();
        assert successMessage.contains("Customer Registered Successfully!!!") : "Customer creation failed.";
        String customerId = newCustomerPage.getCustomerID();
        ExtentTestManager.getTest().pass("New customer created successfully with ID: " + customerId);
    }    
}
