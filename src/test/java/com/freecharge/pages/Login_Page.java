package com.freecharge.pages;

import com.aventstack.extentreports.Status;
import com.freecharge.base.PageBase;
import com.freecharge.browsersetup.TLDriver;
import com.freecharge.extentreader.ExtentUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_Page extends PageBase {

    @FindBy(css = "button.login-facebook")
    private WebElement facebook_button;
    @FindBy(css = "button.login-google")
    private WebElement google_button;
    @FindBy(css = "input.login-user-input-email")
    private WebElement emailaddress_input;
    @FindBy(css = "input.login-user-input-password")
    private WebElement password_input;
    @FindBy(css = "button.login-login-button")
    private WebElement login_button;
    @FindBy(css = "a.login-create-account-link")
    private WebElement create_account_link;


    public Login_Page waitForLoginPageLoad() {
        ExtentUtil.fetchTest().log(Status.INFO, "Waiting for login page to load");
        (new WebDriverWait(TLDriver.getDriver(), 20)).until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(facebook_button),
                ExpectedConditions.visibilityOf(google_button),
                ExpectedConditions.visibilityOf(create_account_link)));
        return this;
    }


    public Login_Page validatePageTitle() {
        ExtentUtil.fetchTest().log(Status.INFO, "Validating login page title");
        verifyEquals(pageTitle(), "Login", "Login page title validation failed");
        return this;
    }


    public Login_Page typeEmailAndPassword(String email, String password) {
        ExtentUtil.fetchTest().log(Status.INFO, "Typing username: " + email + " and password: " + password);
        type(emailaddress_input, email);
        type(password_input, password);
        return this;
    }


    public Login_Page clickLoginButton() {
        ExtentUtil.fetchTest().log(Status.INFO, "Clicking login button");
        click(login_button);
        return this;
    }


}

