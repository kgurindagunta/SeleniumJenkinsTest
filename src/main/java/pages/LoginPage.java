package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	private By uname = By.xpath("//input[@placeholder='Username']");
	private By pwd = By.xpath("//input[@placeholder='Password']");
	private By submitBtn = By.xpath("//button");
	private By wrongTextMsg = By.xpath("//p[text()='Invalid credentials']");
	
	public String errorTextAlert() {
		String alert = driver.findElement(wrongTextMsg).getText();
		return alert;
	}

	public void validLoginText(String username, String password) {

		driver.findElement(uname).sendKeys(username);
		driver.findElement(pwd).sendKeys(password);
		driver.findElement(submitBtn).click();

	}

}
