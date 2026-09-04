package dataprovider;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderDemo {

	WebDriver driver;

	@BeforeClass // Because setup will execute only one time
	public void setUp() {

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@Test(dataProvider = "dp")
	public void testLogin(String email, String password) throws InterruptedException {

		driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Thread.sleep(2000);
		boolean status = driver.findElement(By.xpath("//span[normalize-space()='My Account']")).isDisplayed();
		if (status == true) {
			driver.findElement(By.xpath("//a[@class='list-group-item'][normalize-space()='Logout']")).click();
			Assert.assertTrue(true);
		} else {
			Assert.fail();
		}

	}

	@AfterClass // after completion of login teardown methods execute only once
	public void tearDown() {
		driver.close();
	}

	@DataProvider(name = "dp",indices= {0,3,4})
	public Object[][] loginData() {
		
		Object data[][] =
			{ 
				{ "dipalipatil23@gmail.com", "12345" },
				{ "dip123@gmail.com", "1234" },
				{"dip123@gmail.com", "1234" },
				{"dip123@gmail.com", "1234" },
				{ "dipalipatil23@gmail.com", "12345" },

			};
		
		return data;
	}

}
