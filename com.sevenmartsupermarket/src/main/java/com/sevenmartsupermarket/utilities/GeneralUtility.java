package com.sevenmartsupermarket.utilities;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.github.javafaker.Faker;

public class GeneralUtility {

	public String get_attribute(WebElement element, String attribute) {

		return element.getAttribute(attribute);

	}
//get informations like having return value is stored in general utility
	public List<String> gettextOfeleemnts(List<WebElement> elements) {
		List<String> data = new ArrayList<String>();
		for (WebElement element : elements) {
			data.add(element.getText());
		}
		return data;
	}

	public String get_cssvalue(WebElement elements, String value) {
		return elements.getAttribute(value);

	}

	public static String getRandomFullName() {
		Faker faker = new Faker();
		return faker.name().fullName();
	}

	public static String getRandomFirstName() {
		Faker faker = new Faker();
		String name = faker.name().fullName();
		String firstName = faker.name().firstName();

		return firstName;
	}

	public static String getRandomLastName() {
		Faker faker = new Faker();
		String name = faker.name().fullName();
		String lastName = faker.name().lastName();
		return lastName;
	}
	// String streetAddress = faker.address().streetAddress();
	// return streetAddress;

	public boolean istextContain(WebElement element, String expectedText) {
		return element.getText().contains(expectedText);
	}
}
