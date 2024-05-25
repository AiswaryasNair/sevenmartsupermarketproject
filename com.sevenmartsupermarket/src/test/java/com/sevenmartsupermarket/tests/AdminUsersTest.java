package com.sevenmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.sevenmartsupermarket.DataProviders.DataProviders;
import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.pages.AdminUsersPage;
import com.sevenmartsupermarket.pages.HomePage;
import com.sevenmartsupermarket.pages.LoginPage;
import com.sevenmartsupermarket.utilities.GeneralUtility;
import com.sevenmartsupermarket.utilities.PageUtility;

public class AdminUsersTest extends Base {
	LoginPage loginpage;
	HomePage homepage;
	AdminUsersPage adminuserspage;
	PageUtility pageutility;
	//SoftAssert softassert = new SoftAssert();
	@Test(dataProvider = "usertypelist", dataProviderClass = DataProviders.class,groups = "regression")
	public void selectadminUsers(String passWord, String userTypes) {
		loginpage = new LoginPage(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.getAdminUser();
		String userName = GeneralUtility.getRandomFullName();
		adminuserspage.createNewUser(userName, passWord, userTypes);
		String actualCreationalert = adminuserspage.verifyUserNameAlert();
		String expectedCreationalert = "User Created Successfully";
		Assert.assertTrue(actualCreationalert.contains(expectedCreationalert));
	}

	@Test(dataProvider = "existingusertypelist", dataProviderClass = DataProviders.class,groups={"regression","smoke"})
	public void existingAdminUsers(String userName, String passWord, String userTypes) {
		loginpage = new LoginPage(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.getAdminUser();
		adminuserspage.createNewUser(userName, passWord, userTypes);
		String actualCreationalert = adminuserspage.verifyExistingUserNameAlert();
		String expectedCreationalert = "Username already exists.";
		Assert.assertTrue(actualCreationalert.contains(expectedCreationalert));
	}
	
	@Test( )
	public void verifynewUserName() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.getAdminUser();
		String userName = GeneralUtility.getRandomFullName();
		String passWord = GeneralUtility.getRandomFirstName();
		String userTypes="Staff";
		adminuserspage.createNewUser(userName, passWord, userTypes);
		homepage.logOut();
		loginpage.login(userName, passWord);
		String actualProfileName=homepage.getProfileName();
		String expectedProfileName=userName;
		Assert.assertEquals(actualProfileName, expectedProfileName);
		
	}
	@Test( )
	public void verifySerachUser() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.getAdminUser();
		adminuserspage.searchUser("Aisw", "staff");
		String actualUserName=adminuserspage.verifyUserName();
		String expectedUserNmae="Aisw";
		Assert.assertEquals(actualUserName, expectedUserNmae);
	}
	
	@Test(dataProvider = "usertypelist", dataProviderClass = DataProviders.class)
	public void verifyUserDeletion(String passWord, String userTypes) {
		loginpage = new LoginPage(driver);
		pageutility = new PageUtility(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.getAdminUser();
		String userName = GeneralUtility.getRandomFullName();
		adminuserspage.createNewUser(userName, passWord, userTypes);
		//search user
		adminuserspage.searchUser(userName,userTypes);
		adminuserspage.deleteBtn();
		pageutility.acceptalert();
		//search user after deletion
		adminuserspage.searchUser(userName,userTypes);
		String actualResult=adminuserspage.verifyDeletionOfUser();
		String expectedResult=userName;
		Assert.assertFalse(actualResult.contains(expectedResult));
	}
	
	@Test(dataProvider = "usertypelist", dataProviderClass = DataProviders.class)
	public void verifyUserUpdate(String passWord, String userTypes) {
		loginpage = new LoginPage(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.getAdminUser();
		String userName = GeneralUtility.getRandomFullName();
		adminuserspage.createNewUser(userName, passWord, userTypes);
		//search
		adminuserspage.searchUser(userName,userTypes);
		//update
		adminuserspage.clickEditBtn();
		adminuserspage.clear();
		String updatedUserName = GeneralUtility.getRandomFullName();
		adminuserspage.enterUserName(updatedUserName);
		adminuserspage.enterPassWord("1234");
		adminuserspage.clickUpdateBtn();
		String actualCreationalert = adminuserspage.verifyUserNameAlert();
		String expectedCreationalert = "User Updated Successfully";
		Assert.assertTrue(actualCreationalert.contains(expectedCreationalert));
		//verify username
		adminuserspage.searchUser(updatedUserName,userTypes);
		String actualUserName=adminuserspage.verifyUserName();
		String expectedUserNmae=updatedUserName;
		Assert.assertEquals(actualUserName, expectedUserNmae);
		
	}	
	@Test(dataProvider = "usertypelist", dataProviderClass = DataProviders.class)
	public void verifyDeactivationFunctionality(String passWord, String userTypes) {
		loginpage = new LoginPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		adminuserspage.getAdminUser();
		String userName = GeneralUtility.getRandomFullName();
		adminuserspage.createNewUser(userName, passWord, userTypes);
		adminuserspage.deactivateusers(userName);
		adminuserspage.clickSearchBtn();
		adminuserspage.searchUserName(userName);
		adminuserspage.searchUserTypes("staff");
		adminuserspage.clickSearchUser();
		String actualStatus=adminuserspage.verifyInActiveStatus();
		String expectedStatus="Inactive";
		Assert.assertEquals(actualStatus, expectedStatus);
	}
	@Test
	public void listUserName() {
		loginpage = new LoginPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		loginpage.login();
		adminuserspage.getAdminUser();
		adminuserspage.listNamesOfUser();
	}
	
	//Read data from excel using dataprovider
	@Test(dataProvider = "excelread", dataProviderClass = DataProviders.class)
	public void excelData(String passWord, String userTypes) {
		loginpage = new LoginPage(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.getAdminUser();
		String userName = GeneralUtility.getRandomFullName();
		adminuserspage.createNewUser(userName, passWord, userTypes);
		String actualCreationalert = adminuserspage.verifyUserNameAlert();
		String expectedCreationalert = "User Created Successfully";
		Assert.assertTrue(actualCreationalert.contains(expectedCreationalert));
	}
}
