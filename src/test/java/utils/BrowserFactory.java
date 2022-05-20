package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.time.Duration;

public class BrowserFactory {


    //WebDriver driver =null;
    public static WebDriver startApplication(WebDriver driver, String browserName, String appURL) {
        if (browserName.equalsIgnoreCase("Chrome")) {

            System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
            driver = new ChromeDriver();

        } else if (browserName.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
            driver = new FirefoxDriver();

        } else if (browserName.equals("IE")) {
            System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");
            driver = new InternetExplorerDriver();

        } else {
            System.out.println("We do not support this browser");

        }

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(appURL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        return driver;
    }


    public static void quitBrower(WebDriver driver) {
        driver.quit();

    }
}
