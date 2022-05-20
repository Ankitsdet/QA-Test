package ui;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.BrowserFactory;
import utils.ConfigDataProvider;
import utils.ExcelDataProvider;
import utils.Helper;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class BaseSpec {

    public static WebDriver driver;
    public ExcelDataProvider excel;
    public ConfigDataProvider config;
    public ExtentReports report;
    public ExtentTest logger;

    @BeforeSuite
    public void setUpSuite() {
        excel = new ExcelDataProvider();
        config = new ConfigDataProvider();
        ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir") + "/reports/Lampenwelt" + Helper.getCurrentDateTime() + ".html"));
        report = new ExtentReports();
        report.attachReporter(extent);
    }

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getStagingURL());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @AfterMethod
    public void tearDownMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            Helper.captureScreenshot(driver);
            logger.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
        }

        report.flush();
        BrowserFactory.quitBrower(driver);
    }



    public static void click(WebElement element) {
        WebElement webEle = new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(element));
        webEle.click();
    }

    public static void type(WebElement webElement, String key) throws InterruptedException {
        WebElement webEle = new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(webElement));
        webEle.sendKeys(key);
    }

    public static void waitForElement(WebElement webElement) {
        WebElement webEle = new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(webElement));
        webEle.isDisplayed();
    }

    public static void waitForPageLoad() {
        Boolean readyStateComplete = false;
        while (!readyStateComplete) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            readyStateComplete = ((String) executor.executeScript("return document.readyState")).equals("complete");
        }
    }

    public static void selectValue(WebElement element, String type, String value) {
        Select select = new Select(element);
        switch (type) {
            case "index":
                select.selectByIndex(Integer.parseInt(value));
                break;
            case "value":
                select.selectByValue(value);
                break;
            case "visibletext":
                select.selectByVisibleText(value);
                break;
            default:
                Assert.fail("Please pass the correct value");
                break;
        }

    }

}
