package testngprograms.annotationsdemo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AnnotationDEmo2 {
	
	@BeforeClass
	public void Login() {
		System.out.println("Login");
	}

	@Test(priority = 1)
	public void Search() {
		System.out.println("Serach");
	}

	@Test(priority = 2)
	public void AdvSearch() {
		System.out.println("Adv Search");
	}

	@AfterClass
	public void Logout() {
		System.out.println("Logout");
	}

}
