package com.freecharge.pages;

import com.aventstack.extentreports.Status;
import com.freecharge.base.PageBase;
import com.freecharge.browsersetup.TLDriver;
import com.freecharge.extentreader.ExtentUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Product_Page extends PageBase {

    @FindBy(css = "div.breadcrumbs-container")
    private WebElement breadcrumbs;
    @FindBy(css = "h1.pdp-title")
    private WebElement product_name_label;
    @FindBy(css = "span.pdp-price > strong")
    private WebElement product_price_label;
    @FindBy(css = "button.size-buttons-size-button")
    private List<WebElement> select_size_list;
    @FindBy(css = "div.pdp-add-to-bag")
    private WebElement addtobag_button;
    @FindBy(css = "a.pdp-goToCart")
    private WebElement gotocart_button;


    public Product_Page switchToProductPageTab(String product) {
        ExtentUtil.fetchTest().log(Status.INFO, "Switching to product page");
        String partOfUrl = product.toLowerCase().replace(" ", "-");
        switchTabsUsingPartOfUrl(partOfUrl);
        return this;
    }


    public Product_Page waitForProductPage() {
        ExtentUtil.fetchTest().log(Status.INFO, "Waiting for product page to load");
        (new WebDriverWait(TLDriver.getDriver(), 20)).until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(breadcrumbs),
                ExpectedConditions.visibilityOf(product_name_label)));
        return this;
    }


    public Product_Page validatePageTitle(String name) {
        ExtentUtil.fetchTest().log(Status.INFO, "Validating product page title");
        verifyContains(pageTitle(), "Buy " + name, "Product page title validation failed");
        return this;
    }


    public Product_Page validateSelectedProductDetails(String name, String price) {
        ExtentUtil.fetchTest().log(Status.INFO, "Validating selected product details");
        verifyEquals(getText(product_name_label), name, "Product name does not match");
        verifyEquals(getText(product_price_label).substring(4).replace(",", ""), price, "Product price does not match");
        return this;
    }


    public Product_Page selectProductSize() {
        ExtentUtil.fetchTest().log(Status.INFO, "Selecting product size");
        click(select_size_list.get(0));
        return this;
    }


    public Product_Page clickAddToBagButton() {
        ExtentUtil.fetchTest().log(Status.INFO, "Clicking add to bag button");
        click(addtobag_button);
        return this;
    }


    public Product_Page clickGoToCartButton() {
        ExtentUtil.fetchTest().log(Status.INFO, "Clicking go to bag button");
        click(gotocart_button);
        return this;
    }


}
