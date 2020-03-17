package com.freecharge.tests.myntra;

import com.freecharge.browsersetup.TLDriver;
import com.freecharge.pages.*;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class AddToCart {

    /*############################################################
     * ' Function Name: addToCartWorkflow
     * ' Purpose: Adding product to cart workflow test
     */
    @Test(priority = 1, description = "Myntra - Add to cart workflow")
    public void addToCartWorkflow(ITestContext context) {
        String searchText = "kurta";
        int productIndex = 0;
        String email = "akshah911@gmail.com";

        String url = context.getCurrentXmlTest().getParameter("URL");
        TLDriver.getDriver().get(url);

        Landing_Page landingPage = new Landing_Page();
        landingPage.waitForLandingPage()
                .validatePageTitle()
                .clickLoginButton();

        Login_Page loginPage = new Login_Page();
        loginPage.waitForLoginPageLoad()
                .validatePageTitle()
                .typeEmailAndPassword(email, "9867985991")
                .clickLoginButton();

        landingPage.waitForLandingPage()
                .validateLoggedInUserEmail(email)
                .searchForProduct(searchText)
                .clickOnSearchedProductRecommendation(searchText);

        ProductListing_Page productListingPage = new ProductListing_Page();
        productListingPage.waitForProductListingPage()
                .validatePageTitle(searchText)
                .hoverOnProduct(productIndex)
                .validateAddToBagAndWishlistButtonsAreVisible(productIndex);
        String name = productListingPage.getProductName(productIndex);
        String price = productListingPage.getProductPrice(productIndex).substring(4).replace(",", "");
        productListingPage.clickOnProduct(productIndex);

        Product_Page productPage = new Product_Page();
        productPage.switchToProductPageTab(name)
                .waitForProductPage()
                .validatePageTitle(name)
                .validateSelectedProductDetails(name, price)
                .selectProductSize()
                .clickAddToBagButton()
                .clickGoToCartButton();

        ShoppingCart_Page shoppingCartPage = new ShoppingCart_Page();
        shoppingCartPage.waitForCartPage()
                .validatePageTitle()
                .validateProductDetailsInCart(name, price);
        int totalPrice = shoppingCartPage.validateTotalPriceGreaterOrEqualToCartTotal();
        shoppingCartPage.clickPlaceOrderButton();

        Checkout_Page checkoutPage = new Checkout_Page();
        checkoutPage.waitForCartPage()
                .validatePageTitle()
                .validateTotalPriceFromCartPage(totalPrice)
                .clickContinueButton();

        Payment_Page paymentPage = new Payment_Page();
        paymentPage.waitForCartPage()
                .validatePageTitle()
                .validateTotalPriceFromCheckoutPage(totalPrice);
    }

}
