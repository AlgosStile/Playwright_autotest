Feature: Playwright Tests

  Scenario: Check page title
    Given I open the Playwright test page
    When I check the page title
    Then I click on the cart item


  Scenario: Check page H1
    Given I open the Playwright H1 page
    When I check for the H1 header
    Then I close the browser after checking welcome header

