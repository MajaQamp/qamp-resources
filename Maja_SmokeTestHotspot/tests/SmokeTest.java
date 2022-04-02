package placelab.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import placelab.pages.*;
import placelab.utilities.WebDriverSetup;

import java.util.concurrent.TimeUnit;

public class SmokeTest {
    public WebDriver driver;
    private SoftAssert softAssert = new SoftAssert();
    public String host = System.getProperty("host");
    public String user = System.getProperty("user");
    private String username = System.getProperty("username");
    private String password = System.getProperty("password");
    private String homePageURL = System.getProperty("homepage");
    private String hotspotURL = System.getProperty("hotspotURL");
    private String reportsURL = System.getProperty("reportsURL");
    private String reportName = System.getProperty("reportName");
    private Login login;
    private HomePage homePage;
    private CreateReport createReport;
    private ReportsPage reportsPage;
    private NewReportPage newReportPage;

    @Parameters({"browser"})

    @BeforeSuite(alwaysRun = true)
    public void initDriver(String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        login = new Login(driver);
        homePage = new HomePage(driver);
        createReport = new CreateReport(driver);
        reportsPage = new ReportsPage(driver);
        newReportPage = new NewReportPage(driver);
    }


    @BeforeTest(alwaysRun = true, description = "Verify that the user can open Placelab")
    public void openApp() {

        driver.navigate().to(host);
        login.verifyLoginPage(host);
    }


    @Test(priority = 1, description = "Verify that the user can log in to Placelab with valid credentials",
            suiteName = "Smoke Test")
    public void testLoginPage() {

        //Fill out the login parameters
        login.enterUsername(username);
        login.enterPassword(password);

        //Click on the login button
        login.submit();


        //Validate that the user is successfully logged in
        Assert.assertEquals(driver.getCurrentUrl(), homePageURL, "The right URL doesn't display after" +
                "the user logs in");
        softAssert.assertEquals(homePage.getUserRole(), "Group Admin", "Logged user has an " +
                "invalid user role");
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Verify that the user can access the page to" +
            " create a Hotspot Area Analysis Report", suiteName = "Smoke Test")
    public void accessHotspotReport() {
        WebDriverWait wait = (new WebDriverWait(driver, 5));

        //Click on the Create Report menu
        homePage.accessHotspot();

        //Verify that the menu displays
        softAssert.assertEquals(driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/h6")).getText(),
                "PLACES", "The menu with different categories and reports should display.");
        softAssert.assertAll();

        //In the Create Report menu, click on Hotspot Area Analysis, then wait until the corresponding page loads
        homePage.selectHotspot();
        wait.until(ExpectedConditions.urlMatches("https://demo.placelab.com/places/hotspot_area_analyses/new"));

        //Verify that the URL is correct
        Assert.assertEquals(driver.getCurrentUrl(), hotspotURL, "Wrong URL found");
    }

    @Test(priority = 3, description = "Verify that you can create a report", suiteName = "Smoke Test")
    public void createReport() {

        //Wait for the page to load until the button Submit is available
        WebDriverWait wait = (new WebDriverWait(driver, 60));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotspot_poi_query\"]/button")));

        //Enter the name of the report
        createReport.enterReportName();

        //Select Accommodation in the Category drop down list
        createReport.selectCategory();

        //Verify Accommodation is selected in the list
        softAssert.assertEquals(createReport.getAccommodation(), "Accommodation",
                "Accommodation is not selected");
        softAssert.assertAll();

        //Enter the city, the radius, the latitude and the longitude
        createReport.enterLocation();
        createReport.selectRadius();
        createReport.setLatitude();
        createReport.setLongitude();

        //Click on Submit
        createReport.submitReport();

        //Wait until the new report is created
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test(priority = 4, description = "Verify that the report has been created", suiteName = "Smoke Test")
    public void checkReportPage() {
        WebDriverWait wait = (new WebDriverWait(driver, 30));

        //Reload the Reports page
        newReportPage.goToReportsPage();

        //Validate the URL
        Assert.assertEquals(driver.getCurrentUrl(), reportsURL, "Wrong URL");

        //Click on the report previously created (hvala Seniti na ovom hacku!)
        reportsPage.clickOnTheReport();

        //Wait until the report page loads
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div/span[2]"))));

        //Verify that the name of the report is correct
        Assert.assertEquals(newReportPage.getNameOfReport(), reportName, "The name of the report in " +
                " the page doesn't match the name of the newly created report.");

        //Check that Analysis Info section displays, and contains appropriate information
        Assert.assertEquals(newReportPage.getAnalysisInfo(), "Analysis Info", "Analysis Info" +
                " section should display.");
        Assert.assertEquals(newReportPage.getUsernameAnalysisInfo(), user, "The name of the user should" +
                " display");
        Assert.assertEquals(newReportPage.getCategory(), "Accommodation", "The category the user" +
                " selected should displays");

        //Check that the widgets display
        newReportPage.getMapWidget().isDisplayed();
        newReportPage.getTotalPOI().isDisplayed();
        newReportPage.getAttributePresence().isDisplayed();
        newReportPage.getDistanceAnalysis().isDisplayed();
        newReportPage.getBayesian().isDisplayed();
        newReportPage.getDistributionPerProvider().isDisplayed();
        newReportPage.getDistribution().isDisplayed();
        newReportPage.getFrequentCategories().isDisplayed();
        newReportPage.getReviewDistribution().isDisplayed();
        newReportPage.getPhotoDistribution().isDisplayed();
    }

    @AfterTest(alwaysRun = true)
    public void cleanAfterTest() {

        //Go back to the Reports page
        driver.navigate().to(reportsURL);

        //Select the report
        reportsPage.checkboxReport();

        //Click on the icon to delete the selected report, then confirm
        reportsPage.deleteReport();
        reportsPage.confirmDelete();

        //Verify that the report has been deleted
        reportsPage.getSuccessMessage().isDisplayed();

        //Sign out
        homePage.signOut();
        Assert.assertEquals(driver.getCurrentUrl(), host);
    }

    @AfterSuite(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }
}
