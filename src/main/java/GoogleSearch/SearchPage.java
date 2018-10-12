package GoogleSearch;

import libs.ConfigData;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class SearchPage {
    private final WebDriverWait webDriverWait;
    WebDriver driver;
    Logger logger;
    final String errorCopyScreenshot = "Can't copy screenshot of element to disk ";


    @FindBy(xpath = "//td/a[@aria-label='Page 10']")
    WebElement tenthPage;


    public SearchPage(WebDriver exterDriver) throws IOException {
        this.driver = exterDriver;
        logger = Logger.getLogger(getClass());
        webDriverWait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);

    }

    public void clickByNavigationPage(int p) {
        if (p <= 10) {
            driver.findElement(By.xpath("//td/a[@aria-label='Page " + p + "\']")).click();
            logger.info("Page "+p+" was clicked");
        }else {
            int a = p-10;
            tenthPage.click();
            for (int i=4; i<=a; i=i+4) {
                int b=10+i;
                 try {
                     driver.findElement(By.xpath("//td/a[@aria-label='Page " + b + "\']")).click();
                     logger.info("Page "+b+" was clicked");
                 }catch (Exception e){
                     logger.error("Page '"+b+"' not found. Please check search query");
                     Assert.fail("Page '"+b+"' not found. Please check search query");
                 }
            }
            driver.findElement(By.xpath("//td/a[@aria-label='Page " + p + "\']")).click();
        }
    }

    public void saveScreenshotWithSearchResult() throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File screenshotLocation = new File("D:\\images\\nameScreenshot.png");
            FileUtils.copyFile(screenshot, screenshotLocation);
            logger.info("Element screenshot was copied to disk: " + screenshotLocation);
        }catch (Exception e){
            logger.error(errorCopyScreenshot);
            Assert.fail(errorCopyScreenshot);
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
