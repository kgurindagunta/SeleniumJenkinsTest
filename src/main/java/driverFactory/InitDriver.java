package driverFactory;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InitDriver {

	// public static ThreadLocal <WebDriver> driver = new ThreadLocal<>();

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public static void setDriver(WebDriver driverInstance) {

		driver.set(driverInstance);

	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void removeDriver() {
		driver.remove();
	}

	public void initBrowser(String browser) {

		switch (browser.toLowerCase()) {

		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
			break;

		case "edge":
			driver.set(new EdgeDriver());
			break;

		case "firefox":
			driver.set(new FirefoxDriver());
			break;

		default:
			throw new IllegalArgumentException("Browser not supported: " + browser);
		}
	}

	public Properties getProperties() throws IOException {
		Properties prop = new Properties();
		FileReader file = new FileReader("src/main/resources/config.properties");
		prop.load(file);
		return prop;

	}

}
