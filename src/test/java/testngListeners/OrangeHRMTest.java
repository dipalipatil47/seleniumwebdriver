package testngListeners;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(testngListeners.MyListener.class)
public class OrangeHRMTest {
	
	WebDriver driver;
	
	@BeforeClass
	void setup() throws InterruptedException {
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		Thread.sleep(3000);
	}

	@Test(priority = 1)
	public void testLogo() throws InterruptedException {
		Thread.sleep(3000);
		boolean logo=driver.findElement(By.xpath("//img[@alt='company-branding']")).isDisplayed();
		Assert.assertEquals(logo, true);

	}
	
	@Test(priority = 2)
	public void testappurl() throws InterruptedException {
		Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/auth/logindfe");

	}

	@Test(priority = 3,dependsOnMethods = {"testappurl"})
	public void testhomepageTitle() {
		Assert.assertEquals(driver.getTitle(), "OrangeHRM");
	}

	@AfterClass
	public void testLogout() {
		driver.close();

	}

}
