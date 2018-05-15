Feature: Poolcar Registration

  Scenario: Navigate to Create An Account Screen
    Given user lunch the app
    And app is loaded with app auth
    Then user will see slider view
    When user will swipe the sliders
    Then Get Started button will be displayed
    And user click on Get Started button
    Then Login Page will be displayed
    When user will click on Create an Account link
    Then user will be redirected to the User Info section of Registration process
