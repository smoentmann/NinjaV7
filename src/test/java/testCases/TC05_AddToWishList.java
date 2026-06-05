package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CategoryPage;
import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.ProductPage;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC05_AddToWishList extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC05_AddToWishList.class);

    @Test(groups = {"regression"}, retryAnalyzer = utilities.RetryAnalyzer.class)
    void testAddToWishList() {

        logger.info("********** Starting TC05_AddToWishList Test **********");

        try {

            logger.debug("Creating HomePage object");
            homePage hp = new homePage(getDriver());

            logger.debug("Clicking My Account");
            hp.clickmyAccount();

            logger.debug("Navigating to Login page");
            hp.goToLogin();

            logger.debug("Creating LoginPage object");
            loginPage lp = new loginPage(getDriver());

            logger.debug("Entering email address");
            lp.setEmail("shawnamoentmann@gmail.com");

            logger.debug("Entering password");
            lp.setPassword("M@nkey12");

            logger.debug("Clicking Login button");
            lp.clickLogin();

            logger.info("User logged in successfully");

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

            logger.info("HP product selected successfully");

            logger.debug("Creating ProductPage object");
            ProductPage pp = new ProductPage(getDriver());

            logger.debug("Adding product to wishlist");
            pp.addToWishlist();

            logger.info("Product added to wishlist");

            try {

                logger.debug("Verifying wishlist success message");

                boolean status = pp.isSuccessMessageDisplayed();

                Assert.assertTrue(status, "Wishlist message not shown.");

                logger.info("Wishlist success message verified successfully");

            } catch (AssertionError ae) {

                logger.error("Wishlist validation failed.");
                logger.error("Expected wishlist success message was not displayed.");
                logger.error(ae.getMessage());

                throw ae; // Required for RetryAnalyzer to execute
            }

        } catch (Exception e) {

            logger.error("Exception occurred during Add To Wishlist test execution", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());

        } finally {

            logger.info("********** Finished TC05_AddToWishList Test **********");
        }
    }
}