package todo;

import common.CustomDriver;
import common.CustomLogger;
import dataprovider.StaticDataProvider;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import training.basic.dataprovider.Browser;
import training.basic.dataprovider.User;
import training.basic.pageObject.HomePage;
import training.basic.pageObject.LoginPage;

/**
 * DataProvider can be used with primitive types: String, Boolean, Integer, etc.
 *
 * @author fapateanu
 */
public class DataProviderSearchTests {
    private static String username;
    private static String password;
    private HomePage homePage;

    private CustomDriver myDriver;
    private static Logger LOG;

    @BeforeClass
    public static void runBeforeClassInit() {
        LOG = CustomLogger.getInstance(DataProviderSearchTests.class).getLogger();
        LOG.info("Running setup before class test methods initialization");
    }

    @AfterClass
    public static void runAfterClassFinished() {
        LOG.info("Running teardown after class test methods run finished");
    }

    @BeforeMethod
    public void runBeforeEachTestMethod(Object[] dataProviderParameters) {
        LOG.info("Running setup before each test method");

        //the data provider sends as first object the User object
        User user = (User) dataProviderParameters[0];
        username = user.getUsername();
        password = user.getPassword();

        //the data provider sends as second object the Browser object
        Browser browser = (Browser) dataProviderParameters[1];
        LOG.info("Browser name provided was: " + browser.getBroswserName());
        myDriver = CustomDriver.getInstance(browser.getBroswserName());

        LoginPage loginPage = new LoginPage(myDriver.getDriver());
        homePage = loginPage.performLogin(username, password);
    }

    @AfterMethod
    public void runAfterEachTestMethod() {
        LOG.info("Running teardown before each test method");
        myDriver.closeDriver();
    }

    //TODO replace the Browser parameter object with your Search object
    @Test(dataProvider = "valid_login_search_browser_data", dataProviderClass = StaticDataProvider.class)
    public void searchWithMultipleResults(User user, Browser browser, Browser searchQuery) {
        Assert.assertTrue(homePage.isSearchAreaDisplayed(), "The search area is not displayed");
        //TODO replace this string value with your Search object string value
        String searchText = searchQuery.getBroswserName();

        homePage.performSearch(searchText);
        Assert.assertTrue(homePage.isRepositorySearchResultListDisplayed(), "The search action did not return multiple results");
    }
}
