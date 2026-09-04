package testngprograms;

import org.testng.annotations.Test;

public class FirstTestCase {

	

	@Test(priority =0 )
	public void login() {
		System.out.println("Login app");
	}

	@Test(priority =1 )
	public void logout() {
		System.out.println("Logout app");
	}
	@Test(priority = -100)
	public void openapp() {
		System.out.println("Opening app");
	}

}
