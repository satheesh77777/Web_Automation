Feature: verify Each Mobile Features

  @Mobile
  Scenario Outline:successfully Verify Mobile Features

  #Home Page
    Given user Navigates To Flipkart Homepage
    And user Searches For Product "<productDescription>"
    When user Clicks On Search Icon

# #Products List Page
    Then user Verifies Products List Page
    When user Clicks On Product Tittle "<productName>"

#  #Product Summary
    Then user Navigates To Product Summary Tab
    And user Verifies Product Summary Page
    And user Verifies Product Price "<productPrice>"
    And user Verifies Display Size "<displaySize>" In Highlight Section
    And user Verifies ROM Size "<ROMSize>" In Highlight Section
    And user Verifies Battery Type "<batteryType>"
    When user Clicks On Read More Button
    Then user Verifies Operating System "<operatingSystem>"
    And user Verifies Processor Brand "<processorBrand>"








    Examples:
      | productDescription    | productName                             | productPrice | displaySize         | ROMSize | batteryType |operatingSystem|processorBrand|
    |Apple iphone 17 pro max|Apple iPhone 17 Pro Max (Deep Blue, 2 TB)|₹2,29,900     |17.53 cm (6.9 inch)  |2 TB ROM |Lithium Ion  |iOS 26         |Apple         |



























