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
	@FindBy(xpath = "//i[@class=' fa fa-search']")
	private WebElement searchBtn ;
	@FindBy(xpath = "(//input[@class='form-control'])[1]")
	private WebElement userNameSearch ;
	@FindBy(xpath = "(//select[@class='form-control'])[1]")
	private WebElement userTypeSearch ;

	@FindBy(xpath = "//button[@name='Search']")
	private WebElement searchUserBtn ;
	@FindBy(xpath = "//table//tbody//tr[1]//td[1]")
	private WebElement getUserName ;
	@FindBy(xpath = "//i[@class='fas fa-trash-alt']")
	private WebElement deletebtn ;
	@FindBy(xpath = "//a[@class='btn btn-sm btn btn-primary btncss']")
	private WebElement editBtn ;
	@FindBy(xpath = "//button[@name='Update']")
	private WebElement updateBtn ;
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
	public void clickSearchBtn() {
		searchBtn.click();
	}
	
	public String searchUserName(String userSearch) {
		userNameSearch.sendKeys(userSearch);
		return userSearch;
	}
	public String searchUserTypes(String userTypeSearches) {
		 userTypeSearch.sendKeys(userTypeSearches);
		 return userTypeSearches;
	}
	public void clickSerchUser() {
		searchUserBtn.click();
	}
	public String verifyUserName() {
		return getUserName.getText();
	}
	
	public void deleteBtn() {
		deletebtn.click();
	}
	public String verifyDeletionOfUser() {
		return getUserName.getText();
	}
	public void clickEditBtn() {
		editBtn.click();
	}
	public void clickUpdateBtn() {
		updateBtn.click();
	}
	public void clear() {
		userName.clear();
	}
}
