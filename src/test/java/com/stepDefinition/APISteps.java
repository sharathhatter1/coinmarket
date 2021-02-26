package com.stepDefinition;

import java.util.ArrayList;
import java.util.List;

import com.runner.Functions.APIActions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.cucumber.datatable.DataTable;

public class APISteps {
	
	APIActions apiActions = new APIActions();
	
    private List<Long> currencyIDs = new ArrayList<>();

	
	@Given("I retrieve the IDs of the follwing currencies")
	public void I_retrieve_the_IDs_of_the_follwing_currencies(List<String> currencies) {
	    currencyIDs = apiActions.retrieveTheCurrencyIDs(currencies);
	}
	
	@Then("I convert retrived IDs currencies to Bolivian Boliviano currency {string}")
	public void I_convert_retrived_IDs_currencies_to_Bolivian_Boliviano_currency(String convertCurrency)  {
	    apiActions.convertToBoliviano(currencyIDs, convertCurrency);

	}

	@Given("I retrieve Ethereum info of id {long}")
	public void i_retrieve_Ethereum_info_of_id(Long long1) {
	   apiActions.getInfoCallForCurrency(long1);
	}

	@Then("I verify the following details")
	public void i_verify_the_following_details(DataTable dataTable) {
		List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> columns : rows) {
            apiActions.verifyInfoCallDetail(columns.get(0), columns.get(1));
        }
		
	}
	
	@Then("I verify and print the currency which has mineable tag")
	public void i_verify_and_print_the_currency_which_has_mineable_tag() {
		apiActions.verifyMineable();
	}

	

	
}
