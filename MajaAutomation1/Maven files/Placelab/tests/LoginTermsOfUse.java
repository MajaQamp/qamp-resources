package Placelab.tests;

import Placelab.utilities.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTermsOfUse {

    public String browser;
    public WebDriver driver;
    public String host = System.getProperty("host");
    public String targetURL = "https://demo.placelab.com/terms_of_service";

    @BeforeTest
    public void openBrowser() {

        driver = WebDriverSetup.getChromeDriver();
        this.driver.navigate().to(host);
    }

    @Test
    public void checkTermsOfUse() {
        //Click on the Terms of Use link and verify the corresponding page displays correctly
        WebElement openInSameTab = driver.findElement(By.linkText("Terms of Use"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('target','_self');", openInSameTab);
        openInSameTab.click();

        // Probala sam razne metode, izmedju ostalih i ovih par ispod, ali sam jedino sa ovom iznad uspjela da se
//        otvara u istom tabu i da driver saceka da se stranica ucita.

//        driver.findElement(By.linkText("Terms of Use")).click();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        WebDriverWait wait = new WebDriverWait(driver,10);
//        wait.until(ExpectedConditions.urlContains("terms_of_service"));
//        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

        Assert.assertEquals(this.driver.getCurrentUrl(), targetURL);


    }

    @AfterTest
    public void closeBrowser() {

        this.driver.close();
    }
}
