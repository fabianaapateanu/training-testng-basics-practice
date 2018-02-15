package dataprovider;

import common.CustomDriver;
import common.CustomLogger;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import training.basic.dataprovider.Browser;
import training.basic.dataprovider.User;
import training.basic.pageObject.LoginPage;

/**
 * DataProvider can be used with your own defined objects.
 * {main/java/training/basic/dataprovider/User.java} is used in this example for login test data.
 * {main/java/training/basic/dataprovider/Browser.java} is used for browser name parameter
 *
 * @author fapateanu
 */
public class DataProviderLoginTests {
    private CustomDriver myDriver;
    private static Logger LOG;

    @BeforeClass
    public static void runBeforeClassInit() {
        LOG = CustomLogger.getInstance(DataProviderLoginTests.class).getLogger();
        LOG.info("Running setup before class test methods initialization");
    }

    @AfterClass
    public static void runAfterClassFinished() {
        LOG.info("Running teardown after class test methods run finished");
    }

    /**
     * The before method receives an array of objects, the one sent through data provider.
     * In this case it is a pair of (User, Browser) objects.
     * At index 0 in the array we have the User object and at index 1 the Browser object.
     *
     * @param dataProviderParameters
     */
    @BeforeMethod
    public void runBeforeEachTestMethod(Object[] dataProviderParameters) {
        LOG.info("Running setup before each test method");
        //the data provider sends as second object the Browser object
        Browser browser = (Browser) dataProviderParameters[1];
        LOG.info("Browser name provided was: " + browser.getBroswserName());
        myDriver = CustomDriver.getInstance(browser.getBroswserName());
    }

    @AfterMethod
    public void runAfterEachTestMethod() {
        LOG.info("Running teardown before each test method");
        myDriver.closeDriver();
    }

    @Test(dataProvider = "invalid_user_data_and_browser", dataProviderClass = StaticDataProvider.class)
    public void loginWithInvalidUser(User testUser, Browser browser) {
        LoginPage loginPage = new LoginPage(myDriver.getDriver());
        loginPage.fillUsername(testUser.getUsername());
        loginPage.fillPassword(testUser.getPassword());
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Some failure log");
    }
}
