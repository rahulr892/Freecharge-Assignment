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
import org.testng.Assert;

import java.util.List;

public class ProductListing_Page extends PageBase {

    @FindBy(css = "div.vertical-filters-filters")
    private List<WebElement> vertical_filters_list;
    @FindBy(css = "section.horizontal-filters-base")
    private WebElement horizontal_filters;
    @FindBy(css = "li.product-base")
    private List<WebElement> products_list;
    @FindBy(css = "h3.product-brand")
    private List<WebElement> product_names_list;
    @FindBy(css = "span.product-discountedPrice")
    private List<WebElement> product_price_list;
    @FindBy(css = "span.product-addToBag")
    private List<WebElement> addtobag_list;
    @FindBy(css = "span.product-wishlist")
    private List<WebElement> addtowishlist_list;
    @FindBy(css = "ul.pagination-container")
    private WebElement pagination_footer;


    public ProductListing_Page waitForProductListingPage() {
        ExtentUtil.fetchTest().log(Status.INFO, "Waiting for product listing page to load");
        (new WebDriverWait(TLDriver.getDriver(), 20))
                .until(visibilityOfNElementsLocatedBy(products_list, 30));
        (new WebDriverWait(TLDriver.getDriver(), 20)).until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(horizontal_filters),
                ExpectedConditions.visibilityOf(pagination_footer)));
        return this;
    }


    public ProductListing_Page validatePageTitle(String product) {
        ExtentUtil.fetchTest().log(Status.INFO, "Validating product listing page title");
        verifyContains(pageTitle(), product, "Product listing page title validation failed");
        return this;
    }


    public ProductListing_Page hoverOnProduct(int num) {
        ExtentUtil.fetchTest().log(Status.INFO, "Hovering on product based on index");
        mouseOver(product_names_list.get(num));
        return this;
    }

    public ProductListing_Page validateAddToBagAndWishlistButtonsAreVisible(int num) {
        ExtentUtil.fetchTest().log(Status.INFO, "Validating add to bag and wishlist buttons are visible");
        Assert.assertTrue(addtobag_list.get(num).isDisplayed());
        Assert.assertTrue(addtowishlist_list.get(num).isDisplayed());
        return this;
    }

    public String getProductName(int num) {
        return getText(product_names_list.get(num)).trim();
    }

    public String getProductPrice(int num) {
        return getText(product_price_list.get(num)).trim();
    }

    public ProductListing_Page clickOnProduct(int num) {
        ExtentUtil.fetchTest().log(Status.INFO, "Clicking on product based on index");
        click(product_price_list.get(num));
        return this;
    }


}
