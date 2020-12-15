Feature: To verify API Latest Exchange Rate
As a : User
I want: to validate exchange Rate API

Background: 
Given I want to get latest exchange rate

@Test
Scenario: To verify - GET Exchange rate request
Then I should get 200 as Success status code
Then I validate the response key "date"

@UAT @Regression
Scenario Outline: Validate that API responds with correct status code when queried with different parameter combinations
   When API is hit with end point as "<EndPoint>" "<QueryParam>" "<BaseCurrency>"
   Then API Should respond with status code as "<StatusCode>"
   And Response should contain not null values for "<BaseCurrency>"

   Examples: 
      | EndPoint   | QueryParam | BaseCurrency | StatusCode |
      | latest     | symbols    | GBP          |        200 |
      | latest     | symbols    | USD,GBP      |        200 |
      | latest     | base       | JPY          |        200 |
      | 2020-06-01 | base       | RUB          |        200 |
      | 2020-05-31 | symbols    | USD,CAD      |        200 |
      
 @UAT @Regression
  Scenario Outline: Confirm that API responds with correct base value for currency based on input
    When API is hit with end point as "<EndPoint>" "<QueryParam>" "<BaseCurrency>"
    Then API Should respond with status code as "200"
    And Response should contain base currency as "<BaseCurrency>"
    And Response should contain value "1.0" for "<BaseCurrency>"
    And Response should contain not null values for "<CheckCurrencies>"

    Examples: 
      | EndPoint | QueryParam | BaseCurrency | CheckCurrencies |
      | latest   | base       | GBP          | INR,AUD         |
      | latest   | base       | INR          | PHP,AUD         |
      | latest   | base       | AUD          | INR,USD         |
      | latest   | base       | HKD          | INR,AUD         |
      | latest   | base       | NZD          | INR,AUD         |

  @InvalidEndpoint @UAT @Regression
  Scenario Outline: Validate results when incorrect/invalid endpoint is invoked
    When API is hit with end point as ?base="<EndPoint>"
    Then API Should respond with status code as "<StatusCode>"
    And Error message should be displayed as "<ErrorMessage>"

    Examples: 
      | EndPoint | StatusCode | ErrorMessage                 |
      | BKG      |        400 | Base 'BKG' is not supported. |
      | 123      |        400 | Base '123' is not supported. |
      | @#$      |        400 | Base '@#$' is not supported. |

  @PastConversionRates @UAT @Regression
  Scenario Outline: Validate that API returns data for specific past date
    When API is hit with end point as "<EndPoint>" "<QueryParam>" "<BaseCurrency>"
    Then API Should respond with status code as "200"
    And Response should contain date as "<ResponseDate>"
    And Response should contain not null values for "<CheckCurrencies>"
    And Response should contain base currency as "<BaseCurrency>"

    Examples: 
      | EndPoint   | QueryParam | BaseCurrency | ResponseDate | CheckCurrencies |
      | 2020-06-01 | base       | INR          | 2020-06-01   | AUD,GBP         |
      | 2020-05-31 | base       | USD          | 2020-05-31   | NZD,INR         |
      | 2019-12-31 | base       | EUR          | 2019-12-31   | EUR,JPY         |

  @FutureConversionRates @UAT @Regression
  Scenario Outline: Validate that API returns data for today when queried for a future date
    When API is hit with end point as "<EndPoint>" "<QueryParam>" "<BaseCurrency>"
    Then API Should respond with status code as "200"
    And Response should contain date as "<ResponseDate>"
    And Response for future date should match with response for today
    And Response should contain not null values for "<CheckCurrencies>"

    Examples: 
      | EndPoint   | QueryParam | BaseCurrency | ResponseDate | CheckCurrencies |
      | 2020-06-03 | base       | EUR          | Today        | NZD,INR         |
      | 2020-12-31 | base       | JPY          | Today        | USD,CHF         |
      