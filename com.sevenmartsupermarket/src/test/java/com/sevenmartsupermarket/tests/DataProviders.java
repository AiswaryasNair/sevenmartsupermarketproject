package com.sevenmartsupermarket.tests;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "usertypelist")
	public Object[][] usertypelist() {
		return new Object[][] { { "Aisw", "staff" } };
	}

	@DataProvider(name = "existingusertypelist")
	public Object[][] existingusertypelist() {
		return new Object[][] { { "Aisw", "Aisw", "staff" } };
	}
}
