package testngprograms.annotationsdemo;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AnnotationDemo1 {

	@BeforeMethod
	public void testLogin() {
		System.out.println("Login");
	}

	@Test(priority = 1)
	public void testSearch() {
		System.out.println("Serach");
	}


	@Test(priority = 2)
	public void testAdvSearch() {
		System.out.println("Adv Search");
	}

	@AfterMethod
	public void testLogout() {
		System.out.println("Logout");
	}

}
