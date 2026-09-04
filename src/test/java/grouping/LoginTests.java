package grouping;

import org.testng.annotations.Test;

public class LoginTests {

	@Test(priority = 1,groups = {"Sanity"})
	void loginByEmail() {
		System.out.println("Email");

	}

	@Test(priority = 2,groups = {"Sanity"})
	void loginByFb() {
		System.out.println("Fb");
	}

	@Test(priority = 3,groups = {"Sanity"})
	void loginByTwitter() {
		System.out.println("Twiter");

	}

}
