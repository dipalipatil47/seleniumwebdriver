package pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage2 {

	WebDriver driver;

	// Constructors
	public LoginPage2(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locators

	@FindBy(xpath = "//input[@placeholder='Username']")
	WebElement uname;

	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement pass;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement login;

	@FindBy(tagName = "a")
	List<WebElement> links;

	// Action methods

	public void setUsername(String user) {
		uname.sendKeys("Admin");
	}

	public void setPassword(String password) {
		pass.sendKeys("admin123");
	}

	public void clickLogin() {
		login.click();
	}

}
