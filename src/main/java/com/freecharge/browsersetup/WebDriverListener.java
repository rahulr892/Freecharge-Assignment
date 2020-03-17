package com.freecharge.browsersetup;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.freecharge.excelreader.TestUtils;
import com.freecharge.extentreader.ExtentUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static com.freecharge.constants.Constants.REPORTS_LOC;

public class WebDriverListener implements IExecutionListener, IInvokedMethodListener {

    /**
     * Creates a single report for test run
     */
    @Override
    public void onExecutionStart() {
        System.out.println("Suite execution start\nStart extent reporting");
        String dir = "./ExtentReports/report";
        String time = new SimpleDateFormat("@dd_MM_yyyy@HH_mm_ss").format(new Date());
        String html = ".html";
        String path = dir + time + html;
        ExtentUtil.createReporter(path);
    }


    @Override
    public void onExecutionFinish() {
        System.out.println("Suite execution finish\nEnd extent reporting");
    }

    /**
     * Runs before each test method
     * Creates test and assigns category in extent report & Launches browser
     *
     * @param method     current test method
     * @param testResult current test method result
     */
    public synchronized void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {

            String className = method.getTestMethod().getRealClass().getSimpleName();
            String methodName = method.getTestMethod().getMethodName();
            String description = method.getTestMethod().getDescription();
            String OS_Browser = method.getTestMethod().getXmlTest().getLocalParameters().get("OS_Browser");
            String url = method.getTestMethod().getXmlTest().getLocalParameters().get("URL");

            // Checks in config.xlsx to run the test class or not
            if (!TestUtils.isTestExecutable(className)) {
                String message = "Skipping the TEST CLASS : '" + className + "' as Runmode is N";
                System.out.println(message);
                throw new SkipException(message);
            }
            // Checks in config.xlsx to run the test method or not
            if (!TestUtils.isTestExecutable(methodName)) {
                String message = "Skipping the TEST METHOD : '" + methodName + "' as Runmode is N";
                System.out.println(message);
                throw new SkipException(message);
            }
            // If runmode is set to Y for both class and method in config.xlsx then proceed with execution

            // Create test in extent report and assign category
            ExtentUtil.createTest(methodName + " - " + OS_Browser, description);
            ExtentUtil.fetchTest().assignCategory(className);

            // Assign category fetched from test groups to test method in extent report
            String[] categories = method.getTestMethod().getGroups();
            for (String category : categories)
                ExtentUtil.fetchTest().assignCategory(category);

            Reporter.log("\nBROWSER: " + OS_Browser, true);
            Reporter.log("TESTCASE: " + className + "." + methodName + "\n", true);

            try {
                WebDriver driver = DriverFactory.createInstance(OS_Browser);
                TLDriver.setWebDriver(driver);
            } catch (Exception e) {
                System.out.println("beforeInvocation - Driver instance not created");
                e.printStackTrace();
            }
        }
    }


    /**
     * Runs after each test method
     * Logs test result and ends browser session
     *
     * @param method     current test method
     * @param testResult current test method result
     */
    public synchronized void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            if (TLDriver.getDriver() != null) {

                if (testResult.getStatus() == ITestResult.SUCCESS) {
                    ExtentUtil.fetchTest().log(Status.PASS, MarkupHelper.createLabel("TEST PASSED", ExtentColor.GREEN));

                } else if (testResult.getStatus() == ITestResult.FAILURE) {
                    try {
                        String screenShotPath = capture(TLDriver.getDriver(), randomString());
                        ExtentUtil.fetchTest().addScreenCaptureFromPath(screenShotPath);
                    } catch (IOException e) {
                        System.out.println("AfterInvocation screenshot failure" + e.getCause());
                    }
                    ExtentUtil.fetchTest().log(Status.INFO, testResult.getThrowable());
                    ExtentUtil.fetchTest().log(Status.FAIL, MarkupHelper.createLabel("TEST FAILED", ExtentColor.RED));

                } else if (testResult.getStatus() == ITestResult.SKIP) {
                    ExtentUtil.fetchTest().log(Status.INFO, testResult.getThrowable());
                    ExtentUtil.fetchTest().log(Status.SKIP, MarkupHelper.createLabel("TEST SKIPPED", ExtentColor.ORANGE));
                }

                TLDriver.getDriver().quit();
                ExtentUtil.saveReporter(); // Saves test log in report under ExtentReports folder
            } else {
                System.out.println("afterInvocation - Driver instance creation failed");
            }
        }
    }


    /**
     * @param driver         current driver instance
     * @param screenShotName random 10 letter string
     * @return failure screenshot folder path and image name
     * @throws IOException while copying file to folder
     */
    private String capture(WebDriver driver, String screenShotName) throws IOException {
        screenShotName = screenShotName + ".png";
        String todayAsString = new SimpleDateFormat("dd-MM-yyyy/").format(new Date());
        String directory = REPORTS_LOC + todayAsString;
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(source, new File(directory + screenShotName));
        } catch (IOException e) {
            e.getMessage();
        }
        return directory + screenShotName;
    }


    /**
     * @return random 15 letter string
     */
    private String randomString() {
        final String data = "abcdefghijklmnopqrtuvwxyz1234567890";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(15);
        for (int i = 0; i < 10; i++) {
            sb.append(data.charAt(random.nextInt(data.length())));
        }
        return sb.toString();
    }


}

