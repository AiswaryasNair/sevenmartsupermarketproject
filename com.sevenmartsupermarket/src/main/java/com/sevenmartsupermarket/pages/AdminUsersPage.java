package com.sevenmartsupermarket.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminUsersPage {

	WebDriver driver;

	@FindBy(xpath = "(//i[@class='fas fa-arrow-circle-right'])[2]")
	private WebElement adminUsesMenu;
	@FindBy(xpath = "(//i[@class='fas fa-edit'])[1]")
	private WebElement newBtn;
	@FindBy(xpath = "//input[@id='username']")
	private WebElement userName;
	@FindBy(xpath = "//input[@id='password']")
	private WebElement passWord;
	@FindBy(xpath = "//button[@name='Create']")
	private WebElement saveBtn;
	@FindBy(xpath = "//select[@name='user_type']")
	private WebElement userType;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement userCreationAlert;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement existingUserAlert;

	public AdminUsersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void getAdminUser() {
		adminUsesMenu.click();
	}

	public void clickBtnNew() {
		newBtn.click();
	}

	public void enterUserName(String username) {
		userName.sendKeys(username);
	}

	public void enterPassWord(String password) {
		passWord.sendKeys(password);
	}

	public void save() {
		saveBtn.click();
	}

	public void selectUserType(String userTypes) {
		userType.sendKeys(userTypes);
	}

	public String verifyUserNameAlert() {

		String AlertText = userCreationAlert.getText();
		return AlertText;
	}

	public String verifyExistingUserNameAlert() {

		String existingAlertText = existingUserAlert.getText();
		return existingAlertText;
	}
}
