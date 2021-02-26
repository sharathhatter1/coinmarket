Feature: Store Front Scenarios

Background: 
	Given I open on coinmarket homepage 
	And I login with my credentials
	And I go to Watchlist page
	And I clear watchlist if any and navigates to homepage

@storefront	
Scenario: I select crypto currencies and add to watchlist and verify
    When I will select few random currencies between 5 and 10
    Then I navigate to watchlist page
    And verify all currencies are exists in watchlist and Logout
    
      