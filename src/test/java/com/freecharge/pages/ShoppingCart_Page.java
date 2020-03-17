package com.freecharge.pages;

import com.aventstack.extentreports.Status;
import com.freecharge.base.PageBase;
import com.freecharge.browsersetup.TLDriver;
import com.freecharge.extentreader.ExtentUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCart_Page extends PageBase {

    @FindBy(css = "div.itemBlock-base-itemHeader > div")
    private WebElement myshoppingbag_label;
    @FindBy(css = "div.itemContainer-base-brand")
    private WebElement product_name_label;
    @FindBy(css = "div.itemComponents-base-price > div")
    private WebElement product_price_label;
    @FindBy(css = "div.itemComponents-base-size > span")
    private WebElement product_size_label;
    @FindBy(css = "button.removeButton")
    private WebElement remove_product_button;
    @FindBy(css = "button.wishlistButton")
    private WebElement wishlist_product_button;
    @FindBy(css = "div.priceDetail-base-total span.priceDetail-base-value")
    private WebElement total_price_label;
    @FindBy(css = "div.button-base-button")
    private WebElement place_order_button;


    public ShoppingCart_Page waitForCartPage() {
        ExtentUtil.fetchTest().log(Status.INFO, "Waiting for shopping cart page to load");
        (new WebDriverWait(TLDriver.getDriver(), 20)).until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(myshoppingbag_label),
                ExpectedConditions.visibilityOf(remove_product_button),
                ExpectedConditions.visibilityOf(wishlist_product_button)));
        return this;
    }


    public ShoppingCart_Page validatePageTitle() {
        ExtentUtil.fetchTest().log(Status.INFO, "Validating shopping cart page title");
        verifyEquals(pageTitle(), "SHOPPING BAG", "Page title validation failed");
        return this;
    }


    public ShoppingCart_Page validateProductDetailsInCart(String name, String price) {
        ExtentUtil.fetchTest().log(Status.INFO, "Validating product details in cart");
        verifyEquals(getText(product_name_label), name, "Product name does not match");
        verifyEquals(getText(product_price_label), price, "Product price does not match");
        return this;
    }


    public int validateTotalPriceGreaterOrEqualToCartTotal() {
        ExtentUtil.fetchTest().log(Status.INFO, "Validate total price is greater than or equal to the cart total");
        int productPrice = Integer.parseInt(getText(product_price_label));
        int totalPrice = Integer.parseInt(getText(total_price_label));
        verifyIsTrue(totalPrice >= productPrice, "Total price greater or equal to cart total failed");
        return totalPrice;
    }


    public ShoppingCart_Page clickPlaceOrderButton() {
        ExtentUtil.fetchTest().log(Status.INFO, "Validating product details in cart");
        click(place_order_button);
        return this;
    }


}
