package com.freecharge.tests.login;

import com.freecharge.browsersetup.TLDriver;
import com.freecharge.pages.Login_Page;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class ValidateTitle {

    /*############################################################
     * ' Function Name: validate_PageTitle
     * ' Purpose: Simple login test
     */
    @Test(priority = 1, description = "test description text")
    public void validate_PageTitle(ITestContext context) {
        String url = context.getCurrentXmlTest().getParameter("URL");

        TLDriver.getDriver().get(url);

        Login_Page LoginPage = new Login_Page();

        LoginPage.waitForLoginPageLoad()
                .validatePageTitle();
    }

}
