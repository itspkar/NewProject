
Feature: User is checking and selecting options from a dynamic dropdown
  

  Scenario: User opens browser and go to the url where dynamic dropdown is present
    
    Given User opens browser and browser gets loaded with the dynamic dropdown visible
 
    When the user tries to select the option of his wish
      
    Then the user verifies the same
    
    And user closes the browser

 