package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class HomePage extends PageObject {

    @FindBy(xpath="//div[contains(text(),'Find Talent')]")
    private WebElement findTalentLink;

    @FindBy(xpath="//input[@id='input-text']")
    private WebElement searchBox;

    @FindBy(xpath="//div[@id='cart']")
    private WebElement searchResultDisplayPanel;

    @FindBy(xpath="//div[@id='cart']//h2/a")
    private List<WebElement> listOfSearchResults;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return findTalentLink.isDisplayed();
    }

    public void SearchKeyWordInFindTalent(String word){
        this.findTalentLink.click();

        if (searchBox.isDisplayed()) {
            searchBox.clear();
            searchBox.sendKeys(word);
            searchBox.sendKeys(Keys.RETURN);

            waitForSearchResult();
        }
    }

    public void waitForSearchResult() {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = searchResultDisplayPanel;
                if(searchResultDisplayPanel.isDisplayed()){
                    System.out.println("Search result is displayed");
                    return element;
                }else{
                    System.out.println("FluentWait on search result Failed");
                    return null;
                }
            }
        });
    }

    public List<WebElement> getListOfSearchResults() {
        return listOfSearchResults;
    }
}
