package com.stepDefinition;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;

import com.runner.Functions.WishListObject;
import com.runner.Functions.Utils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class CategoryLandingPageSteps {
	
	public WebDriver driver;
	public WishListObject categoryLandingPageObject;

	public CategoryLandingPageSteps(){
		driver = (WebDriver) Utils.getDriver();
		categoryLandingPageObject = Utils.getCategoryLandingPageObject();
	}
	
	@Given("I clear watchlist if any and navigates to homepage")
	public void i_clear_watchlist_if_any() {
		categoryLandingPageObject.acceptAlert();
	    categoryLandingPageObject.removeWatchList();
	    
	}
	
	@Then("verify all currencies are exists in watchlist and Logout")
	public void verify_all_currencies_are_exists_in_watchlist() {
	   categoryLandingPageObject.compareCurrency();
	   categoryLandingPageObject.logout();
	}

    @AfterClass
    public void tearDown() throws InterruptedException {
    	Thread.sleep(1000);
    	driver.quit();
    }



}
