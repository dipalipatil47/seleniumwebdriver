package testngprograms;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class OrangeHRMTest {

	WebDriver driver;

	@Test(priority = 1)
	public void openapp() {
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
	}

	@Test(priority = 2)
	public void testLogo() throws InterruptedException {
		Thread.sleep(3000);
		boolean logo=driver.findElement(By.xpath("//img[@alt='company-branding']")).isDisplayed();
		System.out.println("Logo displayed"+logo);

	}

	@Test(priority = 3)
	public void testLogin() {
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	@Test(priority = 4)
	public void testLogout() {
		driver.close();

	}
}
