package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	
	public ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	//public ExtentTest test;
	
	String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date());
	
	public  void setupExtentReport() {
		
		String reportName = "Extent-Report" +date+".html";
		//sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/target/extentReports"+reportName);
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/target/extentReports/index.html");
		sparkReporter.config().setDocumentTitle("OrangeHRM LoginTest");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("OrangeHRM Extent LoginReport");
		sparkReporter.config().setTimeStampFormat("yyyy-MM-dd_HH-mm-ss-SSS");
		extent = new ExtentReports();
		extent.setSystemInfo("Device", "windows");
		extent.setSystemInfo("Website", "OrangeHrm");
		extent.setSystemInfo("Env", "QA");
		extent.setSystemInfo("Build", "1");
		extent.setSystemInfo("QA owner", "Kiran");
		extent.attachReporter(sparkReporter);	
	}
	
//	public  void endReport() {
//		extent.flush();
//	}
	
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void startTest(String testName) {
        ExtentTest test = extent.createTest(testName);
        extentTest.set(test);
    }

    public void flush() {
        if (extent!= null) {
        	extent.flush();
        }
    }
	
	
	
	

}
