package com.poolcar.test.features;

import com.poolcar.R;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

public class RegistrationTest {
    

    @Given("^user will see slider view$")
    public void user_will_see_slider_view() throws Throwable {

    }

    @When("^user will swipe the sliders$")
    public void user_will_swipe_the_sliders() throws Throwable {
        onView(withId(R.id.viewPager)).perform(swipeLeft());
        onView(withId(R.id.viewPager)).perform(swipeLeft());
    }


    @Then("^Get Started button will be displayed$")
    public void get_Started_button_will_be_displayed() throws Throwable {
        Thread.sleep(2000);


    }

    @Then("^user click on Get Started button$")
    public void user_click_on_Get_Started_button() throws Throwable {
        onView(allOf(withId(R.id.getStartedButton), isDisplayed())).perform(click());

    }


    @Then("^Login Page will be displayed$")
    public void login_Page_will_be_displayed() throws Throwable {

    }


    @When("^user will click on Create an Account link$")
    public void user_will_click_on_Create_an_Account_link() throws Throwable {
        onView(withId(R.id.createAccount)).perform(click());
    }


    @Then("^user will be redirected to the User Info section of Registration process$")
    public void user_will_be_redirected_to_the_User_Info_section_of_Registration_process() throws Throwable {


    }


}
