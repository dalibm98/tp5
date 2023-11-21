Feature: Google search
  as a user want test google search function
  Scenario: basic search
    Given  i open google search page
    When lookup the word "Selenium"
    Then Serach results disppalt "Selenium"