package com.sevenmartsupermarket.pages;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenmartsupermarket.constants.Constants;

public class LoginPage {
	//driver
	WebDriver driver;
	//aggregations
	Properties properties = new Properties();
	//find by xpath
	@FindBy(xpath ="//input[@name='username']" )
	private WebElement userNameField;//encapsulation
	@FindBy(xpath ="//input[@name='password']" )
	private WebElement passwordField;
	@FindBy(xpath ="//button[@class='btn btn-dark btn-block']" )
	private WebElement login;
	@FindBy(xpath ="//div[contains(@class, 'alert') and contains(@class, 'alert-danger') and contains(@class, 'alert-dismissible')][contains(text(), 'Invalid Username/Password')]" )
	private WebElement alertmsg;
	
	@FindBy(xpath ="//label[@for='remember']")
	private WebElement rememberBtn;
	
	@FindBy(xpath ="//input[@id='remember']")
	private WebElement selectrembtn;
	@FindBy(xpath ="//b[text()='7rmart supermarket']")
	private WebElement title;
	//constructors
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		try {
			FileInputStream fs = new FileInputStream(Constants.CONFIG_FILE_PATH);
			properties.load(fs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//methods
	public void enterUserName(String userName) {
		userNameField.sendKeys(userName);
	}
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}
	public void loginBtn() {
		login.click();
	}
	public void login() {
		String userName=properties.getProperty("userName");
		String password=properties.getProperty("password");
		enterUserName(userName);
		enterPassword(password);
		loginBtn();
	}
	public void login(String userName,String password) {
		enterUserName(userName);
		enterPassword(password);
		loginBtn();
		
	}
	public String getErrorMsg() {
		return alertmsg.getText();
		
	}
	public void clickRememberButton() {
		rememberBtn.click();
	}
	public boolean verifyCheckBoxSelection() throws InterruptedException {
		Thread.sleep(3000);
		return rememberBtn.isSelected();
		
	}
	public String getLoginPageTitle() {
		return title.getText();
}
}
