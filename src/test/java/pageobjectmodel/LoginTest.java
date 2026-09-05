package pageobjectmodel;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {

	WebDriver driver;
	LoginPage1 loginPage;

	@BeforeClass
	void setup() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
	}

	@Test
	void testLogin() {

		LoginPage2 lp = new LoginPage2(driver);
		lp.setUsername("Admin");
		lp.setPassword("admin123");
		lp.clickLogin();

		Assert.assertEquals(driver.getTitle(), "OrangeHRM");
	}

	@AfterClass
	void tearDown() {
		driver.close();

	}

}
