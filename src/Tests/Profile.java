package Tests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.Constants;
import util.Element;
import util.InitializeDriver;

import static org.junit.Assert.assertTrue;

public class Profile {

    public IOSDriver<IOSElement> driver;
    InitializeDriver driv;

    @Before
    public void before() throws Exception {
        System.out.println("\n@Before: - Profile ");

        driv = new InitializeDriver();
        driv.startFlow();
        driver = driv.driver;

        assertTrue(Element.Click(driver, "HelpStart"));
        assertTrue(Element.Click(driver, "LoginButton"));
    }

    @Test
    public void profileFlow() throws Exception {
        Element.SetText(driver, "EmailField", Constants.TestAccountLogin);
        Element.SetText(driver, "PasswordLoginField", Constants.TestAccountPassword);
        assertTrue(Element.Click(driver, "GoButton"));

        Thread.sleep(6000);

        assertTrue(Element.Click(driver, "yasnoponyatnoButton"));
        assertTrue(Element.Click(driver, "closeGuideButton"));
        System.out.println("Successful authorization");
        assertTrue(Element.Click(driver, "ProfileTab"));

        // Open first pet

        // CASE 1 (C42084)
        // Switch to the editing pet mode
        Element.touchWithoutFinding(driver, 45, 300);
        if (Element.isPresent(driver, "dogName")) {
            System.out.println("PASSED: Case (C42084) - Switch to the editing pet mode");
        } else {
            System.out.println("FAIL: Case (C42084) - Switch to the editing pet mode");
        }

        // CASE 2 (C42085)
        // Back button and return to screen
        Element.Click(driver, "BackBtn");
        Element.touchWithoutFinding(driver, 45, 300);
        if (Element.isPresent(driver, "dogName")) {
            System.out.println("PASSED: Case (C42085) - Back button and return to screen");
        } else {
            System.out.println("FAIL: Case (C42085) - Back button and return to screen");
        }

        // CASE 3 (C42088)
        // Not clickable fields in profile view mode
        if (Element.Click(driver, "dogName") &&
                Element.Click(driver, "dogAvatar") &&
                Element.Click(driver, "BreedButton") &&
                Element.Click(driver, "Gender") &&
                Element.Click(driver, "ageLabel") &&
                Element.Click(driver, "Weight") &&
                Element.Click(driver, "Height") &&
                Element.Click(driver, "Castrated") &&
                Element.Click(driver, "Diseases")) {
            System.out.println("PASSED: Case (C42088) - Not clickable fields in profile view mode");
        } else {
            System.out.println("FAIL: Case (C42088) - Not clickable fields in profile view mode");
        }

        // CASE 4 (C42154)
        // Enter the correct value in the Name field
        assertTrue(Element.Click(driver, "editMode"));
        String field = StringUtils.repeat("Д", 5);
        field += StringUtils.repeat("R", 5);
        field += StringUtils.repeat("-", 5);
        field += StringUtils.repeat(" ", 5);
        field += StringUtils.repeat("'", 5);
        field += StringUtils.repeat("S", 5);
        Element.SetText(driver, "dogName", field);
        assertTrue(Element.Click(driver, "editMode"));

        Thread.sleep(1000);
        if (Element.isPresent(driver, "dogName")) {
            System.out.println("FAIL: Case (C42154) - Enter the correct value in the Name field");
        } else {
            System.out.println("PASSED: Case (C42154) - Enter the correct value in the Name field");
        }

        // CASE 5 (C42159)
        // Field Name is empty.
        Element.touchWithoutFinding(driver, 45, 300);
        assertTrue(Element.Click(driver, "editMode"));
        Element.SetText(driver, "dogName", "");
        assertTrue(Element.Click(driver, "editMode"));
        driver.findElementByName("Понятно").click();
        Thread.sleep(500);
        if (Element.isPresent(driver, "dogName")) {
            System.out.println("PASSED: Case (C42154) - Field Name is empty");
        } else {
            System.out.println("FAIL: Case (C42154) - Field Name is empty");
        }

        // CASE 6 (C42215)
        // Selecting the gender of the pet
        if (Element.isPresent(driver, "Sterilized")) {
            Element.Click(driver, "Gender");
            if (Element.isPresent(driver, "Castrated")) {
                System.out.println("PASSED: Case (C42215) - Selecting the gender of the pet (Girl -> Boy)");
            } else {
                System.out.println("FAIL: Case (C42215) - Selecting the gender of the pet (Girl -> Boy)");
            }
        } else {
            Element.Click(driver, "Gender");
            if (Element.isPresent(driver, "Castrated")) {
                System.out.println("FAIL: Case (C42215) - Selecting the gender of the pet (Boy -> Girl)");
            } else {
                System.out.println("PASSED: Case (C42215) - Selecting the gender of the pet (Boy -> Girl)");
            }
        }

        // CASE 7 (C42217)
        // Calculating the age of the pet
        String firstAge = (Element.GetText(driver, "ageLabel"));
        Element.Click(driver, "ageLabel");
        for (int i = 0; i < 5; i++) {
            driver.swipe(0, 200, 0, 700, 100);
        }
        Element.touchWithoutFinding(driver, 45, 300);
        String secondAge = (Element.GetText(driver, "ageLabel"));

        if (firstAge != secondAge) {
            System.out.println("PASSED: Case (C42217) - Calculating the age of the pet (age was changed " + firstAge + " -> " + secondAge + ")");
        } else {
            System.out.println("FAIL: Case (C42217) - Calculating the age of the pet (age was't changed)");
        }

        // CASE 8 (C42216)
        // The age unit of the pet is younger the year
        String age = Element.GetText(driver, "ageLabel");
        if (age.contains("месяц")) {
            System.out.println("PASSED: Case (C42216) - The age unit of the pet is younger the year");
        } else {
            System.out.println("FAIL: Case (C42216) - The age unit of the pet is younger the year");
        }


        // CASE 9 (C42219)
        // The age unit of the pet is over the year
        Element.Click(driver, "ageLabel");
        Element.Click(driver, "dateButton");
        driver.swipe(0, 200, 0, 700, 100);
        driver.swipe(0, 200, 0, 700, 100);
        Element.touchWithoutFinding(driver, 45, 300);
        Element.touchWithoutFinding(driver, 45, 300);
        age = Element.GetText(driver, "ageLabel");
        if (age.contains("год")) {
            System.out.println("PASSED: Case (C42219) - The age unit of the pet is over the year (" + age + ")");
        } else {
            System.out.println("FAIL: Case (C42219) - The age unit of the pet is over the year");
        }

        // CASE 10 (C42220)
        // Weight in edit mode (not entered)
        assertTrue(Element.Click(driver, "Weight"));
        System.out.println("PASSED: Case (C42220) - Weight in edit mode (not entered)");

//        // CASE 11 (C42221)
//        // Block Advanced (boy)
//        if (Element.GetText(driver, "Castrated") == "Не кастрирован") {
//            System.out.println("PASSED: Case (C42221) - Block Advanced (boy). By default not castrated");
//        } else {
//            System.out.println("FAIL: Case (C42221) - Block Advanced (boy). By default castrated");
//        }
//
//        // CASE 12 (C42222)
//        // Block Advanced (girl)
//        Element.Click(driver, "Gender");
//        if (!Element.IsActive(driver, "Sterilized")) {
//            System.out.println("PASSED: Case (C42222) - Block Advanced (girl). By default not sterilized");
//        } else {
//            System.out.println("FAIL: Case (C42222) - Block Advanced (girl). By default sterilized");
//        }

        // CASE 13 (C42223)
        // Selecting a position in the Additional section


        // CASE 14 (C42352)
        // Block Diseases in profile view mode. No diseases
        Element.SetText(driver, "dogName", "Lord");
        assertTrue(Element.Click(driver, "editMode"));
        Element.touchWithoutFinding(driver, 45, 300);
        Element.Click(driver, "Diseases");
        if (Element.isPresent(driver, "dogName")) {
            System.out.println("PASSED: Case (C42352) - Block Diseases in profile view mode. No diseases");
        } else {
            System.out.println("FAIL: Case (C42352) - Block Diseases in profile view mode. No diseases");
        }

        // CASE 15 (C42943)
        // Block Diseases in profile view mode. With diseases
        assertTrue(Element.Click(driver, "editMode"));
        Element.Click(driver, "Diseases");
        // Select random disease
        Element.touchWithoutFinding(driver, 45, 300);
        assertTrue(Element.Click(driver, "addButton"));
        assertTrue(Element.Click(driver, "editMode"));
        Element.touchWithoutFinding(driver, 45, 300);
        assertTrue(Element.Click(driver, "Diseases"));

        if (Element.isPresent(driver, "dogName")) {
            System.out.println("FAIL: Case (C42943) - Block Diseases in profile view mode. With diseases");
        } else {
            System.out.println("PASSED: Case (C42943) - Block Diseases in profile view mode. With diseases");
        }

        // CASE 16 (C47355)
        // Finding a breed
        assertTrue(Element.Click(driver, "cancelButton"));
        assertTrue(Element.Click(driver, "editMode"));
        assertTrue(Element.Click(driver, "BreedButton"));
        Element.SetText(driver, "SearchBar", "TestBreed");
        Element.touchWithoutFinding(driver, 119, 145);

        if (Element.isPresent(driver, "dogName")) {
            System.out.println("FAIL: Case (C47355) - Finding a breed");
        } else {
            System.out.println("PASSED: Case (C47355) - Finding a breed");
        }

        // CASE 17 (C47356, C47357)
        // Entering the digits in the Name field
        Element.SetText(driver, "dogName", "Собака#5");
        assertTrue(Element.Click(driver, "editMode"));
        driver.findElementByName("Понятно").click();
        Thread.sleep(1000);
        if (Element.isPresent(driver, "dogName")) {
            System.out.println("PASSED: Case (C47356, C47357) - Entering the digits in the Name field");
        } else {
            System.out.println("FAIL: Case (C47356, C47357) - Entering the digits in the Name field");
        }

        // CASE 18 (C47358)
        // Enter more than 50 characters in the Name field
        Element.SetText(driver, "dogName", StringUtils.repeat("G", 60));
        assertTrue(Element.Click(driver, "editMode"));
        Thread.sleep(2000);
        if (Element.isPresent(driver, "dogName")) {
            System.out.println("PASSED: Case (C47358) - Can't enter more than 50 characters in the Name field");
        } else {
            System.out.println("FAIL: Case (C47358) - Enter more than 50 characters in the Name field");
        }

        // CASE 19 (C47359)
        // The choice of birth dates later than the current.
        firstAge = (Element.GetText(driver, "ageLabel"));
        Element.Click(driver, "ageLabel");
        driver.swipe(0, 700, 0, 200, 100);
        Element.touchWithoutFinding(driver, 45, 300);
        assertTrue(Element.Click(driver, "Понятно"));
        assertTrue(Element.Click(driver, "BackBtn"));
        secondAge = (Element.GetText(driver, "ageLabel"));

        if (firstAge == secondAge) {
            System.out.println("PASSED: Case (C47359) - The choice of birth dates later than the current");
        } else {
            System.out.println("PASSED: Case (C47359) - Dates differ after opening the calendar");
        }

        // CASE 20 (C42090)
        // Pet avatar in profile editing mode
        assertTrue(Element.Click(driver, "dogAvatar"));
        driver.findElementByName("Выбрать").click();
        driver.findElementByName("Разрешить").click();
        driver.findElementByName("Разрешить").click();
        Element.touchWithoutFinding(driver, 20, 140);
        Element.touchWithoutFinding(driver, 5, 140);
        driver.findElementByName("Выбрать").click();
    }

    @After
    public void after() throws Exception {
        System.out.println("\nEnd.");
    }
}
