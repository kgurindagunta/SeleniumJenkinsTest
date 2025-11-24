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
import org.openqa.selenium.chrome.ChromeOptions;

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
			ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-gpu");
                 options.addArguments("--window-size=1920,1080");	
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver(options));
			break;

		case "edge":
				ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-gpu");
                 options.addArguments("--window-size=1920,1080");
			driver.set(new EdgeDriver(options));
			break;

		case "firefox":
				ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-gpu");
                 options.addArguments("--window-size=1920,1080");
			driver.set(new FirefoxDriver(options));
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
