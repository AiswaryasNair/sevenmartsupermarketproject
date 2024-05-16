package com.sevenmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.pages.AdminUsersPage;
import com.sevenmartsupermarket.pages.HomePage;
import com.sevenmartsupermarket.pages.LoginPage;
import com.sevenmartsupermarket.utilities.GeneralUtility;

public class AdminUsersTest extends Base {
	LoginPage loginpage;
	HomePage homepage;
	AdminUsersPage adminuserspage;

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
		driver.quit();
	}
	
}
