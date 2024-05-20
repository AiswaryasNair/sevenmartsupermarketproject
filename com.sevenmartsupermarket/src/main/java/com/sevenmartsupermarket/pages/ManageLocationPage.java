package com.sevenmartsupermarket.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageLocationPage {

	WebDriver driver;
	@FindBy(xpath = "(//p[text()='Manage Location'])[1]")
	private WebElement selectManageLoc;
	@FindBy(xpath = "(//i[@class='fas fa-edit'])[1]")
	private WebElement newBtn;
	@FindBy(xpath = "//select[@id='country_id']")
	private WebElement countryName;
	@FindBy(xpath = "//select[@id='st_id']")
	private WebElement stateName;
	@FindBy (xpath="//input[@id='location']")
	private WebElement Locations;
	@FindBy (xpath="//input[@id='delivery']")
	private WebElement deliveryCharge;
	@FindBy (xpath="//button[@name='create']")
	private WebElement saveBtn;
	public ManageLocationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void getManageLocation() {
		selectManageLoc.click();
	}
	public void clickBtnNew() {
		newBtn.click();
	}
	public void selectCountry(String Country) {
		countryName.sendKeys(Country);
	}
	public void selectState(String state) {
		stateName.sendKeys(state);
	}
	public void selectLocation(String location) {
		Locations.sendKeys(location);
	}
	public void deliveryCharges(int charge) {
	    deliveryCharge.sendKeys(String.valueOf(charge));
	}
	public void save() {
		saveBtn.click();
	}
	public void createNewLocation(String Country,String state,String loc,int charge) {
		getManageLocation();
		clickBtnNew();
		selectCountry(Country);
		selectState(state);
		selectLocation(loc);
		deliveryCharges(charge);
		save();
	}
}
