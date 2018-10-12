package GoogleSearch;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;


public class InputSearchQueryAndNavigationBySearchPages {
    WebDriver driver = new ChromeDriver();
    HomePage homePage = new HomePage(driver);
    SearchPage searchPage = new SearchPage(driver);

    public InputSearchQueryAndNavigationBySearchPages() throws IOException {
    }

    @Test
    public void InputSearchQueryAndNavigationBySearchPages() throws IOException {

        homePage.openBrowserAndHomePage();
        homePage.inputValueInSearchField("тестирование");
        searchPage.clickByNavigationPage(17);
        searchPage.saveScreenshotWithSearchResult();
    }


    @After
    public void tearDown() { homePage.closeBrowser();
    }
}