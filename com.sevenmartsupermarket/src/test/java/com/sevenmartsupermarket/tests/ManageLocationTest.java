package com.sevenmartsupermarket.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.pages.AdminUsersPage;
import com.sevenmartsupermarket.pages.LoginPage;
import com.sevenmartsupermarket.pages.ManageLocationPage;
import com.sevenmartsupermarket.utilities.ExcelReader;
import com.sevenmartsupermarket.utilities.GeneralUtility;
public class ManageLocationTest extends Base {

	LoginPage loginpage;
	ManageLocationPage managelocationpage;
	AdminUsersPage adminuserspage;
	ExcelReader excelreader=new ExcelReader();
	@Test
	public void createNewLocation() {
		loginpage = new LoginPage(driver);
		managelocationpage = new ManageLocationPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		excelreader.setExcelFile("ManageLocationData","Locations");//workbooknmae,sheetname
		String country= excelreader.getCellData(1, 0);//cell value
		String state= excelreader.getCellData(1, 1);
		String location= excelreader.getCellData(1, 2);
		int charge = Integer.parseInt(excelreader.getCellData(1, 3));
		//description=description+GeneralUtility.getRandomFirstName();//avoid duplication
		managelocationpage.createNewLocation(country, state, location,charge);
		String actualCreationalert = adminuserspage.verifyUserNameAlert();
		String expectedCreationalert = "Location Created Successfully";
		Assert.assertTrue(actualCreationalert.contains(expectedCreationalert));
	}
	
	
}
