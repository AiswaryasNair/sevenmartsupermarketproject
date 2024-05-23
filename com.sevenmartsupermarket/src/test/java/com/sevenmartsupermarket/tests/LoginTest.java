package com.sevenmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.pages.HomePage;
import com.sevenmartsupermarket.pages.LoginPage;
import com.sevenmartsupermarket.utilities.ScreenshotCapture;


public class LoginTest extends Base {
	LoginPage loginpage;//Aggregation
	HomePage homepage;
	@Test(groups = "smoke")
	public void verifyUserLogin() {
		
		loginpage=new LoginPage(driver);
		loginpage.login();
	}
	
	@Test(groups = "smoke",retryAnalyzer =com.sevenmartsupermarket.listeners.RetryAnalyzer.class)
	
	public void getUserName() {
		loginpage=new LoginPage(driver);
		homepage=new HomePage(driver);
		//ScreenshotCapture screenshotcap=new ScreenshotCapture();
		loginpage.login();
		String actualProfileName=homepage.getProfileName();
		String expectedProfileName="Admin";
		//capture screenshot
		//screenshotcap.takescreenshot(driver, "Aiswarya");
		Assert.assertEquals(actualProfileName, expectedProfileName);	
	}
	
	@Test
	
	public void invalidLogin() {
		loginpage=new LoginPage(driver);
		loginpage.login("ammu", "tyyy");
		String actualAlert=loginpage.getErrorMsg();
		String expectedAlert="Invalid Username/Password";
		Assert.assertTrue(actualAlert.contains(expectedAlert));
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
