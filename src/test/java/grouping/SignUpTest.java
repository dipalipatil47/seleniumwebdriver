package grouping;

import org.testng.annotations.Test;

public class SignUpTest {

	@Test(priority = 1,groups= {"Regression"})
	void signupByEmail() {
		System.out.println("Signup Email");

	}

	@Test(priority = 2,groups= {"Regression"})
	void signUpByFb() {
		System.out.println("Signup Fb");
	}

	@Test(priority = 3,groups= {"Regression"})
	void signUpTwitter() {
		System.out.println("SignUp Twiter");

	}
}
