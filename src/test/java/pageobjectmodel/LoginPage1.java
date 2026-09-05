package pageobjectmodel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage1 {

	WebDriver driver;

	// Constructors
	public LoginPage1(WebDriver driver) {

		this.driver = driver;
	}

	// Locators
	By uname = By.xpath("//input[@placeholder='Username']");
	By pass = By.xpath("//input[@placeholder='Password']");
	By login = By.xpath("//button[normalize-space()='Login']");
	

	// Action methods
	public void setUsername(String user) {
		driver.findElement(uname).sendKeys(user);
	}

	public void setPassword(String password) {
		driver.findElement(pass).sendKeys(password);
	}

	public void clickLogin() {
		driver.findElement(login).click();
	}

}
