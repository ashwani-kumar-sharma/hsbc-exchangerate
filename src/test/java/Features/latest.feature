Feature: To verify API Latest Exchange Rate
As a : User
I want: to validate exchange Rate API

Background: 
Given I want to get latest exchange rate

@Test
Scenario: To verify - GET Exchange rate request
Then I should get 200 as Success status code
Then I validate the response key "date"