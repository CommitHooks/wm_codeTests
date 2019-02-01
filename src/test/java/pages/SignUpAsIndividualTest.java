package pages;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import utils.RandomNumberGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class SignUpAsIndividualTest extends SeleniumTest {
    SignUpAsIndividualPage individualSignUpPage;

    @Before
    public void openSignUpAsIndividualPage() {
        driver.get("https://dev.workmarket.com/register/campaign/10081C503B209A0C8E7F05FDCC1AA98D4C904DEEF5F73265CAE38C744E7EAD3E");

        SignUpPage signUpPage = new SignUpPage(driver);
        assertTrue(signUpPage.isInitialized());

        individualSignUpPage = signUpPage.submit();
        assertTrue(individualSignUpPage.isInitialized());
    }

    public String createRandomName(String aname) {
        int num = RandomNumberGenerator.generateOneNum(100, 10000);
        return aname + num;
    }

    public SignUpThankYouPage stepsToSubmitIndividualSignUp(String firstName, String lastName, String email, String password) {
        individualSignUpPage.enterName(firstName, lastName);
        individualSignUpPage.enterEmail(email);
        individualSignUpPage.enterPassword(password);
        individualSignUpPage.setConsentCheck();

        return individualSignUpPage.submit();
    }

    @Test
    public void firstTimeHappyRegisterTest(){
        String firstName = createRandomName("First");
        String lastName = createRandomName("Last");
        String email = firstName + "." + lastName + "@" + "test.com";
        String password = "Password778934!&";

        SignUpThankYouPage thankYouPage = stepsToSubmitIndividualSignUp(firstName, lastName, email, password);
        assertEquals("Next Steps", thankYouPage.getConfirmationText());
    }

    @Test
    public void wrongFormatOfEmailAddressTest(){
        String firstName = createRandomName("First");
        String lastName = createRandomName("Last");
        String email = firstName + "@" + lastName + "@" + "test.com";
        String password = "Password778934!&";

        try {
            SignUpThankYouPage thankYouPage = stepsToSubmitIndividualSignUp(firstName, lastName, email, password);
            assertNotEquals("Next Steps", thankYouPage.getConfirmationText());
        } catch (NoSuchElementException e) {
            System.err.println("------ Test wrong format of email address.");
        }
    }

    @Test
    public void tooSimplePasswordTest(){
        String firstName = createRandomName("First");
        String lastName = createRandomName("Last");
        String email = firstName + "." + lastName + "@" + "test.com";
        String password = "Password";

        try {
            SignUpThankYouPage thankYouPage = stepsToSubmitIndividualSignUp(firstName, lastName, email, password);
            assertNotEquals("Next Steps", thankYouPage.getConfirmationText());
        } catch (NoSuchElementException e) {
           System.err.println("------ Test password is too simple.");
        }
    }

    @Test
    public void duplicatedRegisterTest(){
        String firstName = "Winter";
        String lastName = "Summer";
        String email = firstName + "." + lastName + "@" + "test.com";
        String password = "Password778934!&";

        try {
            SignUpThankYouPage thankYouPage = stepsToSubmitIndividualSignUp(firstName, lastName, email, password);
            assertNotEquals("Next Steps", thankYouPage.getConfirmationText());
        } catch (NoSuchElementException e) {
            System.err.println("------ Test user that is already registered.");
        }
    }
}
