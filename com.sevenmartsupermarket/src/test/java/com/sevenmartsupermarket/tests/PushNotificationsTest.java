package com.sevenmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.pages.HomePage;
import com.sevenmartsupermarket.pages.LoginPage;
import com.sevenmartsupermarket.pages.PushNotificationsPage;
import com.sevenmartsupermarket.utilities.ExcelReader;
import com.sevenmartsupermarket.utilities.GeneralUtility;

public class PushNotificationsTest extends Base {

	PushNotificationsPage pushnotificationspage;
	LoginPage loginpage;
	HomePage homepage;
	ExcelReader excelreader = new ExcelReader();

	//Verify alert message and get random full name
	@Test
	public void sendNotification() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.login();
		pushnotificationspage = new PushNotificationsPage(driver);
		pushnotificationspage.selectPushNotification();
		pushnotificationspage.sendNotification("Test", "discription");
		Assert.assertTrue(pushnotificationspage.verifyAlertMsg(), "Message is displayed");
		System.out.println(GeneralUtility.getRandomFullName());//generate fullname
	}
	@Test
	public void sendNotificationExcel() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.login();
		pushnotificationspage = new PushNotificationsPage(driver);
		pushnotificationspage.selectPushNotification();
		excelreader.setExcelFile("pushNotificationData","Notifications");//workbooknmae,sheetname
		String title= excelreader.getCellData(1, 0);//cell value
		String description= excelreader.getCellData(1, 1);
		pushnotificationspage.sendNotification(title, description);
		String actualAlertmsg=pushnotificationspage.verifyAlertMsgs();
		String ExpectedAlertmsg="Message send successfully";
		Assert.assertTrue(actualAlertmsg.contains(ExpectedAlertmsg));
		}
	@Test

	public void verifyReset() {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.login();
		pushnotificationspage = new PushNotificationsPage(driver);
		pushnotificationspage.selectPushNotification();
		pushnotificationspage.sendNotification("Test", "");
		pushnotificationspage.selectResetBtn();
	}
	
	
}
