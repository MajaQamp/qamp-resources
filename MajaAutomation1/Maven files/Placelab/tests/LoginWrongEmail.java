package Placelab.tests;

import Placelab.utilities.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginWrongEmail {

    public String browser;
    public WebDriver driver;
    public String host = System.getProperty("host");
    public String username = "wrongUsername";
    public String password = System.getProperty("password");
    public String errorMessage = "Invalid credentials!";


    @BeforeTest
    public void openBrowser() {

        driver = WebDriverSetup.getFirefoxDriver();
        this.driver.navigate().to(host);
    }

    @Test
    public void logInTryWithWrongEmail() {
        //Enter a wrong e-mail address
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
