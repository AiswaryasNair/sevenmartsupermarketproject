package com.sevenmartsupermarket.DataProviders;

import org.testng.annotations.DataProvider;

import com.sevenmartsupermarket.utilities.ExcelReader;

public class DataProviders {

	ExcelReader excelreader=new ExcelReader();
	@DataProvider(name = "usertypelist")
	public Object[][] usertypelist() {
		return new Object[][] { { "Aisw", "staff" } };
	}

	@DataProvider(name = "existingusertypelist")
	public Object[][] existingusertypelist() {
		return new Object[][] { { "Aisw", "Aisw", "staff" } };
	}
	//Load data from excel using data provider
	@DataProvider(name = "excelread")
	public Object[][] excelread() {
		excelreader.setExcelFile("ManageLocationData", "NewLoc");
		return excelreader.getMultidimentionalData(2, 2);
	}
}
