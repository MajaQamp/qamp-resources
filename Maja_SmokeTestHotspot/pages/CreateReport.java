package placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateReport {
   WebDriver driver;
    private String reportName = System.getProperty("reportName");
   private String location = "Sarajevo";
   private String radius = "0";


    public CreateReport(WebDriver driver) {
        this.driver = driver;
    }

    public void enterReportName(){
        WebElement enterReportName = driver.findElement(By.id("name"));
        enterReportName.sendKeys(reportName);
    }

    public void selectCategory(){
        WebElement selectCategory = driver.findElement(By.xpath("//*[@id=\"hotspot_poi_query\"]/div[2]/div/button/span"));
        selectCategory.click();
        WebElement selectAccommodation = driver.findElement(By.xpath("//*[@id=\"hotspot_poi_query\"]/div[2]/div/ul/div/li[2]/a/label"));
        selectAccommodation.click();
    }

    public String getAccommodation(){
        return driver.findElement(By.xpath("//*[@id=\"hotspot_poi_query\"]/div[2]/div/button/span")).getText();
    }

    public void enterLocation(){
        WebElement enterLocation = driver.findElement(By.name("location_name"));
        enterLocation.sendKeys(location);
    }

     public void setLatitude(){
     WebElement setLatitude= driver.findElement(By.id("city_lat"));
     setLatitude.sendKeys("43.8562586");
    }

    public void setLongitude(){
        WebElement setLongitude= driver.findElement(By.id("city_lng"));
        setLongitude.sendKeys("18.4130763");
    }

    public void selectRadius(){
        WebElement selectRadius = driver.findElement(By.id("radius"));
        selectRadius.sendKeys(radius);
    }

    public void submitReport(){
        WebElement submitReport = driver.findElement(By.xpath("//*[@id=\"hotspot_poi_query\"]/button"));
        submitReport.click();
    }
}
