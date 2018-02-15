# training-testng-basic 
Basic TestNG examples with WebDriver and Chrome browser.

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
    
## Browser parameter

Supported values are:
* value="firefox"
* value="chrome"
* value="safari"
    
## Running the Demos
1. You will need to create yourself a valid GitHub account
2. For login tests you will need to use the data providers withe the User object.

## Demo 1
Example of static data provider, a different data provider class with two 
test data parameters.

1. Data provider class `StaticDataProvider.java` we have:
    1. @DataProvider(name = "invalid_user_data_and_browser")
        * returns pairs of User, Browser objects for invalid login on Chrome and Firefox
2. In test class `DataProviderLoginTests.java` we have added:
    1. `@Test(dataProvider = "invalid_user_data_and_browser", dataProviderClass = StaticDataProvider.class)` 
    2. Both parameters `loginWithInvalidUser(User testUser, Browser browser)`
3. Running the `runStaticProvider.xml` will run invalid login test on Chrome and one test on Firefox

        
## Demo 2
Example of parallel run using User and Browser objects from static data provider.

1. In test class `StaticDataProvider.java` we have:
    1. @DataProvider(name = "valid_user_data_and_browser", parallel = true)
        * returns pairs of User, Browser objects for valid login on Chrome and Firefox
2. In test class `DataProviderBrowserTests.java`:
    1. `@Test(dataProvider = "valid_user_data_and_browser", dataProviderClass = StaticDataProvider.class)`
3. Running the `runStaticProviderParallel.xml` will run 

## Practice, practice :exclamation: :sweat:
1. In `DataProviderObjectTests.java` add a new data provider method and perform a valid login test
    1. Create a new data provider method `loginValidProvider()` for creating valid login data
    2. Create a new test data method which is using the valid data provider from point 1.1

2. Create a data provider object example for a search functionality test
    1. Create a class which will hold the search test data (package main/java/training/basic/dataprovider)
    2. Create a test class which will have the search test (package test/java/dataprovider)
    3. In the test class create a test data provider method for creating the search test data object from point i
    4. Create the search test method by using the data provider from point iii