package Tests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

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

        driv = new InitializeDriver("11.3");
        driv.startFlow();
        driver = driv.driver;
    }

    @Test
    public void loginFlow() throws Exception {
        System.out.println("\n@Test");
    }

    @After
    public void after() throws Exception {
        System.out.println("\n@After");
    }
}