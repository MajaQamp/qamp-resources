package Placelab.tests;

import Placelab.utilities.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginWrongPassword {


    public String browser;
    public WebDriver driver;
    public String host = System.getProperty("host");
    public String username = System.getProperty("username");
    public String password = "wrongPassword";
    public String errorMessage = "Invalid credentials!";


    @BeforeTest
    public void openBrowser() {

        driver = WebDriverSetup.getFirefoxDriver();
        this.driver.navigate().to(host);
    }

    @Test
    public void loginWithWrongPassword() {
        //Enter an e-mail address and a wrong password
        driver.findElement(By.id("email")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);

        //Click on the Login button
        driver.findElement(By.xpath("//*[@id=\"login_form\"]/input[4]")).click();

        //Verify the error message displays
        Assert.assertEquals(this.driver.findElement(By.xpath("//*[@id=\"login\"]/div[1]/div/div")).getText(), errorMessage);
    }

    @AfterTest
    public void closeBrowser() {

        this.driver.close();
    }
}
