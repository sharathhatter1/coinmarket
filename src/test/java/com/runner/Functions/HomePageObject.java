package com.runner.Functions;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObject {

	WebDriver driver;

	//locators
	//to select the numbers of rows in the table
	@FindBy(xpath = "//tbody/tr/td/p[contains(@color,'text')]/parent::td/parent::tr")  
	private List<WebElement> getRows;

	//element for the page number
	@FindBy(xpath = "//div[@class='sc-8ccaqg-3 locCiP']//a[@aria-label='Page 2']")  
	private WebElement bottomRowNumber;

	//element for the watchlist
	@FindBy(xpath="//div/span[text()='Watchlist']")
	private WebElement watchListlink;

	@FindBy(xpath="//a[text()='Watchlist']//parent::div//parent::a")
	private WebElement watchListlink1;

	@FindBy(xpath="//div[@class='sc-1evth2q-4 cSAlRq']//span[contains(text(),'Cryptocurrencies')]")
	private WebElement cryptoCurrencyLink;

	@FindBy(xpath="//li[@class='sc-1evth2q-3 KBqEP'][1]")
	private WebElement cryptoCurrencyLink1;

	@FindBy(xpath="//div[@class='sc-16r8icm-0 kXPxnI']//div[1]//a[1]")
	private WebElement rankingLink;


	@FindBy(xpath="//div[@class='tableWrapper___3utdq cmc-table-homepage-wrapper___22rL4']/table/tbody")
	private WebElement table; 

	@FindBy(xpath="//button[@class='sc-1ejyco6-0 fUnrJj quq9zv-3 dSenMZ table-control-filter']")
	private WebElement filter; 

	@FindBy(xpath="//button[normalize-space()='Price']")
	private WebElement priceRange; 

	@FindBy(xpath="//input[@data-qa-id='range-filter-input-min']")
	private WebElement fromPriceRange; 

	@FindBy(xpath="//input[@data-qa-id='range-filter-input-max']")
	private WebElement toPriceRange; 

	@FindBy(xpath="//button[@data-qa-id='filter-dd-button-apply']")
	private WebElement applyButton; 


	public HomePageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		driver.manage ().timeouts ().implicitlyWait ( 100, TimeUnit.SECONDS );
	}

	public void clickOnToggleLanguageToArabic() throws Throwable{
		System.out.println("*** In clickOnToggleLanguageToEnglish");

	}

	//Method to open homepage
	public void openHomePage() {
		driver.get("https://coinmarketcap.com/");
		// TODO Auto-generated method stub

	}

	//returns page title
	public String getHomePageTitle() {
		return driver.getTitle();
	}

	//returns number of rows
	public int verifyNumberOfRows() {
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			e.printStackTrace();
		}
		for(int i=0;i<=Utils.scrollSize;i++) {
			Utils.scrollByPixels();
		}
		return getRows.size();
	}

	//click on watchlist
	public WishListObject clickWatchLIst() {
		Utils.click(watchListlink);
		return new WishListObject(driver);
	}



	//scrolls to bottom row number element
	public void scrollToRowElement() {
		Utils.scrollToElement(bottomRowNumber);
	}

	//scrolls page by the pixels to load all rows
	public void scrollToEnd() {
		((JavascriptExecutor) driver)
		.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}



	//Method to add currencies to watchlist between 5 and 10
	public void addWatchList(int min, int max) {
		//instead of using random method


		try {
			Thread.sleep(2000); 
		} catch (InterruptedException e) 
		{ 
			e.printStackTrace(); 
		}
		int[] factors = {5,6,7,8,9,10}; 
		List<Integer> a1= new ArrayList<>(); 
		for (int element : factors) 
		{ 
			a1.add(element); 
		}
		ArrayList<Integer> a2= new ArrayList<Integer>(); 
		ArrayList<String> al = new ArrayList<String>(); 
		for(int i=0;i<=2;i++) 
		{ 
			int index = Utils.random(0,a1.size()); 
			Integer s =a1.get(index); 
			a2.add(s);
			a1.remove(index);
		}


		for(int i=0;i<a2.size();i++) 
		{ 
			int number = a2.get(i); 
			try {
				Thread.sleep(2000); 
			} catch (InterruptedException e) 
			{ e.printStackTrace(); }
			al.add(driver.findElement(By.xpath("//tbody/tr["+number+"]/td[3]/a/div/div/p"
					)).getText()); 
			WebElement eleWatchList = driver.findElement(By.xpath("//p[text()="+number+
							"]/parent::td/preceding-sibling::td//span[@class='icon-Star']"));
			Utils.click(eleWatchList); }

			al.stream().forEach(a->System.out.println("In home ->"+a));


		//Using random

		//		ArrayList<String> al = new ArrayList<String>(); 
		//		for(int i=0;i<=1;i++) {
		//
		//			int number = Utils.random(min,max); try { Thread.sleep(2000); } catch
		//			(InterruptedException e) { e.printStackTrace(); }
		//
		//			al.add(driver.findElement(By.xpath("//tbody/tr["+number+"]/td[3]/a/div/div/p")).getText()); 
		//			WebElement eleWatchList = driver.findElement(By.xpath("//p[text()="+number+"]/parent::td/preceding-sibling::td//span[@class='icon-Star']"));
		//			Utils.click(eleWatchList); 
		//	}

		Utils.wishListItems = al;
	}


	public void scrollToWatchList() {
		Utils.scrollToElement(watchListlink);
	}


	//Method to open new tab and opens watchlist page
	public void openTab() {
		((JavascriptExecutor)driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(Utils.WATCHLIST_URL);
	}


	//Method to select the ranking from cryptocurrency drop down
	public void clickRanking() {
		Actions action = new Actions(driver);
		action.moveToElement(cryptoCurrencyLink1).perform();
		Utils.waitForVisibility(rankingLink);
		action.moveToElement(rankingLink).click().perform();
	}

	//method to record the table data
	public List<String> pricedata() {
		List<String> priceData = new ArrayList<>();
		List<WebElement> list = driver.findElements(By.xpath("//table[contains(@class, 'cmc-table')]/tbody/tr"));
		List<WebElement> listFinal = new ArrayList<>();

		try {
			Thread.sleep(3000); 
		} catch (InterruptedException e) 
		{ 
			e.printStackTrace(); 
		}
		
		for (WebElement row : list) {
			List<WebElement> ele = row.findElements(By.xpath("//div[contains(@class,'ad-row')]"));
			if(ele!=null && ele.size() != 0){
				listFinal.add(row);
			}
		}
		

		for (WebElement dataRow : listFinal) {

			List<WebElement> cols = dataRow.findElements(By.tagName("td"));
			try {
			if(cols.size()>3){
				priceData.add(cols.get(3).getText());
			}}catch(Exception e) {
				System.out.println("ad row");
			}

		}

		Utils.allPriceDetails = priceData;
		//priceData.stream().forEach(a->System.out.println("price data ->"+a));
		return priceData;
	}

	//method to tap on filter 
	public void tapFilter() {
		Utils.click(filter);

	}

	//method to select price range  
	public void selectPrice(String from, String to) {
		Utils.click(priceRange);
		fromPriceRange.sendKeys(from);;
		toPriceRange.sendKeys(to);

	}

	//method to tap on apply button 
	public void applyFilter() {
		Utils.click(applyButton);

	}

	//method to verify the data
	public void verifyFilterData(List<String> filteredPriceDetails, Double fromPrice, Double toPrice) {
		List<Double> filteredPrices = new ArrayList<>();

		for (String filteredPriceDetail : filteredPriceDetails) {
			//System.out.println(filteredPriceDetail);
			if(filteredPriceDetail != null)
				filteredPrices.add(Double.valueOf(filteredPriceDetail.replace("$", "").replace(",", "").replace(" ", "")));
		}
		for (Double filteredPrice : filteredPrices) {
			assertThat(filteredPrice >= fromPrice && filteredPrice <= toPrice).isTrue();
		}
		driver.close();
	}

}