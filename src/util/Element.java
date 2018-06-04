package util;

import io.appium.java_client.MobileBy;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class Element {

    static Map<String, WebElement> elements = new HashMap<String, WebElement>();

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

    private static WebElement IsPresent(WebDriver driver, String name) {

        if (elements.get(name) != null) {
            return elements.get(name);
        }

        By by = MobileBy.ByAccessibilityId.name(name);
        WebElement ele = null;

        try {
            ele = driver.findElement(by);
            elements.put(name, ele);
            return ele;

        } catch (NoSuchElementException error) {
            System.out.println("> Error: no such element: " + by.toString().toString());
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
}
