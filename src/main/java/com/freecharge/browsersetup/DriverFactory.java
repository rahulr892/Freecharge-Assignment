package com.freecharge.browsersetup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

class DriverFactory {

    private static OptionsManager options = new OptionsManager();

    /**
     * Start browser based on input in testng.xml and used in WebDriverListener
     *
     * @param OS_Browser fetched from xml
     * @return driver instance
     */
    static WebDriver createInstance(String OS_Browser) {
        RemoteWebDriver driver = null;
        String hub = "http://localhost:4444/wd/hub";

        WebDriverManager.chromedriver().setup();
        WebDriverManager.iedriver().arch32().version("3.11").setup();
        WebDriverManager.firefoxdriver().version("0.20.1").setup();

        if (("Win_Chrome").equalsIgnoreCase(OS_Browser)) {
            try {
                driver = new ChromeDriver(options.getChromeOptions());
            } catch (Exception e) {
                System.out.println("Win_Chrome - " + e);
            }

        } else if (("Win_Firefox").equalsIgnoreCase(OS_Browser)) {
            try {
                driver = new FirefoxDriver(options.getFirefoxOptions());
            } catch (Exception e) {
                System.out.println("Win_Firefox - " + e);
            }

        } else if (("Win_IE").equalsIgnoreCase(OS_Browser)) {
            try {
                driver = new InternetExplorerDriver(options.getIEOptions());
            } catch (Exception e) {
                System.out.println("Win_IE - " + e);
            }

        } else if (("Mac_Chrome").equalsIgnoreCase(OS_Browser)) {
            try {
                driver = new RemoteWebDriver(new URL(hub), options.getChromeOptions());
                driver.setFileDetector(new LocalFileDetector());
            } catch (Exception e) {
                System.out.println("Mac_Chrome - " + e);
            }

        } else if (("Mac_Safari").equalsIgnoreCase(OS_Browser)) {
            try {
                driver = new RemoteWebDriver(new URL(hub), options.getSafariOptions());
                driver.setFileDetector(new LocalFileDetector());
            } catch (Exception e) {
                System.out.println("Mac_Safari - " + e);
            }

        } else {
            try {
                driver = new ChromeDriver(options.getChromeOptions());
            } catch (Exception e) {
                System.out.println("Win_Chrome - " + e);
            }
        }

        if (driver != null) {
            try {
                driver.manage().window().maximize();
                driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            } catch (Exception e) {
                System.out.println("Please check browser value");
                e.printStackTrace();
            }
        }
        return driver;
    }

}

