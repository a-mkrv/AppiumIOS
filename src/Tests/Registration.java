package Tests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import org.apache.commons.lang3.StringUtils;
import util.Element;
import util.InitializeDriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Registration {

    public IOSDriver<IOSElement> driver;
    InitializeDriver driv;

    @Before
    public void before() throws Exception {
        System.out.println("\n@Before: - Registration");

        driv = new InitializeDriver();
        driv.startFlow();
        driver = driv.driver;

        assertTrue(Element.Click(driver, "HelpStart"));
        assertTrue(Element.Click(driver, "SignUpButton"));
    }

    @Test
    public void registrationFlow() throws Exception {
        System.out.println("\n@Test");


        // CASE 1:
        // One empty field
        Element.SetText(driver, "PasswordField", "email");
        Element.SetText(driver, "ConfirmPasswordField", "email");
        assertTrue(Element.Click(driver, "UACheckBox"));
        if (!Element.IsEnable(driver, "GoButton")) {
            System.out.println("PASSED: Case - One empty field");
        } else {
            System.out.println("FAIL: Case - One empty field");
        }

        // CASE 2:
        // Different Passwords
        Element.SetText(driver, "EmailField", "test@932.ru");
        Element.SetText(driver, "PasswordField", "password_1");
        Element.SetText(driver, "ConfirmPasswordField", "password_2");

        String pass_1 = Element.GetText(driver, "PasswordField");
        String confirm = Element.GetText(driver, "ConfirmPasswordField");

        Element.Click(driver, "GoButton");
        if (Element.isPresent(driver, "SerialNumberField")) {
            System.out.println("FAIL: Case - Different Passwords");
            Element.Click(driver, "BackButton");
        } else {
            System.out.println("PASSED: Case - Different Passwords");
        }

        // CASE 3:
        // Inactive checkbox
        Element.Click(driver, "UACheckBox");
        if (!Element.IsEnable(driver, "GoButton")) {
            System.out.println("PASSED: Case - Inactive checkbox");
            Element.Click(driver, "UACheckBox");
        } else {
            System.out.println("FAIL: Case - Inactive checkbox");
        }

        // CASE 4:
        // Invalid characters in email
        Element.SetText(driver, "EmailField", "DogNumber#5@932.ru");
        if (!Element.IsEnable(driver, "GoButton")) {
            System.out.println("PASSED: Case - Invalid characters in email");
        } else {
            System.out.println("FAIL: Case - Invalid characters in email");
        }

        // CASE 5:
        // Invalid email mask
        Element.SetText(driver, "EmailField", "steingrims@a.m");
        if (!Element.IsEnable(driver, "GoButton")) {
            System.out.println("PASSED: Case - Invalid email mask");
        } else {
            System.out.println("FAIL: Case - Invalid email mask");
        }

        // CASE 6:
        // Email length <= 100 (ПО ФТ)
        String field = StringUtils.repeat("A", 100) + "@test.ru";
        Element.SetText(driver, "EmailField",field);
        if (Element.GetText(driver, "EmailField").length() < field.length()) {
            System.out.println("PASSED: Case - Email length <= 100");
            Element.SetText(driver, "EmailField", "test@932.ru");
        } else {
            System.out.println("FAIL: Case - Email length <= 100");
        }

        // CASE 7:
        // Password length <= 50
        field = StringUtils.repeat("A", 51);
        Element.SetText(driver, "PasswordField",field);
        Element.SetText(driver, "ConfirmPasswordField",field);

        if (Element.GetText(driver, "PasswordField").length() < field.length()
                && Element.GetText(driver, "ConfirmPasswordField").length() < field.length()) {
            System.out.println("PASSED: Case - Password length <= 50");
        } else {
            System.out.println("FAIL: Case - Password length <= 50");
        }

        // CASE 8:
        // Open User Agreement
        assertTrue(Element.Click(driver, "UserAgreement"));
        if (Element.isPresent(driver, "UATextView")) {
            Element.Click(driver, "BackBtn");
            System.out.println("PASSED: Case - Open User Agreement");
        } else {
            System.out.println("FAIL: Case - Open User Agreement");
        }

        // CASE 9:
        // Email already in use
        Element.SetText(driver, "EmailField", "test@932.ru");
        Element.SetText(driver, "PasswordField", "password");
        Element.SetText(driver, "ConfirmPasswordField", "password");

        assertTrue(Element.Click(driver, "GoButton"));
        if (Element.isPresent(driver, "LoginButton")) {
            System.out.println("PASSED: Case - Email already in use");
        } else {
            System.out.println("FAIL: Case - Email already in use");
        }

        // CASE 10:
        // Success registration
        Element.SetText(driver, "EmailField", "test@0079.ru");
        Element.SetText(driver, "PasswordField", "password");
        Element.SetText(driver, "ConfirmPasswordField", "password");
        assertTrue(Element.Click(driver, "GoButton"));

        if (Element.isPresent(driver, "SerialNumberField")) {
            System.out.println("PASSED: Case - Success registration");
        } else {
            System.out.println("FAIL: Case - Success registration");
        }

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
        System.out.println("\nEnd.");
    }
}