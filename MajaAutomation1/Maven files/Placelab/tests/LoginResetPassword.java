package Placelab.tests;

import Placelab.utilities.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginResetPassword {
    public String browser;
    public WebDriver driver;
    public String host = System.getProperty("host");
    public String pageURL = "https://demo.placelab.com/password/forgot";
    public String username = System.getProperty("username");


    @BeforeTest
    public void openBrowser() {
        driver = WebDriverSetup.getFirefoxDriver();
        this.driver.navigate().to(host);
    }

    @Test
    public void resetPassword() {
        //Click on the link Forgot your Password?
        driver.findElement(By.xpath("//*[@id=\"password-area\"]/a")).click();

        //Verify the page Change your password displays
        Assert.assertEquals(driver.getCurrentUrl(), pageURL);

        //Enter an e-mail address
        driver.findElement(By.id("email")).sendKeys(username);

        //Click on Continue
        driver.findElement(By.name("commit")).click();

        //Verify that the page informing the user that an e-mail has been sent displays
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"login\"]/p")).getText(), "We have sent you a link to change your password");

    }

    @AfterTest
    public void closeBrowser() {

        this.driver.close();
    }

}
