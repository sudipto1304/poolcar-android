Feature: Poolcar Registration

  Background:
    Given user lunch the app
    And app is loaded with app auth

  Scenario Outline: New user Registration
    Given user will see slider view
    When user will swipe the sliders
    Then Get Started button will be displayed
    And user click on Get Started button
    Then Login Page will be displayed
    When user will click on Create an Account link
    Then user will be redirected to the User Info section of Registration process
    Then user will input userid as "<userid>"
    And user will input emailid as "<emailid>"
    And user will retype emailid as "<emailid>"
    And user will input password as "<password>"
    And user will retype password as "<password>"
    And user will input firstName as "<firstName>"
    And user will input lastName as "<lastName>"
    Then user will click continue
    Then user will get Phone and Location verify screen
    When user will click on alow permition for location
    Then user will get the current location
    Then user will click on phoneNumber filed
    And User will input phone number as "<phone>"
    Then user will click continue to register
    Then user will get One Time Password page
    When user will type the One Time Password as "<otp>"
    And click on Confirm OTP

    Examples:
    |userid|emailid|password|firstName|lastName|phone|otp|
    |sudipto1302|sudipto1306@gmail.com|Test@1234|Sudipto|Aich|4685875852|12345|
