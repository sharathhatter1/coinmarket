package com.stepDefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.runner.Functions.HomePageObject;
import com.runner.Functions.LoginPageObject;
import com.runner.Functions.Utils;

import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class HomePageSteps {

	public static WebDriver driver;
	public static HomePageObject homePage;
	public static LoginPageObject loginPage;
	SoftAssert softAssert = new SoftAssert();

	public HomePageSteps() {
		driver = (WebDriver) Utils.getDriver();
		homePage = Utils.getHomePageObject();
	}
	
    List<String> dataPrice = new ArrayList<>();


	@Given("I open on coinmarket homepage")
	public void i_open_on_coinmarket_homepage() {
		homePage.openHomePage();
	}

	@Given("User on home page")
	public void user_on_home_page() {
		homePage.getHomePageTitle();
	}
	
	@Given("I go to Watchlist page")
	public void i_go_to_Watchlist_page() {
	    homePage.clickWatchLIst();
	}

	@Given("Verifies the home page and verify page title")
	public void verifies_the_home_page_and_verify_page_title() {
		homePage.getHomePageTitle();
	}

	@When("User check the number of rows")
	public void user_check_the_number_of_rows() {
		int size = homePage.verifyNumberOfRows(); 
		System.out.println("Number of rows ->" +size);
		
	}

	@Then("Row count should be {int}")
	public void row_count_should_be(Integer expectedRowCount) {
		Assert.assertTrue(homePage.verifyNumberOfRows() == expectedRowCount);
	}
	
	@When("I will select few random currencies between {int} and {int}")
	public void i_will_select_few_random_currencies_between_and(Integer min, Integer max) {
	    // Write code here that turns the phrase above into concrete actions
		Utils.scrollByPixels();
		homePage.addWatchList(min, max);
		homePage.scrollToWatchList();
	    
	}

	@Then("I navigate to watchlist page")
	public void i_navigate_to_watchlist_page() {
	    homePage.openTab();
	}
	
	
	@Given("the user select Ranking from Cryptocurrencies dropdown")
	public void the_user_select_Ranking_from_Cryptocurrencies_dropdown() {
	    homePage.clickRanking();
	}

	@Given("record the data in the page")
	public void record_the_data_in_the_page() {
	    dataPrice = homePage.pricedata();
	}

	@When("the user uses the filter price range between {string} and {string}")
	public void the_user_uses_the_filter_combination_of_mineable_and_price_range_between_and(String from, String to) {
	    homePage.tapFilter();
	    homePage.selectPrice(from, to);
	    homePage.applyFilter();
	}

	@Then("verify that page should display currencies with the price between {double} and {double}")
	public void verify_that_page_should_display_mineable_and_currencies_with_the_price_between_and(Double int1, Double int2) {
		List<String> filteredPriceDetails = homePage.pricedata();
        homePage.verifyFilterData(filteredPriceDetails, int1, int2);
	}
	
	@After
    public void CloseBrowser(Scenario scenario){
        driver.close();
    }
	
	
}



