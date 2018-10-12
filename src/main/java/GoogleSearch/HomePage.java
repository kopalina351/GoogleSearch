package GoogleSearch;

import libs.ConfigData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class HomePage {
    private final WebDriverWait webDriverWait;
    WebDriver driver;
    Logger logger;
    final String errorElement = "Can't work with element ";
    final String baseUrl = "https://www.google.com.ua/";

    @FindBy(id = "lst-ib")
    WebElement inputSearch;

    public HomePage(WebDriver exterDriver) throws IOException {
        this.driver = exterDriver;
        logger = Logger.getLogger(getClass());
        webDriverWait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    /**
     * Method opens Browser And HomePage
     */
    public void openBrowserAndHomePage() {
        try {

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            driver.get(ConfigData.getCfgValue("BASE_URL"));
            if (driver.getCurrentUrl().toString().equals(baseUrl) == true) {
                logger.info("Home page was opened");
            } else logger.error("Home page wasn't opened");
        } catch (Exception e) {
            logger.error("Can not work with HomePage");
            Assert.fail("Can not work with HomePage");
        }
    }

    /**
     * Method inputs value in Search field and submits
     * @param valueSearch
     */
    public void inputValueInSearchField(String valueSearch){
        try {
            inputSearch.clear();
            inputSearch.sendKeys(valueSearch);
            logger.info("Search query '"+valueSearch+"' was inputted in Search field");
            inputSearch.submit();
        }catch (Exception e){
            logger.error(errorElement);
            Assert.fail(errorElement);
        }

    }


    /**
     * Method does Close Browser
     */
    public void closeBrowser() {
        driver.quit();
        logger.info("Browser was closed");
    }
}
