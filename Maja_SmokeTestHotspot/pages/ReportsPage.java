package placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReportsPage {
    WebDriver driver;
    private String reportName = System.getProperty("reportName");

    public ReportsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkboxReport(){
        WebElement checkboxReport = driver.findElement(By.xpath("//*[@id=\"table_queries\"]/tbody/tr[1]/td[2]/div"));
        checkboxReport.click();

    }

    public void deleteReport(){
        WebElement deleteReport = driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/div[5]/div/ul/li[3]/a/i"));
        deleteReport.click();
    }

    public void confirmDelete(){
        WebElement confirmDelete = driver.findElement(By.xpath("//*[@id=\"confirm-link\"]"));
        confirmDelete.click();
    }

    public void clickOnTheReport(){
        String path = "//div[@class='query_name']/a[text()='" + reportName + "']";
        WebElement report = driver.findElement(By.xpath(path));
        report.click();
    }

    public String getSuccessfullyDeleted(){
        return driver.findElement(By.xpath("//*[@id=\"alert-place\"]/div/text()")).getText();
    }

    public WebElement getSuccessMessage(){
        WebElement successMessage = driver.findElement(By.xpath("//*[@id=\"alert-place\"]/div"));
        return successMessage;
    }
}
