# training-testng-basic-practice
More TestNG examples with WebDriver and Chrome & Firefox browsers.

## Project resources
* src/main/resources/drivers/mac - chromedriver v. 2.35
* src/main/resources/drivers/mac - geckodriver v. 0.19.1
* src/main/resources/drivers/windows - chromedriver.exe v.2.35
* src/main/resources/drivers/windows - geckodriver.exe 0.19.1

## Project structure
* src/main/java/common - webdriver custom driver and helpers
* src/main/java/training/basic - page object classes and dataprovider object class for the demos
    * /dataprovider
        * User object - the username and password
        * Browser object - the name of the browser
    * /pageObject
  
* src/test/java
    * /basic - test classes for basic examples
    * /dataprovider - test classes for dataprovider examples
    * /todo - test class for you to practice
    
## Browser parameter
Supported values are:
* value="firefox"
* value="chrome"
* value="safari"
    
## Running the Demos
1. You will need to create yourself a valid GitHub account
2. For login tests you will need to use the data providers with the User object, which is
your valid GitHub username and password

## Demo 1
Example of static data provider, a different data provider class with two 
test data parameters. Test class used is `DataProviderLoginTests.java`.

1. Data provider class `StaticDataProvider.java` we have:
    1. `@DataProvider(name = "invalid_user_data_and_browser")`
        * returns pairs of User, Browser objects for invalid login on Chrome and Firefox
2. In test class `DataProviderLoginTests.java` we have added:
    1. `@Test(dataProvider = "invalid_user_data_and_browser", dataProviderClass = StaticDataProvider.class)` 
    2. Both parameters `loginWithInvalidUser(User testUser, Browser browser)` were added to the test method
3. Running the `runStaticProvider.xml` will run invalid login test on Chrome and one test on Firefox

        
## Demo 2
Example of parallel test method run using User and Browser objects from static data provider.
Test class used is `DataProviderBrowserTests.java`.

1. In test class `StaticDataProvider.java` we have:
    1. `@DataProvider(name = "valid_user_data_and_browser", parallel = true)`
       * returns pairs of User, Browser objects for valid login on Chrome and Firefox
2. In test class `DataProviderBrowserTests.java`:
    1. `@Test(dataProvider = "valid_user_data_and_browser", dataProviderClass = StaticDataProvider.class)`
    2. Make sure to add here your valid user with correct username and password
3. Running the `runStaticProviderParallel.xml` will run both valid login tests one on Chrome and one on Firefox
    1. The xml file contains the paramteres for parallelization:
        * `thread-count="10" data-provider-thread-count="20"`

## Practice, practice :exclamation: :sweat:
1. You need to have the previously created object for Search functionality (a class with one String attribute, similar to Browser)
2. In `DataProvideSearchTests.java` modify the data provider method with your Search object:
    * `//TODO replace the second Browser object with your Search object and use it in a multiple result search test
       @DataProvider(name = "valid_login_search_browser_data")`
3. In `DataProviderSearchTests.java` modify the test method parameters accordingly:
    * `//TODO replace the Browser parameter object with your Search object
       @Test(dataProvider = "valid_login_search_browser_data", dataProviderClass = StaticDataProvider.class)
       public void searchWithMultipleResults(User user, Browser browser, Browser searchQuery)` 
4. Create a new testng.xml file which will run your class `DataProviderSearchTests.java` and its test methods sequentially.