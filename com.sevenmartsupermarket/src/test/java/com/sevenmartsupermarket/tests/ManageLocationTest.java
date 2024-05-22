package com.sevenmartsupermarket.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.pages.AdminUsersPage;
import com.sevenmartsupermarket.pages.LoginPage;
import com.sevenmartsupermarket.pages.ManageLocationPage;
import com.sevenmartsupermarket.utilities.ExcelReader;
import com.sevenmartsupermarket.utilities.GeneralUtility;
import com.sevenmartsupermarket.utilities.PageUtility;
public class ManageLocationTest extends Base {

	LoginPage loginpage;
	ManageLocationPage managelocationpage;
	AdminUsersPage adminuserspage;
	ExcelReader excelreader=new ExcelReader();
	PageUtility pageutility;
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
		String loc = location+GeneralUtility.getRandomFirstName();	
		int charge = Integer.parseInt(excelreader.getCellData(1, 3));
		//description=description+GeneralUtility.getRandomFirstName();//avoid duplication
		managelocationpage.createNewLocation(country, state, loc,charge);
		String actualCreationalert = adminuserspage.verifyUserNameAlert();
		String expectedCreationalert = "Location Created Successfully";
		Assert.assertTrue(actualCreationalert.contains(expectedCreationalert));
	}
	@Test
	public void searchLocation(){
		loginpage = new LoginPage(driver);
		managelocationpage = new ManageLocationPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		managelocationpage.searchLocations("United Kindom", "Cambridge", "XYZ");
		String actualCreationalert = managelocationpage.verifyLocSearch();
		String expectedCreationalert = "XYZ";
		Assert.assertTrue(actualCreationalert.contains(expectedCreationalert));
	}
	@Test
	public void deactivateLocation(){
		loginpage = new LoginPage(driver);
		managelocationpage = new ManageLocationPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		excelreader.setExcelFile("ManageLocationData","Locations");//workbooknmae,sheetname
		String country= excelreader.getCellData(1, 0);//cell value
		String state= excelreader.getCellData(1, 1);
		String location= excelreader.getCellData(1, 2);
		String loc = location+GeneralUtility.getRandomFirstName();
		int charge = Integer.parseInt(excelreader.getCellData(1, 3));
		managelocationpage.createNewLocation(country, state, loc,charge);
		managelocationpage.cancelBtn();
		managelocationpage.deactivateLoc(loc);
		managelocationpage.searchLocations(country,state, loc);
		String actualStatus=adminuserspage.verifyInActiveStatus();
		String expectedStatus="Inactive";
		Assert.assertEquals(actualStatus, expectedStatus);
	}
	@Test
	public void deleteLoc() {
		loginpage = new LoginPage(driver);
		managelocationpage = new ManageLocationPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		pageutility = new PageUtility(driver);
		loginpage.login();
		managelocationpage.searchLocations("United Kindom", "Cambridge", "XYZ");
		managelocationpage.deleteBtn();
		pageutility.acceptalert();
		String actualCreationalert = adminuserspage.verifyUserNameAlert();
		String expectedCreationalert = "Location Deleted Successfully";
		Assert.assertTrue(actualCreationalert.contains(expectedCreationalert));
	}
	
}
