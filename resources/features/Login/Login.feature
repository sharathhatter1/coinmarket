#Feature file : Login.feature
Feature: Store Front Scenarios

Background: 
	Given I open on coinmarket homepage 

@storefront
Scenario: User verify the row count
	Given User on home page
	And Verifies the home page and verify page title
    When User check the number of rows 
	Then Row count should be 100

	
