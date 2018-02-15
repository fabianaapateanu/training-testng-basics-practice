package common.helper;

import common.CustomLogger;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ActionHelper {
    private WebDriver driver;
    private static final Logger LOG = CustomLogger.getInstance(ActionHelper.class).getLogger();

    public ActionHelper(WebDriver driver) {
        this.driver = driver;
    }

    // Click on an element
    public void clickOn(WebElement elem) {
        LOG.info("Clicking on the element " + elem);
        elem.click();
    }

    //Send keys to an element
    public void sendKeys(WebElement elem, String text) {
        elem.sendKeys(text);
        LOG.info("The text " + text + " was sent to the element " + elem);
    }
}
