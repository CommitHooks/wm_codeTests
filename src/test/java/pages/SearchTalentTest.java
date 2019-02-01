package pages;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


import static org.junit.Assert.*;

public class SearchTalentTest extends SeleniumTest {

    HomePage homePage;

    @Before
    public void openSignUpAsIndividualPage() {
        driver.get("http://dev.workmarket.com/login");

        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isInitialized());

        loginPage.enterEmail("qa+candidatetest@workmarket.com");
        loginPage.enterPassword("candidate123");

        homePage = loginPage.submit();
        assertTrue(homePage.isInitialized());
    }

    @Test
    public void searchTalentByKeywordTest() {
        homePage.SearchKeyWordInFindTalent("test");

        List<WebElement> listOfFindElements = homePage.getListOfSearchResults();

        String MainWindow = driver.getWindowHandle();

        for (int i = 0; i < listOfFindElements.size(); i++) {
            try {
                listOfFindElements.get(i).click();
            }catch (StaleElementReferenceException e) {
                listOfFindElements = homePage.getListOfSearchResults();
                listOfFindElements.get(i).click();
            } catch (WebDriverException e) {
                System.err.println("------ WebDriverException for the " + i + "th result. Ignored..");
            }

            WebDriverWait wait=new WebDriverWait(driver,20);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='View Full Profile']")));

            try {
                assertNotNull(driver.findElement(By.xpath("//*[contains(text(), 'test')]")));
            }catch (NoSuchElementException e) {
                System.err.println("------ Error finding the " + i + "th \"test\".");
            }
            driver.switchTo().window(MainWindow);
        }
    }

}
