package com.runner.Functions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject {

	WebDriver driver;

	//Locators
	@FindBy(xpath="//button[@class='sc-1ejyco6-0 eQMwpO']")
	private WebElement loginnButton;

	@FindBy(xpath="//input[@type='email']")
	private WebElement emailField;

	@FindBy(xpath="//input[@type='password']")
	private WebElement passwordField;

	@FindBy(xpath="//button[text()='Log In']")
	private WebElement loginField;

	public static String email = "sharath1203@ymail.com";
	public static String password = "123456";

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void loginUser() {
		Utils.click(loginnButton);
		Utils.click(emailField);
		Utils.sendKeys(emailField, email);
		Utils.sendKeys(passwordField, password);
		Utils.click(loginField);		
	}

}