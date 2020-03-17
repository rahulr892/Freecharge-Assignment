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

public class Checkout_Page extends PageBase {

    @FindBy(css = "div.addressList-base-addAddressButton")
    private WebElement add_new_address_button;
    @FindBy(css = "div.addressBlocks-base-block")
    private WebElement default_address;
    @FindBy(css = "div.addressDetails-base-name")
    private WebElement user_name_label;
    @FindBy(css = "div.addressBlocks-base-addressDetail")
    private WebElement user_address_label;
    @FindBy(css = "div.addressDetails-base-mobile")
    private WebElement user_mobilenumber_label;
    @FindBy(css = "button.addressBlocks-base-remove")
    private WebElement remove_button;
    @FindBy(css = "button.addressBlocks-base-edit")
    private WebElement edit_button;
    @FindBy(css = "div.priceDetail-base-total > span.priceDetail-base-value")
    private WebElement total_price_label;
    @FindBy(id = "placeOrderButton")
    private WebElement continue_button;


    public Checkout_Page waitForCartPage() {
        ExtentUtil.fetchTest().log(Status.INFO, "Waiting for checkout page to load");
        (new WebDriverWait(TLDriver.getDriver(), 20)).until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(add_new_address_button),
                ExpectedConditions.visibilityOf(default_address),
                ExpectedConditions.visibilityOf(continue_button)));
        return this;
    }


    public Checkout_Page validatePageTitle() {
        ExtentUtil.fetchTest().log(Status.INFO, "Validating checkout page title");
        verifyEquals(pageTitle(), "ADDRESS", "Checkout page title validation failed");
        return this;
    }


    public Checkout_Page validateTotalPriceFromCartPage(int price) {
        ExtentUtil.fetchTest().log(Status.INFO, "Validating total price is equal to the cart page total");
        int totalPrice = Integer.parseInt(getText(total_price_label));
        Assert.assertEquals(totalPrice, price, "Total price from cart page and checkout page does not match");
        return this;
    }


    public Checkout_Page clickContinueButton() {
        ExtentUtil.fetchTest().log(Status.INFO, "Clicking continue button");
        click(continue_button);
        return this;
    }


}
