package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends PageObject {

    @FindBy(xpath="//*[contains(text(), 'Join as an individual')]")
    private WebElement joinAsIndividualButton;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return joinAsIndividualButton.isDisplayed();
    }

    public SignUpAsIndividualPage submit(){
        joinAsIndividualButton.click();
        return new SignUpAsIndividualPage(driver);
    }
}