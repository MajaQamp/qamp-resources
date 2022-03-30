package placelab.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import placelab.utilities.WebDriverSetup;

public class LoginTest {
    public WebDriver driver;
    private String host = System.getProperty("host");
    public String username = System.getProperty("username");
    public String password = System.getProperty("password");
    public String errorMessage = "Invalid credentials!";
    public String forgotPasswordURL = "https://demo.placelab.com/password/forgot";

    @Parameters({"browser"})

//    @BeforeSuite(alwaysRun = true)
//    public void openBrowser(String browser) {
//
//        driver = WebDriverSetup.getWebDriver(browser);
//    }
    @BeforeTest(alwaysRun = true, groups = {"Positive", "Negative", "Login Page", "Forgot Password"},
            description = "Verify that user is able to open" + "PlaceLab App.")
    public void openApp(String browser) {
        driver = WebDriverSetup.getWebDriver(browser);
        driver.navigate().to(host);

        Assert.assertEquals(driver.getCurrentUrl(), host, "Wrong URL");
        Assert.assertEquals(driver.getTitle(), "PlaceLab", "Wrong page");

        WebElement logo = driver.findElement(By.xpath("//*[@id=\"login\"]/img"));
        boolean logoPresent = logo.isDisplayed();
        Assert.assertTrue(logoPresent, "There is no logo.");

    }

    @BeforeMethod(alwaysRun = true, onlyForGroups = {"Forgot Password"})
    public void backToLoginPage() {
        driver.navigate().to(host);
    }

    @Test(priority = 1, groups = {"Negative", "Login Page"}, description = "Verify that the user cannot log in if the e-mail" +
            "address field is left blank", suiteName = "Login page test")
    public void logInWithoutEmail() {
        driver.findElement(By.name("password")).sendKeys(password);
        loginFailedErrorMessage();
    }

    @Test(priority = 2, groups = {"Negative", "Login Page"}, description = "Verify that the user cannot log in without providing" +
            "a password", suiteName = "Login page test")
    public void passwordFieldBlank() {
        driver.findElement(By.id("email")).sendKeys(username);
        loginFailedErrorMessage();
    }

    @Test(priority = 3, groups = {"Negative", "Login Page"}, description = "Verify that the user cannot " +
            "log in with a wrong e-mail", suiteName = "Login page test")
    public void logInTryWithWrongEmail() {
        driver.findElement(By.id("email")).sendKeys("wrongUsername");
        driver.findElement(By.name("password")).sendKeys(password);
        loginFailedErrorMessage();
    }

    @Test(priority = 4, groups = {"Negative", "Login Page"}, description = "Verify that the user cannot " +
            "log in with a wrong password", suiteName = "Login page test")
    public void loginWithWrongPassword() {
        driver.findElement(By.id("email")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys("wrongPassword");
        loginFailedErrorMessage();
    }

    @Test(priority = 5, groups = {"Positive", "Forgot Password"}, description = "Verify that the user can " +
            "reset the password", suiteName = "Login page test")

    public void resetPassword() {
        driver.findElement(By.xpath("//*[@id=\"password-area\"]/a")).click();

        Assert.assertEquals(driver.getCurrentUrl(), forgotPasswordURL, "Wrong page. The page" +
                "\"Change your password should display\"");

        driver.findElement(By.id("email")).sendKeys(username);
        driver.findElement(By.name("commit")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"login\"]/p")).getText(),
                "We have sent you a link to change your password", "The user wasn't able to " +
                        "change the password");

    }

    @Test(priority = 6, groups = {"Negative", "Forgot Password"}, description = "Verify that the user cannot reset the password" +
            "without providing an e-mail address", suiteName = "Login page test")
    public void resetPasswordWithoutEmail() {

        driver.findElement(By.xpath("//*[@id=\"password-area\"]/a")).click();

        Assert.assertEquals(driver.getCurrentUrl(), forgotPasswordURL, "Wrong page. The page" +
                "\"Change your password\" should display");

        driver.findElement(By.name("commit")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"login\"]/div[1]/div/div")).getText(),
                "Email can't be empty", "The message \"Email can't be empty\" should display.");

    }

    @Test(priority = 7, groups = {"Negative", "Forgot Password"}, description = "Verify that the user cannot reset the password" +
            "without providing a valid e-mail address", suiteName = "Login page test")
    public void resetPasswordForNonRegisteredUser() {

        driver.findElement(By.xpath("//*[@id=\"password-area\"]/a")).click();
        Assert.assertEquals(driver.getCurrentUrl(), forgotPasswordURL, "Wrong page. The page" +
                "Change your password should display");

        driver.findElement(By.id("email")).sendKeys("email@email.com");
        driver.findElement(By.name("commit")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"login\"]/div[1]/div/div")).getText(),
                "User with this email doesn't exist", "No error message displayed");

    }

    @Test(priority = 8, groups = {"Positive, Login Page"}, description = "Verify that the link Terms of Use points to the" +
            "right page", suiteName = "Login page test")
    public void checkTermsOfUse() {
        WebElement openInSameTab = driver.findElement(By.linkText("Terms of Use"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('target','_self');", openInSameTab);
        openInSameTab.click();
        Assert.assertEquals(this.driver.getCurrentUrl(), "https://demo.placelab.com/terms_of_service",
                "The link Terms of Use doesn't redirect to the right page");


    }


    @AfterTest(alwaysRun = true)
    public void quitDriver() {
        driver.close();
    }

    /*Metodu ispod sam pokusala ubaciti u poseban fajl u Utilities folderu, ali nisam uspjela :/ Nisam je
     * stavila ni u AfterTest jer se odnosi na testove koji pripadaju istovremeno grupama Forgot Password
     * i Negative, a u AfterTestu bi se pokrenulo za testove koji su ili u jednoj ili  dugoj grupi */
    public void loginFailedErrorMessage() {

        driver.findElement(By.xpath("//*[@id=\"login_form\"]/input[4]")).click();
        Assert.assertEquals(this.driver.findElement(By.xpath("//*[@id=\"login\"]/div[1]/div/div")).getText(),
                errorMessage, "The error message doesn't display.");
    }
}