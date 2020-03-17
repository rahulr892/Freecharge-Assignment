package com.freecharge.pages;

import com.aventstack.extentreports.Status;
import com.freecharge.base.PageBase;
import com.freecharge.browsersetup.TLDriver;
import com.freecharge.extentreader.ExtentUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Payment_Page extends PageBase {

    @FindBy(css = "div.paymentDesktop-base-pModeHeading")
    private WebElement choose_payment_mode_label;
    @FindBy(css = "div.paymentDesktop-base-tabBarBlock")
    private WebElement payment_options;
    @FindBy(css = "div.addressDetails-base-addressTitle")
    private WebElement user_name_label;
    @FindBy(css = "div.addressDetails-base-address")
    private WebElement user_address_details;
    @FindBy(css = "div.priceDetail-base-total > span.priceDetail-base-value")
    private WebElement total_price_label;


    public Payment_Page waitForCartPage() {
        ExtentUtil.fetchTest().log(Status.INFO, "Waiting for payment page to load");
        (new WebDriverWait(TLDriver.getDriver(), 30)).until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(choose_payment_mode_label),
                ExpectedConditions.visibilityOf(payment_options),
                ExpectedConditions.visibilityOf(user_address_details)));
        return this;
    }


    public Payment_Page validatePageTitle() {
        ExtentUtil.fetchTest().log(Status.INFO, "Validating payment page title");
        verifyEquals(pageTitle(), "PAYMENT", "Page title validation failed");
        return this;
    }


    public Payment_Page validateTotalPriceFromCheckoutPage(int price) {
        ExtentUtil.fetchTest().log(Status.INFO, "Validating total price is equal to the checkout page total");
        int totalPrice = Integer.parseInt(getText(total_price_label));
        Assert.assertEquals(totalPrice, price, "Total price from checkout page and payment page does not match");
        return this;
    }


}
