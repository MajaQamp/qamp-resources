package placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public String getUserRole() {
        return driver.findElement(By.id("user-role")).getText();
    }

    public void signOut() {
        driver.findElement(By.id("user-name")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/ul[2]/li/ul/li[4]/a")).click();
    }

    public void accessHotspot() {
        driver.findElement(By.id("create-menu")).click();
    }

    public void selectHotspot() {
        WebElement selectHotspot = driver.findElement(By.id("hotspotareaanalysis"));
        selectHotspot.click();
    }
}
