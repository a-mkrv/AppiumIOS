package Tests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static org.junit.Assert.assertTrue;
import org.apache.commons.lang3.StringUtils;
import util.Element;
import util.InitializeDriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Login_1 {

    public IOSDriver<IOSElement> driver;
    InitializeDriver driv;

    @Before
    public void before() throws Exception {
        System.out.println("\n@Before: - Login (aborted registration)");

        driv = new InitializeDriver();
        driv.startFlow();
        driver = driv.driver;

        assertTrue(Element.Click(driver, "HelpStart"));
        assertTrue(Element.Click(driver, "LoginButton"));
    }

    @Test
    public void loginFlow() throws Exception {
        System.out.println("\n@Test");

        // CASE 1:
        // Go back
        Element.Click(driver, "ForgotButton");
        Element.Click(driver, "BackBtn");
        if (Element.Click(driver, "ForgotButton")) {
            System.out.println("PASSED: Case - Device: Go back");
        }

        // CASE 2:
        // Incorrect email
        Element.SetText(driver, "EmailField", "test.email.ru");
        if (!Element.IsEnable(driver, "SendButton")) {
            System.out.println("PASSED: Case - Device: Incorrect email");
        } else {
            System.out.println("FAIL: Case - Device: Incorrect email");
        }

        // CASE 3:
        // Email length <= 100 (ПО ФТ)
        String field = StringUtils.repeat("A", 100) + "@test.ru";
        Element.SetText(driver, "EmailField", field);
        if (Element.GetText(driver, "EmailField").length() < field.length()) {
            System.out.println("PASSED: Case - Email length <= 100");
        } else {
            System.out.println("FAIL: Case - Email length <= 100");
        }

        // CASE 4:
        // Invalid characters in email
        Element.SetText(driver, "EmailField", "DogNumber#5@932.ru");
        if (!Element.IsEnable(driver, "SendButton")) {
            System.out.println("PASSED: Case - Invalid characters in email");
        } else {
            System.out.println("FAIL: Case - Invalid characters in email");
        }

        // CASE 5:
        // Email field is empty
        Element.SetText(driver, "EmailField", "");
        if (!Element.IsEnable(driver, "SendButton")) {
            System.out.println("PASSED: Case - Email field is empty");
        } else {
            System.out.println("FAIL: Case - Email field is empty");
        }

        // CASE 6:
        // Reset password (wrong email)
        Element.SetText(driver, "EmailField", "test@0079.ru");
        Element.Click(driver, "SendButton");
        if (!Element.isPresent(driver, "OkReset")) {
            System.out.println("PASSED: Case - Device: Reset password (wrong email)");
        } else {
            System.out.println("FAIL: Case - Device: Reset password (wrong email)");
        }

        // CASE 7:
        // Reset password (exist email)
        Element.SetText(driver, "EmailField", "test@0078.ru");
        Element.Click(driver, "SendButton");
        if (Element.isPresent(driver, "OkReset")) {
            Element.Click(driver, "OkReset");
            System.out.println("PASSED: Case - Device: Reset password (exist email)");
        } else {
            System.out.println("FAIL: Case - Device: Reset password (exist email)");
        }

        // CASE 8:
        // Entering non-existent mail
        Element.SetText(driver, "EmailField", "test@0080.ru");
        Element.SetText(driver, "PasswordLoginField", "password");
        assertTrue(Element.Click(driver, "GoButton"));
        if (!Element.isPresent(driver, "SerialNumberField")) {
            System.out.println("PASSED: Case - Entering non-existent mail");
        } else {
            System.out.println("FAIL: Case - Entering non-existent mail");
            Element.Click(driver, "BackBtn");
        }

        // CASE 9:
        // Incorrect password for existing mail
        Element.SetText(driver, "EmailField", "test@932.ru");
        Element.SetText(driver, "PasswordLoginField", "1234");
        assertTrue(Element.Click(driver, "GoButton"));
        if (!Element.isPresent(driver, "SerialNumberField")) {
            System.out.println("PASSED: Case - Incorrect password for existing mail");
        } else {
            System.out.println("FAIL: Case - Incorrect password for existing mail");
            Element.Click(driver, "BackBtn");
        }

        // CASE 10:
        // Enter a nonexistent password
        Element.SetText(driver, "EmailField", "test@932.ru");
        Element.SetText(driver, "PasswordLoginField", "qw12");
        assertTrue(Element.Click(driver, "GoButton"));
        if (!Element.isPresent(driver, "SerialNumberField")) {
            System.out.println("PASSED: Case - Enter a nonexistent password");
        } else {
            System.out.println("FAIL: Case - Enter a nonexistent password");
            Element.Click(driver, "BackBtn");
        }


        Element.SetText(driver, "EmailField", "test@0078.ru");
        Element.SetText(driver, "PasswordLoginField", "password");
        assertTrue(Element.Click(driver, "GoButton"));


        /// Device registration

        // CASE 11:
        // One empty field
        Element.SetText(driver, "SerialNumberField", "621010002");
        if (!Element.IsEnable(driver, "GoButton")) {
            System.out.println("PASSED: Case - Device: One empty field");
        } else {
            System.out.println("FAIL: Case - Device: One empty field");
        }

        // CASE 12:
        // Invalid characters in serial number fields
        Element.SetText(driver, "SerialNumberField", "621!0@0a");
        Element.SetText(driver, "PasswordActiveField", "123#");
        if (!Element.IsEnable(driver, "GoButton")) {
            System.out.println("PASSED: Case - Device: Invalid characters in serial number/password");
        } else {
            System.out.println("FAIL: Case - Device: Invalid characters in serial number/password");
        }

        // CASE 13:
        // Serial/password length <= 9, 6
        field = StringUtils.repeat("1", 10);
        Element.SetText(driver, "SerialNumberField", field);
        Element.SetText(driver, "PasswordActiveField", field);

        if (Element.GetText(driver, "SerialNumberField").length() < field.length() &&
                Element.GetText(driver, "PasswordActiveField").length() < field.length()) {
            System.out.println("PASSED: Case - Device: Serial/password length <= 9, 6");
        } else {
            System.out.println("FAIL: Case - Device: Serial/password length <= 9, 6");
        }

        // CASE 14:
        // Open Quick Guide
        assertTrue(Element.Click(driver, "GuideButton"));
        if (Element.isPresent(driver, "GuideImage")) {
            Element.Click(driver, "CloseGuideButton");
            System.out.println("PASSED: Case - Device: Open Quick Guide");
        } else {
            System.out.println("FAIL: Case - Device: Open Quick Guide");
        }
    }

    @After
    public void after() throws Exception {
        System.out.println("\n@After");
    }
}