package placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Login {


    private WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyLoginPage(final String host) {
        Assert.assertEquals(driver.getCurrentUrl(), host);
        Assert.assertEquals(driver.getTitle(), "PlaceLab");
        getPlacelabLogo().isDisplayed();
    }

    public WebElement getPlacelabLogo() {
        WebElement logo = driver.findElement(By.xpath("//*[@id=\"login\"]/img"));
        return logo;
    }

    public void enterPassword(final String password) {
        WebElement enterPassword = driver.findElement(By.name("password"));
        enterPassword.sendKeys(password);
    }

    public void enterUsername(final String username) {
        WebElement enterUsername = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        enterUsername.sendKeys(username);
    }

    public String loginFailedErrorMessage() {
        WebElement loginFailedErrorMessage = driver.findElement(By.xpath("//*[@id=\"login\"]/div[1]/div/div"));
        return loginFailedErrorMessage.getText();
    }

    //Submit Login form
    public void submit() {
        WebElement submit = driver.findElement(By.xpath("//*[@id=\"login_form\"]/input[4]"));
        submit.click();
    }
}

