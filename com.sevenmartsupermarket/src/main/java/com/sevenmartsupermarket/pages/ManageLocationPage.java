package com.sevenmartsupermarket.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.sevenmartsupermarket.utilities.GeneralUtility;
import com.sevenmartsupermarket.utilities.PageUtility;

public class ManageLocationPage {

	WebDriver driver;
	GeneralUtility generalutility;
	PageUtility pageutility;
	@FindBy(xpath = "(//p[text()='Manage Location'])[1]")
	private WebElement selectManageLoc;
	@FindBy(xpath = "(//i[@class='fas fa-edit'])[1]")
	private WebElement newBtn;
	@FindBy(xpath = "//select[@id='country_id']")
	private WebElement countryName;
	@FindBy(xpath = "//select[@id='st_id']")
	private WebElement stateName;
	@FindBy(xpath = "//input[@id='location']")
	private WebElement locations;
	@FindBy(xpath = "//input[@id='delivery']")
	private WebElement deliveryCharge;
	@FindBy(xpath = "//button[@name='create']")
	private WebElement saveBtn;
	@FindBy(xpath = "//i[@class=' fa fa-search']")
	private WebElement searchBtn;
	@FindBy(xpath = "//button[@name='Search']")
	private WebElement searchLocBtn;
	@FindBy(xpath = "//select[@id='st_id']")
	private WebElement selectStateDropDown;
	@FindBy(xpath = "//tbody//tr[1]//td[1]")
	private WebElement tableData;
	@FindBy(xpath = "//a[text()='Cancel']")
	private WebElement cancel;
	@FindBy(xpath = "//tbody//tr//td[1]")
	List<WebElement> listAllLoc;
	@FindBy(xpath = "//i[@class='fas fa-trash-alt']")
	private WebElement delete;

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
		locations.sendKeys(location);
	}

	public void deliveryCharges(int charge) {
		deliveryCharge.sendKeys(String.valueOf(charge));
	}

	public void save() {
		saveBtn.click();
	}

	public void createNewLocation(String Country, String state, String loc, int charge) {
		getManageLocation();
		clickBtnNew();
		selectCountry(Country);
		selectState(state);
		selectLocation(loc);
		deliveryCharges(charge);
		save();
	}

	public void search() {
		searchBtn.click();
	}

	public void clickSearchLoc() {
		searchLocBtn.click();
	}

	public void clickState(String state) {
		selectStateDropDown.click();
		Select dropdown = new Select(selectStateDropDown);
		dropdown.selectByVisibleText(state);
	}

	public void searchLocations(String Country, String state, String loc) {
		getManageLocation();
		search();
		selectCountry(Country);
		clickState(state);
		selectLocation(loc);
		clickSearchLoc();
	}

	public String verifyLocSearch() {
		return tableData.getText();
	}

	public void deactivateLoc(String loc) {

		pageutility = new PageUtility(driver);
		generalutility = new GeneralUtility();
		List<String> namelist = new ArrayList<String>();
		int a = listAllLoc.size();
		namelist = generalutility.gettextOfeleemnts(listAllLoc);
		int index = 0;
		for (index = 0; index < namelist.size(); index++) {
			if (namelist.get(index).equals(loc)) {
				index++;
				break;
			}
		}
		WebElement deactivateButton = driver.findElement(By.xpath("//table//tr[" + index + "]//td[5]//a[1]"));
		pageutility.ScrollAndClick(deactivateButton);
	}

	public void cancelBtn() {
		cancel.click();

	}
	public void deleteBtn() {
		delete.click();
	}
}
