package com.sevenmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.pages.HomePage;
import com.sevenmartsupermarket.pages.LoginPage;


public class LoginTest extends Base {
	LoginPage loginpage;//Aggregation
	HomePage homepage;
	@Test
	public void verifyUserLogin() {
		
		loginpage=new LoginPage(driver);
		loginpage.login();
	}
	
	@Test
	
	public void getUserName() {
		loginpage=new LoginPage(driver);
		homepage=new HomePage(driver);
		loginpage.login();
		String actualProfileName=homepage.getProfileName();
		String expectedProfileName="Admin";
		Assert.assertEquals(actualProfileName, expectedProfileName);	
	}
	
	@Test
	
	public void invalidLogin() {
		loginpage=new LoginPage(driver);
		loginpage.login("ammu", "tyyy");
		String actualAlert=loginpage.getErrorMsg();
		String expectedAlert="Invalid Username/Password";
		Assert.assertEquals(actualAlert, expectedAlert);
	}
	
	@Test
	public void clickRememberBtn() throws InterruptedException {
		
		loginpage=new LoginPage(driver);
		loginpage.clickRememberButton();
		//Assert.assertTrue(loginpage.verifyCheckBoxSelection());
		loginpage.login();
	}
	
	@Test
	public void verifyLogout() {
		
		loginpage=new LoginPage(driver);
		homepage=new HomePage(driver);
		loginpage.login();
		homepage.logOut();
		String actual=loginpage.getLoginPageTitle();
		String expected="7rmart supermarket";
		Assert.assertEquals(actual,expected);
	}
	
}
