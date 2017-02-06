Feature: Practice writing cucumber test scenarios
  So that I can participate more as an automated tester

  @poc
  Scenario: Users can login to devices as a business users
    Given I am logged into appian with username "Noor.Uddin.Business" and password "MHRA12345"
    Then I should see name of logged in user as "Noor Uddin Business"


  @poc
  Scenario: Users can login to devices as a account holder
    Given I am logged into appian with username "Noor.Uddin.Manufacturer" and password "MHRA12345"
    Then I should see name of the logged in account holder as "Noor Uddin Manufacturer"


  @poc
  Scenario Outline: Users can login to devices using different accounts
    Given I am logged into appian with username "<user>" and password "<password>"
    Then I should see name of the logged in account holder as "<expectedUserName>"
    Examples:
      | user                    | password  | expectedUserName        |
      | Noor.Uddin.Business     | MHRA12345 | Noor Uddin Business     |
      | Noor.Uddin.Manufacturer | MHRA12345 | Noor Uddin Manufacturer |

