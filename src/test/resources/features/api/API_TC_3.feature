Feature: Retrieve the first 10 currencies from the cryptocurrency/info call and validate mineable tag
  I hit coinmarketcap api
  I retrieve the info of Ethereum currency
  and verify the mineable tag and printing it


@backend
  Scenario: Retrieve the info for Ethereum currency and validate
    Given I retrieve Ethereum info of id 1027
	Then I verify and print the currency which has mineable tag