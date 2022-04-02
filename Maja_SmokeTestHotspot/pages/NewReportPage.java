package placelab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewReportPage {
    WebDriver driver;
    private String reportName = System.getProperty("reportName");

    public NewReportPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getNameOfReport() {
        return driver.findElement(By.xpath("//*[@id=\"breadcrumb\"]/div/span[2]")).getText();
    }

    public void goToReportsPage() {
        WebElement goToReportsPage = driver.findElement(By.xpath("//*[@id=\"queries-nav-item\"]/a"));
        goToReportsPage.click();
    }

    public String getAnalysisInfo() {
        WebElement getAnalysisInfo = driver.findElement(By.xpath("//*[@id=\"hs_query_info\"]/div[1]/h5/span"));
        return getAnalysisInfo.getText();
    }

    public String getCategory() {
        WebElement getCategory = driver.findElement(By.xpath("//*[@id=\"hs_query_info\"]/div[4]/div/table/tbody/tr[6]/td[2]"));
        return getCategory.getText();
    }

    public String getUsernameAnalysisInfo() {
        WebElement getUsernameAnalysisInfo = driver.findElement(By.xpath("//*[@id=\"hs_query_info\"]/div[4]/div/table/tbody/tr[7]/td[2]"));
        return getUsernameAnalysisInfo.getText();
    }

    public WebElement getMapWidget() {
        WebElement mapWidget = driver.findElement(By.xpath("//*[@id=\"hotspot_map_info\"]"));
        return mapWidget;
    }

    public WebElement getTotalPOI() {
        WebElement totalPOI = driver.findElement(By.xpath("//*[@id=\"hotspot_total_poi_count\"]"));
        return totalPOI;
    }

    public WebElement getAttributePresence() {
        WebElement attributePresence = driver.findElement(By.xpath("//*[@id=\"hotspot_attribute_completeness\"]"));
        return attributePresence;
    }

    public WebElement getDistanceAnalysis() {
        WebElement distanceAnalysis = driver.findElement(By.xpath("//*[@id=\"hotspot_distance_analysis\"]"));
        return distanceAnalysis;
    }

    public WebElement getBayesian() {
        WebElement bayesian = driver.findElement(By.xpath("//*[@id=\"bayesian_average\"]"));
        return bayesian;
    }

    public WebElement getDistributionPerProvider() {
        WebElement distributionPerProvider = driver.findElement(By.xpath("//*[@id=\"hotspot_category_distribution_per_provider\"]"));
        return distributionPerProvider;
    }

    public WebElement getDistribution() {
        WebElement distribution = driver.findElement(By.xpath("//*[@id=\"category_distribution_per_provider\"]"));
        return distribution;
    }

    public WebElement getFrequentCategories() {
        WebElement frequentCategories = driver.findElement(By.xpath("//*[@id=\"raw_category_frequency\"]"));
        return frequentCategories;
    }

    public WebElement getReviewDistribution() {
        WebElement reviewDistribution = driver.findElement(By.xpath("//*[@id=\"review_distribution\"]"));
        return reviewDistribution;
    }

    public WebElement getPhotoDistribution() {
        WebElement photoDistribution = driver.findElement(By.xpath("//*[@id=\"photo_distribution\"]"));
        return photoDistribution;
    }
}

