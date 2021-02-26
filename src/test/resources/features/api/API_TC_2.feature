Feature: Retrieve the Ethereum (ID 1027) technical documentation website from the cryptocurrency/info call.
  I hit coinmarketcap api
  I retrieve Ethereum currency info
  and verify details

@backend
  Scenario: Ethereum currency and validate
    Given I retrieve Ethereum info of id 1027
    Then I verify the following details 
      | logo          | https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png |
      | technical_doc | https://github.com/ethereum/wiki/wiki/White-Paper            |
      | symbol        | ETH                                                          |
      | date_added    | 2015-08-07T00:00:00.000Z                                     |
      | platform      | null                                                         |
      | tags          | mineable                                                     |
