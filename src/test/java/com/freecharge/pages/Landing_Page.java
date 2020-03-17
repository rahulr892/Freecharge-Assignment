package com.freecharge.pages;

import com.aventstack.extentreports.Status;
import com.freecharge.base.PageBase;
import com.freecharge.browsersetup.TLDriver;
import com.freecharge.extentreader.ExtentUtil;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Landing_Page extends PageBase {

    @FindBy(css = "input[class='desktop-searchBar']")
    private WebElement searchbar_header_input;
    @FindBy(css = "span.sprites-headerUser")
    private WebElement profile_header_logo;
    @FindBy(css = "a[data-track='login']")
    private WebElement login_header_button;
    @FindBy(css = "div.desktop-infoEmail")
    private WebElement email_header_text;
    @FindBy(css = "div.desktop-autoSuggest.desktop-showContent")
    private WebElement search_recommendation_div;
    @FindBy(css = "li.desktop-suggestion")
    private List<WebElement> search_recommnedations_list;
    @FindBy(css = "div.desktop-gyanContainer")
    private WebElement footer_text;


    public Landing_Page waitForLandingPage() {
        ExtentUtil.fetchTest().log(Status.INFO, "Waiting for landing page to load");
        (new WebDriverWait(TLDriver.getDriver(), 20)).until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(searchbar_header_input),
                ExpectedConditions.visibilityOf(profile_header_logo),
                ExpectedConditions.visibilityOf(footer_text)));
        return this;
    }


    public Landing_Page validatePageTitle() {
        ExtentUtil.fetchTest().log(Status.INFO, "Validating landing page title");
        verifyEquals(pageTitle(),
                "Online Shopping for Women, Men, Kids Fashion & Lifestyle - Myntra",
                "Landing Page title validation failed");
        return this;
    }


    public Landing_Page clickLoginButton() {
        ExtentUtil.fetchTest().log(Status.INFO, "Clicking login button");
        mouseOver(profile_header_logo);
        click(login_header_button);
        return this;
    }


    public Landing_Page validateLoggedInUserEmail(String email) {
        ExtentUtil.fetchTest().log(Status.INFO, "Validating logged in user email");
        mouseOver(profile_header_logo);
        verifyEquals(getText(email_header_text), email, "Email validation failed");
        return this;
    }


    public Landing_Page searchForProduct(String product) {
        ExtentUtil.fetchTest().log(Status.INFO, "Searching product in search bar");
        type(searchbar_header_input, product);
        (new WebDriverWait(TLDriver.getDriver(), 20)).until(ExpectedConditions.visibilityOf(search_recommendation_div));
        return this;
    }


    public Landing_Page clickOnSearchedProductRecommendation(String product) {
        ExtentUtil.fetchTest().log(Status.INFO, "Clicking on product recommendation list from search");
        try {
            clickContainsText(search_recommnedations_list, product);
        } catch (Exception e) {
            ExtentUtil.fetchTest().log(Status.WARNING, "Clicking on product recommendation from search failed");
            ExtentUtil.fetchTest().log(Status.INFO, "Clicking enter in search");
            searchbar_header_input.sendKeys(Keys.RETURN);
        }
        return this;
    }


}
