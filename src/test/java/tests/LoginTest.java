package tests;

import java.io.ObjectInputFilter.Config;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ExtentTestManager;

public class LoginTest extends BaseTest{

    ConfigReader config = new ConfigReader();
    

    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(config.getUsername(), config.getPassword());
        Assert.assertTrue(getDriver().getTitle().contains("Guru99 Bank Manager HomePage"), "Login failed or incorrect page title.");
        ExtentTestManager.getTest().pass("Logged in successfully with username: " + config.getUsername());

    }
}
