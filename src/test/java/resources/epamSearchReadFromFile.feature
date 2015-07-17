Feature: google search to exercise cucumber

  I want to search or epam on google, and find a specific result.
  The first result's title should be correct.
  I want to click on it, and assert, that I'm on the correct page.
  I I I mistype the condition, the first result shouldn't be epam.
  If the result is wrong, a screenshot should have to be taken.


  @Peti
  Scenario: Search for the link that I need
    Given I navigate to googlecom "http://www.google.com"
    And I search for "epam 350"
    And The fourth results title should be "350 főt vesz fel itthon az EPAM Systems - IT café Mérleg hír"
    When I click on the title "350 főt vesz fel itthon az EPAM Systems - IT café Mérleg hír"
    Then The header should contain "EPAM Systems"
    And The last sentence should be "Az EPAM Systems a világon összesen 9 300 fejlesztőt foglalkoztat, tavalyi árbevétele 555 millió dollár volt, 2014-re 22 százalékos növekedést várnak."

  @Peti
  Scenario: Search for epam on google, and make sure the first result is what is searched for
    Given I navigate to google "http://www.google.com"
    When I search for string "epam"
    Then The first result must be equal to "EPAM | Software Product Development Services"

  @Peti
  Scenario: Search for epam on google,but accidentally mistyped the condition
    Given I navigate to "http://www.google.com"
    When I search for wrong condition "ewame"
    Then The first result shouldnt be equal to "EPAM | Software Product Development Services"
