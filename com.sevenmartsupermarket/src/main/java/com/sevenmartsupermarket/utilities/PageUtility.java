package com.sevenmartsupermarket.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PageUtility {

	WebDriver driver;
	JavascriptExecutor js;

	public PageUtility(WebDriver driver) {
		this.driver = driver;
		// PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
	}

	public void select_ByIndex(WebElement element, int index) {

		Select select = new Select(element);
		select.deselectByIndex(index);

	}

	public void select_ByValue(WebElement element, String value) {

		Select select = new Select(element);
		select.deselectByValue(value);

	}

	public void acceptalert() {
		driver.switchTo().alert().accept();
	}

	public void dismisstalert() {
		driver.switchTo().alert().dismiss();
	}

	public void ScrollAndClick(WebElement element) {

		int y = 0;
		while (clickStatus(element)) {
			js.executeScript("window.scrollBy(0," + y + ")");
			y = y + 5;
		}
	}

	public boolean clickStatus(WebElement element) {
		try {
			element.click();
			return false;
		} catch (Exception e) {
			return true;
		}
	}
	public void backButton() {
		driver.navigate().back();
	}
}
