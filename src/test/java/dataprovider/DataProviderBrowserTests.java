package dataprovider;

import common.CustomDriver;
import common.CustomLogger;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import training.basic.dataprovider.Browser;
import training.basic.dataprovider.User;
import training.basic.pageObject.HomePage;
import training.basic.pageObject.LoginPage;

public class DataProviderBrowserTests {
    private static ThreadLocal<CustomDriver> myDriver = new ThreadLocal<CustomDriver>();
    private static Logger LOG;

    @BeforeClass
    public static void runBeforeClassInit() {
        LOG = CustomLogger.getInstance(DataProviderBrowserTests.class).getLogger();
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
        myDriver.set(CustomDriver.getInstance(browser.getBroswserName()));
    }

    @AfterMethod
    public void runAfterEachTestMethod() {
        LOG.info("Running teardown before each test method");
        myDriver.get().closeDriver();
    }

    @Test(dataProvider = "valid_user_data_and_browser", dataProviderClass = StaticDataProvider.class)
    public void loginWithValidUser(User testUser, Browser browser) {
        LoginPage loginPage = new LoginPage(myDriver.get().getDriver());
        HomePage homePage = loginPage.performLogin(testUser.getUsername(), testUser.getPassword());
        Assert.assertTrue(homePage.isUserMenuButtonGroupVisible(), "The user menu is not displayed");
    }
}