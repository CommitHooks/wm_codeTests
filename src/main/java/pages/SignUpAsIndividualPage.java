package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpAsIndividualPage extends PageObject {

    @FindBy(xpath="//*[contains(text(), 'Register')]")
    private WebElement registerButton;

    @FindBy(xpath="//input[@id='firstname']")
    private WebElement firstName;

    @FindBy(xpath="//input[@id='lastname']")
    private WebElement lastName;

    @FindBy(xpath="//input[@id='email']")
    private WebElement email;

    @FindBy(xpath="//input[@id='password']")
    private WebElement password;

    @FindBy(xpath="//input[@type='checkbox']")
    private WebElement consentCheck;

    public SignUpAsIndividualPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return registerButton.isDisplayed();
    }

    public void enterName(String firstName, String lastName){
        this.firstName.clear();
        this.firstName.sendKeys(firstName);

        this.lastName.clear();
        this.lastName.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        this.email.clear();
        this.email.sendKeys(email);
    }

    public void enterPassword(String password) {
        this.password.clear();
        this.password.sendKeys(password);
    }

    public void setConsentCheck() {
        if (!consentCheck.isSelected()) {
            this.consentCheck.click();
        }
    }

    public SignUpThankYouPage submit(){
        registerButton.click();
        return new SignUpThankYouPage(driver);
    }

}
