package com.seleniumWebdriver.datadriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;

public class ReadingPropertiesFile {

	public static void main(String[] args) throws IOException {

		// Location of properties file
		FileInputStream fileInputStream = new FileInputStream(
				System.getProperty("user.dir") + "\\testdata\\config.properties");

		// Loading properties file
		Properties propertiesobj = new Properties();
		propertiesobj.load(fileInputStream);

		// Reading data from properties file
		String url = propertiesobj.getProperty("appurl");
		String emailid = propertiesobj.getProperty("email");
		String pwd = propertiesobj.getProperty("password");
		String order = propertiesobj.getProperty("orderid");
		String cust = propertiesobj.getProperty("customerid");
		System.out.println(url + " " + emailid + " " + pwd + " " + order + " " + cust);

		// Reading all the keys from properties file
		Set<String> keys = propertiesobj.stringPropertyNames();
		System.out.println(keys);

		// Reading all the values from properties file
		Collection<Object> values = propertiesobj.values();
		System.out.println(values);

	}

}
