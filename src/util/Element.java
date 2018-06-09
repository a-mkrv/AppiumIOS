package util;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class Element {

    public static boolean SetText(WebDriver driver, String name, String str)  {

        WebElement ele = IsPresent(driver, name);

        if (ele != null) {
            ele.clear();
            ele.sendKeys(str);
            return true;
        }

        return false;
    }

    public static boolean IsEnable(WebDriver driver, String name) {

        WebElement ele = IsPresent(driver, name);

        if (ele != null && ele.isEnabled()) {
            return true;
        }

        return false;
    }

    public static boolean isPresent(WebDriver driver, String name) {
        if (IsPresent(driver, name) == null) {
            return false;
        }

        return true;
    }

    public static boolean IsActive(WebDriver driver, String name) {
        WebElement ele = IsPresent(driver, name);

        if (ele.isSelected()) {
            return true;
        }

        return false;
    }

    private static WebElement IsPresent(WebDriver driver, String name) {

        By by = MobileBy.ByAccessibilityId.name(name);
        WebElement ele = null;

        try {
            ele = driver.findElement(by);
            return ele;

        } catch (NoSuchElementException error) {
            // System.out.println("> Error: no such element: " + by.toString().toString());
            return null;
        }

    }

    public static String GetText(WebDriver driver, String name){

        WebElement ele = IsPresent(driver, name);

        if (ele != null){
            return ele.getText();
        }

        return "null";
    }

    public static boolean Click(WebDriver driver, String name){

        WebElement ele = IsPresent(driver, name);

        if (ele != null) {
            ele.click();
            return true;
        }

        return false;
    }

    public static void touchWithoutFinding(IOSDriver driver, int x, int y) {
        TouchAction action = new TouchAction(driver);
        action.press(x, y);
        action.release();
        action.perform();
    }

}
