package com.sevenmartsupermarket.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.apache.bcel.classfile.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.sevenmartsupermarket.constants.Constants;
import com.sevenmartsupermarket.utilities.ScreenshotCapture;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public WebDriver driver;
	Properties properties = new Properties();
	ScreenshotCapture screenshotcapture=new ScreenshotCapture();
	
	/** Base Constructor **/
	public Base() {
		try {
			FileInputStream fs = new FileInputStream(Constants.CONFIG_FILE_PATH);
			properties.load(fs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** initializing browser **/
	public void initialize(String browzer, String url) {
		if (browzer.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browzer.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browzer.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));
	}
	@Parameters("browzer")
	@BeforeMethod(enabled = false)

	public void launchBrowser(String browzer) {

		String url=properties.getProperty("url");
		initialize(browzer,url);
	}
	@BeforeMethod(enabled = true)

	public void launchBrowser() {

		String browzer=properties.getProperty("browzer");
		String url=properties.getProperty("url");
		initialize(browzer,url);
	}
	@AfterMethod
	public void terminateBrowzer(ITestResult itestresult) {
		if(itestresult.getStatus()==ITestResult.FAILURE) {//listener itestresult
		screenshotcapture.takescreenshot(driver,itestresult.getName());//get the current testcase name
		}
		
	}

}
