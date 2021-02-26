package com.runner.Functions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class WishListObject {

	WebDriver driver;

	//Locators
	//element for the watchlist
	@FindBy(xpath="//span[@class='sc-7f3up6-1 dtMKRz is-starred']")
	private List<WebElement> watchList;

	//element for homepage
	@FindBy(xpath="//a[@class='cmc-logo-link cmc-link']")
	private WebElement homePageLink;

	//element to accept alert  watchlist
	@FindBy(xpath="//button[@class='sc-1ejyco6-0 czBWYA']")
	private WebElement checkItOut;

	//element to add coins button
	@FindBy(xpath="//div[contains(text(),'Add Coins')]")
	private WebElement addCoins;

	@FindBy(xpath="//tbody/tr/td[3]/a/div/div/p")
	private List<WebElement> currencyText;

	//element for user icon
	@FindBy(xpath="//button[@class='sc-10o4ja6-0 iwazsF cmc-profile-popover__trigger']")
	private WebElement userButton;

	//element for logout
	@FindBy(xpath="//span[contains(text(),'Log out')]")
	private WebElement logout;


	public WishListObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		driver.manage ().timeouts ().implicitlyWait ( 100, TimeUnit.SECONDS );
	}


	//accept the alert
	public void acceptAlert() {
		if(checkItOut.isDisplayed()) {
			Utils.click(checkItOut);
		}

	}

	public HomePageObject removeWatchList1() {
		if(checkItOut.isDisplayed()) {
			Utils.click(checkItOut);
		}
		else if(addCoins.isDisplayed()) {
			Utils.click(homePageLink);
		}
		else if(watchList.size()>0) {
			for(WebElement e:watchList) {
				e.click();
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Utils.click(homePageLink);
		}

		return new HomePageObject(driver);

	}

	//method to remove the watchlist if any
	public HomePageObject removeWatchList() {
		System.out.println("watch list size ->" + watchList.size());

		if(watchList.size()>0) {
			for(WebElement e:watchList) {
				e.click();
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Utils.click(homePageLink);
		}

		return new HomePageObject(driver);

	}

	//method to compare the currencies added
	public void compareCurrency() {
		ArrayList<String> al = new ArrayList<String>();
		System.out.println("inside watchlist");

		if(currencyText.size()>0) {
			for(WebElement e:currencyText) {
				al.add(e.getText());
			}
		}
		al.stream().forEach(a->System.out.println("collections items in fav ->"+a));
		boolean bool = Utils.wishListItems.equals(al);
		if(bool) {
			System.out.println("Watchlist currencies verified");
		}
		else
			System.out.println("currencies in watchlist not matching");
	}


	//logout
	public void logout() {
		Utils.click(userButton);
		Utils.click(logout);
		
	}

}