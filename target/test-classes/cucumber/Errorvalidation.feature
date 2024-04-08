
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @tag1
  Scenario: Title of your scenario
    Given I landed on Ecommerce Page
    When Logged in with Username <name> and password <Password>
    Then "Incorrect email or password." message is displayed
    

       Examples: 
      | name               | Password       | 
      | nalini95@gmail.com | Nalini@95      | 
      
