package com.sevenmartsupermarket.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PushNotificationsPage {

	WebDriver driver;

	@FindBy(xpath = "//input[@id='title']")
	private WebElement titleField;
	@FindBy(xpath = "//input[@id='description']")
	private WebElement discriptionField;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement sendbtn;
	@FindBy(xpath = "//p[text()='Push Notifications']")
	private WebElement getPushNotification;

	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement notificationAlert;

	@FindBy(xpath = "//a[@class='btn btn-default btn-fix']")
	private WebElement resetbtn;

	public PushNotificationsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterTitle(String title) {
		titleField.sendKeys(title);
	}

	public void enterDiscription(String discription) {
		discriptionField.sendKeys(discription);
	}

	public void sendbutton() {
		sendbtn.click();
	}

	public void sendNotification(String title, String discription) {
		enterTitle(title);
		enterDiscription(discription);
		sendbutton();
	}

	public void selectPushNotification() {
		getPushNotification.click();
	}

	public boolean verifyAlertMsg() {
		return notificationAlert.isDisplayed();
	}
	public String verifyAlertMsgs() {
		return notificationAlert.getText();
	}

	public void selectResetBtn() {
		resetbtn.click();
		String value = titleField.getAttribute("value");

		if (value == null || value.trim().isEmpty()) {
			System.out.println("Input field is blank or contains only whitespace characters.");
		}
	}
}
