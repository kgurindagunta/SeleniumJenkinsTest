package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtil {
	
	public static String  takeScreenshot(WebDriver driver,String screenhsotName) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date());
		
		String path = System.getProperty("user.dir")+"\\target\\"+screenhsotName+ timeStamp+".png";
		
		
		File destination = new File(path);
		FileHandler.copy(source, destination);
		
		System.out.println("screenshot captured in" +destination);
		
		return path;
		
		
	}

}
