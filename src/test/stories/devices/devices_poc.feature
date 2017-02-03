Feature: Write PC for devices

  @poc
  Scenario: Users can login to devices as a business users
    Given I am logged into appian with username "Noor.Uddin.Business" and password "MHRA12345"
    Then I should see name of logged in user as "Noor Uddin Business"


  @poc
  Scenario: Users can login to devices as a account holder
    Given I am logged into appian with username "Noor.Uddin.Manufacturer" and password "MHRA12345"
    Then I should see name of the logged in account holder as "Noor Uddin Manufacturer"