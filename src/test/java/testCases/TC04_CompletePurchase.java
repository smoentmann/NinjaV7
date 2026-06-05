package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CategoryPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.ProductPage;
import pageObjects.loginPage;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC04_CompletePurchase extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC04_CompletePurchase.class);

    @Test(groups = {"sanity", "regression"}, retryAnalyzer = utilities.RetryAnalyzer.class)
    public void testCompletePurchase() {

        logger.info("********** Starting TC04_CompletePurchase Test **********");

        try {

            logger.debug("Creating CategoryPage object");
            CategoryPage cp = new CategoryPage(getDriver());

            logger.debug("Navigating to Laptops & Notebooks category");
            cp.clickLaptopsAndNotebooks();

            logger.debug("Clicking Show All option");
            cp.clickShowAll();

            logger.debug("Waiting for products to load");
            Thread.sleep(500);

            logger.debug("Selecting HP product");
            cp.selectHPProduct();

            logger.debug("Creating ProductPage object");
            ProductPage pp = new ProductPage(getDriver());

            logger.debug("Setting delivery date");
            pp.setDeliveryDate();

            logger.debug("Adding product to cart");
            pp.clickAddToCart();

            logger.debug("Proceeding to checkout");
            pp.clickCheckout();

            logger.debug("Creating CheckoutPage object");
            CheckoutPage cop = new CheckoutPage(getDriver());

            logger.debug("Clicking Login option during checkout");
            cop.clickLogin();

            logger.debug("Creating LoginPage object");
            loginPage lp = new loginPage(getDriver());

            logger.debug("Entering login credentials");
            lp.setEmail("shawnamoentmann@gmail.com");

            logger.debug("Entering password");
            lp.setPassword("M@nkey12");

            logger.debug("Clicking Login button");
            lp.clickLogin();

            logger.debug("Completing checkout process");
            cop.completeCheckout();

            logger.debug("Creating ConfirmationPage object");
            ConfirmationPage confirmationPage = new ConfirmationPage(getDriver());

            try {

                logger.debug("Verifying order placement confirmation");

                boolean orderStatus = confirmationPage.isOrderPlaced();

                Assert.assertTrue(orderStatus, "Order placement failed!");

                logger.info("Order has been placed successfully.");

            } catch (AssertionError ae) {

                logger.error("Order placement validation failed.");
                logger.error("Expected confirmation message was not displayed.");
                logger.error(ae.getMessage());

                throw ae; // Required for RetryAnalyzer
            }

        } catch (Exception e) {

            logger.error("Exception occurred during Complete Purchase test execution", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());

        } finally {

            logger.info("********** Finished TC04_CompletePurchase Test **********");
        }
    }
}