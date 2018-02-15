package dataprovider;

import org.testng.annotations.DataProvider;
import training.basic.dataprovider.Browser;
import training.basic.dataprovider.User;

/**
 * Data provider class example with data provider method which is returining
 * a two dimensional array with the pair of objects: (User, Browser)
 */
public class StaticDataProvider {

    @DataProvider(name = "invalid_user_data_and_browser")
    public static Object[][] loginInvalidProvider() {
        return new Object[][]{{new User("yeseniaworld@gmail.com", "test12345"), new Browser("chrome")},
                {new User("invalidUser@yahoo", "invalidPass"), new Browser("firefox")}};
    }

    @DataProvider(name = "valid_user_data_and_browser", parallel = true)
    public static Object[][] loginValidProvider() {
        return new Object[][]{{new User("yeseniaworld@gmail.com", "admin2468"), new Browser("chrome")},
                {new User("yeseniaworld@gmail.com", "admin2468"), new Browser("firefox")}};
    }

    //TODO replace the second Browser object with your Search object and use it in a multiple result search test
    @DataProvider(name = "valid_login_search_browser_data")
    public static Object[][] validSearchProvider() {
        return new Object[][]{{new User("yeseniaworld@gmail.com", "admin2468"), new Browser("chrome"), new Browser("appium")},
                {new User("yeseniaworld@gmail.com", "admin2468"), new Browser("firefox"), new Browser("webdriver")}};
    }
}
