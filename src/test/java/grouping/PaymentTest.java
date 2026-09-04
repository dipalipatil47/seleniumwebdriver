package grouping;

import org.testng.annotations.Test;

public class PaymentTest {

	@Test(priority = 1,groups = {"Sanity","Regression","Functional"})
	void paymentInRuppes() {
		System.out.println("Ruppes");

	}

	@Test(priority = 2,groups = {"Sanity","Regression","Functional"})
	void paymentInDollrs() {
		System.out.println("Dollrs");
	}

}
