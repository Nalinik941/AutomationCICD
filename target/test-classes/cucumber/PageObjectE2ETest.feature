
@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

 Background:
 Given I landed on Ecommerce Page
 

  @tag2
  Scenario Outline: Positive test of submitting the order
  
    Given Logged in with Username <name> and password <Password>
    When I add productname <Productname> to cart
    And Checkout <Productname> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | name               | Password       | Productname     |
      | nalini95@gmail.com | Nalini@95      | ZARA COAT 3     |
      
   
