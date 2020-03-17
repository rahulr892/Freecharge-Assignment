package com.freecharge.tests.login;

import com.freecharge.base.TestBase;
import com.freecharge.browsersetup.TLDriver;
import com.freecharge.constants.Constants;
import com.freecharge.excelreader.TestUtils;
import com.freecharge.excelreader.Xls_Reader;
import com.freecharge.pages.Login_Page;
import com.freecharge.pages.SignIn_Page;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class Login extends TestBase {

    private final static String testData = Constants.TESTDATA_LOC;
    private static Xls_Reader xls = new Xls_Reader(testData);

    /**
     * @return test data from TestData.xlsx
     */
    @DataProvider
    private Object[][] getTestData() {
        System.out.println("Executing DataProvider");
        return TestUtils.getData("Login", xls);
    }

    /*############################################################
     * ' Function Name: login_Test
     * ' Purpose: Simple login test
     */
    @Test(dataProvider = "getTestData", priority = 1, description = "test description text")
    public void login_Test(Hashtable<String, String> data, ITestContext context) {
        String url = context.getCurrentXmlTest().getParameter("URL");

        TLDriver.getDriver().get(url);

        Login_Page LoginPage = new Login_Page();
        SignIn_Page SignInPage = new SignIn_Page();

        LoginPage.waitForLoginPageLoad()
                .validatePageTitle();

        SignInPage.wait_For_SignIn_PageLoad()
                .type_Email(TestUtils.getTestData(data, "emailAdd"))
                .click_Next();
    }

}

