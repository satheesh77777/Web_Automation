Feature: verify Redmi Mobile Features

@Mobile
  Scenario Outline:successfully Verify Redmi Mobile Features

  #Home Page
  Given user Navigates To Flipkart Homepage
  And user Searches For Product "<productDescription>"
  When user Clicks On Search Icon

# #Products List Page
  Then user Verifies Products List Page
  When user Clicks On Product Tittle "<productName>"

# Product Summary
  Then user Navigates To Product Summary Tab
  And user Verifies Product Summary Page
  And user Verifies Product Price "<productPrice>"
  And user Verifies Display Size "<displaySize>" In Highlight Section
  And user Verifies ROM Size "<ROMSize>" In Highlight Section
  And user Verifies Battery Type "<batteryType>"
  When user Clicks On Read More Button
  Then user Verifies Operating System "<operatingSystem>"
  And user Verifies Processor Brand "<processorBrand>"
#







  Examples:
    | productDescription    | productName                             | productPrice | displaySize         | ROMSize | batteryType |operatingSystem|processorBrand|
    |Redmi note 8           |Redmi Note 8 (Cosmic Purple, 64 GB)      |₹7,999        |16.0 cm (6.3 inch)   |64 GB ROM|lithium-ion  |Android Pie 9  |Snapdragon    |



























