package com.runner;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class testcode {


	private static WebDriver driver = null;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String homePage = "https://coinmarketcap.com/";
		String url = "";
		int respCode = 200;

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get(homePage);
		Thread.sleep(5000);

		List<WebElement> list = driver.findElements(By.xpath("//table[contains(@class, 'cmc-table')]/tbody/tr"));
		List<WebElement> listFinal = new ArrayList<>();

		for (WebElement row : list) {
			List<WebElement> ele = row.findElements(By.xpath("//div[contains(@class,'ad-row')]"));
			if(ele.size() != 0){
				listFinal.add(row);
			}
		}

		for (WebElement dataRow : listFinal) {

			List<WebElement> cols = dataRow.findElements(By.tagName("td"));
			//System.out.println("table data size ->"+cols);
			if(cols.size()>3){
				System.out.println(cols.get(3).getText());
				//priceData.add(cols.get(3).getText());
			}

		}


		//driver.quit();

	}

}
