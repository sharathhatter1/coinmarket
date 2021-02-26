package com.runner.Functions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hamcrest.core.IsNull;
import org.testng.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.runner.ConfigReader;

import apiConfigs.ApiPath;
import apiConfigs.HeaderConfigs;
import apipojos.CurrencyDataPojo;
import apipojos.CurrencyPojo;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class APIActions {

	private ConfigReader configReader;
	Properties prop;
	public String serverURL;
	public String apiKey;
	public Response response;

	HeaderConfigs headerConfigs = new HeaderConfigs();
	ObjectMapper mapper =new ObjectMapper();

	public void getProperty() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
		serverURL = prop.getProperty("baseURI");
		apiKey = prop.getProperty("apiKey");
	}

	//Method is to make a get map call
	public void getmap(){
		getProperty();
		RestAssured.baseURI = serverURL; 
		System.out.println(serverURL);
		System.out.println(apiKey);
		response = RestAssured.given().headers(headerConfigs.headerWithToken()).when().log().all().get(ApiPath.getCurrency);
		System.out.println(response.asString());
	}

	//This method is to retrieve the currency id from the get Map call response
	public List<Long> retrieveTheCurrencyIDs(List<String> currencies) {
		List<Long> currencyID = new ArrayList<>();
		getmap();
		List<CurrencyPojo> currencyPojos = response.jsonPath().getList("data", CurrencyPojo.class);
		currencyPojos.forEach(e -> {
			if(currencies.contains(e.getSymbol())){
				currencyID.add(e.getId());
			}
		});

		currencyID.stream().forEach(a->System.out.println(a));
		return currencyID;
	}

	//This private method is to make a get currency convert call
	private void getCurrencyConvertCall(long id, String currencyConvert){
		getProperty();
		RestAssured.baseURI = serverURL; 
		response = RestAssured.given().headers(headerConfigs.headerWithToken())
				.queryParam("amount", 10).queryParam("id", id).queryParam("convert", currencyConvert)
				.when().get(ApiPath.priceConversion);
		Assert.assertEquals(200,response.getStatusCode());
	}

	//This method is to convert all the currency IDs to another currency
	public void convertToBoliviano(List<Long> currencyIDs, String convertCurrency){
		currencyIDs.forEach(id -> {
			getCurrencyConvertCall(id, convertCurrency);
		});

	}

	//This method is to get info call for a particular currency
	public void getInfoCallForCurrency(long id){
		getProperty();
		RestAssured.baseURI = serverURL; 
		response = RestAssured.given().headers(headerConfigs.headerWithToken()).queryParam("id", id)
				.when().get(ApiPath.currencyInfo);
		Assert.assertEquals(200,response.getStatusCode());
		
	}
	
	
	public void verifyMineable(){
		for(int i=1;i<=10;i++) {
			getInfoCallForCurrency(i);
			Map map = response.jsonPath().getMap("data");
			String json;
			try {
				json = mapper.writeValueAsString(map.get(i+""));
				CurrencyDataPojo dataCurrency = mapper.readValue(json, CurrencyDataPojo.class);
				if(dataCurrency.getTags() != null) {
					for(String tag : dataCurrency.getTags()) {
						if("mineable".equals(tag)) {
							System.out.println(dataCurrency.getName());
							//list.add(dataCurrency);
							break;
						}
					}
					
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}

	//This method is to verify all the values in the get info call
	public void verifyInfoCallDetail(String key, String expectedValue){
		String logo = "data.1027.logo";
		String technicalDoc = "data.1027.urls.technical_doc[0]";
		String symbol = "data.1027.symbol";
		String dateAdded = "data.1027.date_added";
		String platform = "data.1027.platform";
		String tags = "data.1027.tags[0]";

		String actualValue = null;
		JsonPath jsonPath = new JsonPath(response.body().asString());
		switch(key){
		case "logo":{
			actualValue = jsonPath.get(logo);
			break;
		}
		case "technical_doc":{
			actualValue = jsonPath.get(technicalDoc);
			break;
		}
		case "symbol":{
			actualValue = jsonPath.get(symbol);
			break;
		}
		case "date_added":{
			actualValue = jsonPath.get(dateAdded);
			break;
		}
		case "platform":{
			actualValue = jsonPath.get(platform);
			assertThat(actualValue, is(IsNull.nullValue()));
			break;
		}
		case "tags":{
			actualValue = jsonPath.get(tags);
			break;
		}
		default:{
			response.then().statusCode(200);
		}
		}
		if(actualValue != null)
			assertThat(actualValue, equalTo(expectedValue));
	}

	

}
