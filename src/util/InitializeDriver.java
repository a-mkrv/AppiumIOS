package util;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class InitializeDriver {

    public IOSDriver<IOSElement> driver;

    public InitializeDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.7.2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.2.5");
        capabilities.setCapability(MobileCapabilityType.UDID, Constants.UDID);
        capabilities.setCapability(MobileCapabilityType.APP, Constants.APP);
        capabilities.setCapability("bundleId", Constants.BundledID);
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        //capabilities.setCapability(IOSMobileCapabilityType.AUTO_DISMISS_ALERTS, Boolean.TRUE);
        //capabilities.setCapability(IOSMobileCapabilityType.LOCATION_SERVICES_ENABLED,false);

        try{
            URL remoteAddress = new URL("http://0.0.0.0:4723/wd/hub");
            driver = new IOSDriver<>(remoteAddress, capabilities);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
        catch(IOException e){
            System.out.println("Error creating the device for testing");
            e.printStackTrace();
        }
    }

    public void startFlow() {
        System.out.println("> Start Flow");

        handleAllerts();
        scrollByDown();
    }

    void scrollByDown() {
        System.out.println("Scrolling by down...");

        driver.swipe(0, 800, 0, 1, 100);
        driver.swipe(0, 800, 0, 1, 100);
        driver.swipe(0, 800, 0, 1, 100);
        driver.swipe(0, 800, 0, 1, 100);
    }

    void handleAllerts() {

        System.out.println("Waiting for notifications alert:");
        driver.findElementByName("Разрешить").click();
        System.out.println("Accept!");

        System.out.println("Waiting for location alert:");
        driver.findElementByName("Запретить").click();
        System.out.println("Dismiss!");
    }
}

