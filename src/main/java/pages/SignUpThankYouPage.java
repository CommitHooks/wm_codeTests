package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpThankYouPage extends PageObject {

    @FindBy(xpath ="//div[@class='content']/h3")
    private WebElement confirmText;

    public SignUpThankYouPage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationText(){
        return confirmText.getText();
    }
}
