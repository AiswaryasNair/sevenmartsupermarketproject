package com.sevenmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
	SoftAssert softassert = new SoftAssert();

	@Test(dataProvider = "usertypelist", dataProviderClass = DataProviders.class)
	public void selectadminUsers(String passWord, String userTypes) {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.getAdminUser();
		adminuserspage.clickBtnNew();
		String userName = GeneralUtility.getRandomFullName();
		adminuserspage.enterUserName(userName);
		adminuserspage.enterPassWord(passWord);
		adminuserspage.selectUserType(userTypes);
		adminuserspage.save();
		String actualCreationalert = adminuserspage.verifyUserNameAlert();
		String expectedCreationalert = "User Created Successfully";
		Assert.assertTrue(actualCreationalert.contains(expectedCreationalert));
	}

	@Test(dataProvider = "existingusertypelist", dataProviderClass = DataProviders.class)
	public void existingAdminUsers(String userName, String passWord, String userTypes) {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.getAdminUser();
		adminuserspage.clickBtnNew();
		adminuserspage.enterUserName(userName);
		adminuserspage.enterPassWord(passWord);
		adminuserspage.selectUserType(userTypes);
		adminuserspage.save();
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
		adminuserspage.clickBtnNew();
		String userName = GeneralUtility.getRandomFullName();
		adminuserspage.enterUserName(userName);
		String password = GeneralUtility.getRandomFirstName();
		adminuserspage.enterPassWord(password);
		adminuserspage.selectUserType("staff");
		adminuserspage.save();
		homepage.logOut();
		loginpage.login(userName, password);
		String actualProfileName=homepage.getProfileName();
		String expectedProfileName=userName;
		Assert.assertEquals(actualProfileName, expectedProfileName);
		
	}
	@Test( )
	public void verifySerachUser() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.getAdminUser();
		adminuserspage.clickSearchBtn();
		adminuserspage.searchUserName("Aisw");
		adminuserspage.searchUserTypes("staff");
		adminuserspage.clickSerchUser();
		String actualUserName=adminuserspage.verifyUserName();
		String expectedUserNmae="Aisw";
		Assert.assertEquals(actualUserName, expectedUserNmae);
	}
	
	@Test(dataProvider = "usertypelist", dataProviderClass = DataProviders.class)
	public void verifyUserDeletion(String passWord, String userTypes) {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		pageutility = new PageUtility(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.getAdminUser();
		adminuserspage.clickBtnNew();
		String userName = GeneralUtility.getRandomFullName();
		adminuserspage.enterUserName(userName);
		adminuserspage.enterPassWord(passWord);
		adminuserspage.selectUserType(userTypes);
		adminuserspage.save();
		//search user
		adminuserspage.clickSearchBtn();
		adminuserspage.searchUserName(userName);
		adminuserspage.searchUserTypes(userTypes);
		adminuserspage.clickSerchUser();
		adminuserspage.deleteBtn();
		pageutility.acceptalert();
		//search user after deletion
		adminuserspage.clickSearchBtn();
		adminuserspage.searchUserName(userName);
		adminuserspage.searchUserTypes(userTypes);
		adminuserspage.clickSerchUser();
		String actualResult=adminuserspage.verifyDeletionOfUser();
		String expectedResult=userName;
		Assert.assertFalse(actualResult.contains(expectedResult));
	}
	@Test(dataProvider = "usertypelist", dataProviderClass = DataProviders.class)
	public void verifyUserUpdate(String passWord, String userTypes) {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		pageutility = new PageUtility(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.getAdminUser();
		adminuserspage.clickBtnNew();
		String userName = GeneralUtility.getRandomFullName();
		adminuserspage.enterUserName(userName);
		adminuserspage.enterPassWord(passWord);
		adminuserspage.selectUserType(userTypes);
		adminuserspage.save();
		//search
		adminuserspage.clickSearchBtn();
		adminuserspage.searchUserName(userName);
		adminuserspage.searchUserTypes(userTypes);
		adminuserspage.clickSerchUser();
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
		adminuserspage.clickSearchBtn();
		adminuserspage.searchUserName(updatedUserName);
		adminuserspage.searchUserTypes("staff");
		adminuserspage.clickSerchUser();
		String actualUserName=adminuserspage.verifyUserName();
		String expectedUserNmae=updatedUserName;
		Assert.assertEquals(actualUserName, expectedUserNmae);
		
	}	
}
