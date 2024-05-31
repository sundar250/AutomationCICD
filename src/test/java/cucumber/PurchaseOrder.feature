
@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

Background: 
Given I landed on Ecommerce page


  @tag2
  Scenario Outline: Positive Test for submitting order
    Given logged in with username <name> and passcode <password>
    When I add product <productName> from cart
    And CheckOut <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | name                       | password   |productName
      | abc@company.com  | Testing123!| ZARA COAT 3
   

  
