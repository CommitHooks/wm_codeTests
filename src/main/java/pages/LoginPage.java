package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject {

    @FindBy(xpath="//input[@id='login-email']")
    private WebElement email;

    @FindBy(xpath="//input[@id='login-password']")
    private WebElement password;

    @FindBy (xpath="//button[@id='login_page_button']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return loginButton.isDisplayed();
    }

    public void enterEmail(String email) {
        this.email.clear();
        this.email.sendKeys(email);
    }

    public void enterPassword(String password) {
        this.password.clear();
        this.password.sendKeys(password);
    }

    public HomePage submit(){
        loginButton.click();
        return new HomePage(driver);
    }
}


