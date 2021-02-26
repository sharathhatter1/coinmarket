Feature: Retrieve the ID of bitcoin (BTC), usd tether (USDT), and Ethereum (ETH), and convert them to Bolivian Boliviano
  I hit coinmarketcap api
  I retrieve the ID of few currencies
  and convert to Bolivian Boliviano

  @backend
  Scenario: Get and Convert Few IDs to Bolivian Boliviano
    Given I retrieve the IDs of the follwing currencies
      | BTC  |
      | USDT |
      | ETH  |
    Then I convert retrived IDs currencies to Bolivian Boliviano currency "BOB"
