package com.sevenmartsupermarket.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenmartsupermarket.utilities.GeneralUtility;
import com.sevenmartsupermarket.utilities.PageUtility;

public class AdminUsersPage {

	WebDriver driver;
	GeneralUtility generalutility;
	PageUtility pageutility;
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
	private WebElement searchBtn;
	@FindBy(xpath = "(//input[@class='form-control'])[1]")
	private WebElement userNameSearch;
	@FindBy(xpath = "(//select[@class='form-control'])[1]")
	private WebElement userTypeSearch;

	@FindBy(xpath = "//button[@name='Search']")
	private WebElement searchUserBtn;
	@FindBy(xpath = "//table//tbody//tr[1]//td[1]")
	private WebElement getUserName;
	@FindBy(xpath = "//i[@class='fas fa-trash-alt']")
	private WebElement deletebtn;
	@FindBy(xpath = "//a[@class='btn btn-sm btn btn-primary btncss']")
	private WebElement editBtn;
	@FindBy(xpath = "//button[@name='Update']")
	private WebElement updateBtn;

	@FindBy(xpath = "//tbody//tr//td[1]")
	List<WebElement> listAllUsers;
	@FindBy(xpath = "//span[@class='badge bg-warning']")
	private WebElement status;
	@FindBy(xpath = "//p[text()='Dashboard']")
	private WebElement clickDashboard;

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

	public void createNewUser(String UserName, String PassWord, String userTypes) {
		clickBtnNew();
		enterUserName(UserName);
		enterPassWord(PassWord);
		selectUserType(userTypes);
		save();
	}

//New user creation alert
	public String verifyUserNameAlert() {

		String AlertText = userCreationAlert.getText();
		return AlertText;
	}

	public String verifyExistingUserNameAlert() {

		String existingAlertText = existingUserAlert.getText();
		return existingAlertText;
	}

	// search user
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

	// enter user detailes and click search button
	public void clickSearchUser() {
		searchUserBtn.click();
	}

	public void searchUser(String userName, String userTypes) {
		clickSearchBtn();
		searchUserName(userName);
		searchUserTypes(userTypes);
		clickSearchUser();
	}

	// After search verify the user is displayed or not
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

	public void deactivateusers(String userName) {
		pageutility = new PageUtility(driver);
		generalutility = new GeneralUtility();
		List<String> namelist = new ArrayList<String>();
		int a = listAllUsers.size();
		namelist = generalutility.gettextOfeleemnts(listAllUsers);
		int index = 0;
		for (index = 0; index < namelist.size(); index++) {
			if (namelist.get(index).equals(userName)) {
				index++;
				break;
			}
		}
		WebElement deactivateButton = driver.findElement(By.xpath("//table//tr[" + index + "]//td[5]//a[1]"));
		pageutility.ScrollAndClick(deactivateButton);
	}

	public String verifyInActiveStatus() {
		return status.getText();
	}

	public void SelectDashboard() {
		clickDashboard.click();
	}

	public void listNamesOfUser() {
		for (WebElement listUserNames : listAllUsers) {
			System.out.println(listUserNames.getText());
		}
	}
}
