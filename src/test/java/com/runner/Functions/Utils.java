package com.runner.Functions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

	public static WebDriver driver;
	public static WishListObject categoryLandingPage;
	public static HomePageObject homePage;
	public static LoginPageObject loginPage;


	public static final long WAIT =10;
	public static final int scrollSize = 16;
	public static ArrayList<String> wishListItems;
	public static List<String> allPriceDetails;
	public static String WATCHLIST_URL ="https://coinmarketcap.com/watchlist/";
	
	public static Random r = new Random();


	public static WebDriver getDriver() {

		if(driver == null) {
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("start-maximized");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-gpu");

			driver = new ChromeDriver(options);
			driver.manage().window().maximize();

			// driver = new FirefoxDriver();
		}
		return (WebDriver) driver;
	}


	//Method fot eait for visibility
	public static void waitForVisibility(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(getDriver(), WAIT);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	//Method to click element
	public static void click(WebElement ele) {
		waitForVisibility(ele);
		ele.click();
	}
	//Method to sendkeys to element
	public static void sendKeys(WebElement ele, String text) {
		waitForVisibility(ele);
		ele.sendKeys(text);
	}

	//Method for random generate
	public static int random(int min, int max) {
		int diff = (max - min);
		int result = r.nextInt(diff);
		return result = result + min;
		
		//return r.ints(1, min,max).findFirst().getAsInt();
	}

	public static void scrollByPixels() {
		JavascriptExecutor Scroll = (JavascriptExecutor) driver;
		Scroll.executeScript("window.scrollBy(0,450)", "");
	}
	
	
	public static void openNewTab() {
	    String osName = System.getProperty("os.name").toLowerCase();
	    System.out.println(osName);
	    if (osName.contains("win")) {
	        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
	    } else {
	        driver.findElement(By.cssSelector("body")).sendKeys(Keys.COMMAND + "t");
	    }
	}
	
	public static void scrollToElement(WebElement e) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
	}


	
	public static WishListObject getCategoryLandingPageObject() {

		if( categoryLandingPage == null) {
			categoryLandingPage = new WishListObject(driver);
		}
		return categoryLandingPage;
	}

	public static HomePageObject getHomePageObject() {
		if( homePage == null) {
			homePage = new HomePageObject(driver);
		}
		return homePage;
	}

	public static LoginPageObject getLoginPageObject() {
		if( loginPage == null) {
			loginPage = new LoginPageObject(driver);
		}
		return loginPage;
	}

	public static boolean isElementPresent(WebDriver driver, String xpath) {
		boolean exists = false;
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		try {
			int elementFound = driver.findElements(By.xpath(xpath)).size();
			if (elementFound > 0)
				exists = true;
		} catch (NoSuchElementException e) {
		}
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		return exists;
	}

}
