package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CategoryPage;
import pageObjects.ProductPage;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC03_AddToCart extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC03_AddToCart.class);

    @Test(groups = {"sanity", "regression"}, retryAnalyzer = utilities.RetryAnalyzer.class)
    public void testAddToCart() {

        logger.info("********** Starting TC03_AddToCart Test **********");

        try {

            logger.debug("Creating CategoryPage object");
            CategoryPage cp = new CategoryPage(getDriver());

            logger.debug("Clicking on Laptops & Notebooks menu");
            cp.clickLaptopsAndNotebooks();

            logger.debug("Clicking on Show All Laptops & Notebooks");
            cp.clickShowAll();

            logger.debug("Waiting for products to load");
            Thread.sleep(500);

            logger.debug("Selecting HP Product");
            cp.selectHPProduct();

            logger.debug("Creating ProductPage object");
            ProductPage pp = new ProductPage(getDriver());

            logger.debug("Setting delivery date");
            pp.setDeliveryDate();

            logger.debug("Clicking Add To Cart button");
            pp.clickAddToCart();

            try {

                logger.debug("Verifying success message after adding product to cart");

                boolean status = pp.isSuccessMessageDisplayed();

                Assert.assertTrue(status, "Add to Cart Failed!");

                logger.info("Product successfully added to cart.");

            } catch (AssertionError ae) {

                logger.error("Add to Cart validation failed.");
                logger.error("Expected success message was not displayed.");
                logger.error(ae.getMessage());

                throw ae; // Important for RetryAnalyzer to trigger
            }

        } catch (Exception e) {

            logger.error("Exception occurred during Add To Cart test execution", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());

        } finally {

            logger.info("********** Finished TC03_AddToCart Test **********");
        }
    }
}