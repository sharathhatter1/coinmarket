package com.stepDefinition;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import com.runner.Functions.LoginPageObject;
import com.runner.Functions.Utils;
import cucumber.api.java.en.Given;

public class LoginPageSteps {
	
	public WebDriver driver;
	public LoginPageObject loginPage;

	public LoginPageSteps(){
		driver = (WebDriver) Utils.getDriver();
		loginPage = Utils.getLoginPageObject();
	}	
	
	@Given("I login with my credentials")
	public void i_login_with_my_credentials() {
		loginPage.loginUser();
	}
	
    @AfterClass
    public void tearDown() throws InterruptedException {
    	Thread.sleep(1000);
    	driver.quit();
    }
    
}
