Feature: Display the dropdown menu on the Cryptocurrencies tab.
 and Click any of the three Full List options on this menu.
and Record the data on the current page.
and Apply any combination of filters, displayed in the three dropdown menus above the tabs.
 and Check against the data recorded in Step 4.

Background: 
	Given I open on coinmarket homepage 
	
@storefront
  Scenario: User click Full list and then Filter the currencies based on Price and compare the results
    Given User on home page
    And the user select Ranking from Cryptocurrencies dropdown
    And record the data in the page
    When the user uses the filter price range between "50" and "100"
    Then verify that page should display currencies with the price between 50.00 and 100.00
	