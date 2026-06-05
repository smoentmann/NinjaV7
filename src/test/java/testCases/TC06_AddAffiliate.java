package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AffiliatePage;
import pageObjects.homePage;
import pageObjects.loginPage;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC06_AddAffiliate extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC06_AddAffiliate.class);

    @Test(groups = {"regression"}, retryAnalyzer = utilities.RetryAnalyzer.class)
    void testAddAffiliate() {

        logger.info("********** Starting TC06_AddAffiliate Test **********");

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

            logger.debug("Creating AffiliatePage object");
            AffiliatePage ap = new AffiliatePage(getDriver());

            logger.debug("Navigating to Affiliate registration form");
            ap.navigateToAffiliateForm();

            logger.debug("Filling affiliate details");
            ap.fillAffiliateDetails(
                    "CloudBerry",
                    "cloudberry.services",
                    "123456",
                    "Shadab Siddiqui");

            logger.info("Affiliate details submitted successfully");

            try {

                logger.debug("Verifying affiliate registration success message");

                boolean status = ap.isAffiliateAdded();

                Assert.assertTrue(
                        status,
                        "Affiliate details not added successfully.");

                logger.info("Affiliate registration verified successfully");

            } catch (AssertionError ae) {

                logger.error("Affiliate registration validation failed.");
                logger.error("Expected affiliate success message was not displayed.");
                logger.error(ae.getMessage());

                throw ae; // Required for RetryAnalyzer
            }

        } catch (Exception e) {

            logger.error("Exception occurred during Add Affiliate test execution", e);
            Assert.fail("Test failed due to exception: " + e.getMessage());

        } finally {

            logger.info("********** Finished TC06_AddAffiliate Test **********");
        }
    }
}