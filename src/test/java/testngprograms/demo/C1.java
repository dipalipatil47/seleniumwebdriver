package testngprograms.demo;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class C1 {

	@Test
	void abc() {
		System.out.println("this is abc from c1F ");
	}

	@BeforeTest
	void bt() {
		System.out.println("this is before test method ");
	}

}
