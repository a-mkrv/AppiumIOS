package util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Screenshot {

    private static void takeScreenShot(String filepath, WebDriver driver){
        File screenShot =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(screenShot, new File(filepath));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void takeScreenShot(WebDriver driver) {
        String fileName = String.valueOf(new Date().getTime()+".jpg");
        File dir = new File("test-output/screenshots");
        if (!dir.exists()){
            dir.mkdir();
        }
        String screenShotPath = dir.getAbsolutePath()+"/"+fileName;

        takeScreenShot(screenShotPath, driver);
    }
}
