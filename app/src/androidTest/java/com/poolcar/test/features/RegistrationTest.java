package com.poolcar.test.features;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.v4.content.ContextCompat;

import com.poolcar.R;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

public class RegistrationTest {

    private static final int PERMISSIONS_DIALOG_DELAY = 3000;
    private static final int GRANT_BUTTON_INDEX = 1;

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


    @Then("^user will input userid as \"(.*?)\"$")
    public void user_will_input_userid_as(String arg1) throws Throwable {
        onView(withId(R.id.userId)).perform(typeText(arg1)).perform(closeSoftKeyboard());
    }

    @Then("^user will input emailid as \"(.*?)\"$")
    public void user_will_input_emailid_as(String arg1) throws Throwable {
        onView(withId(R.id.emailId)).perform(typeText(arg1)).perform(closeSoftKeyboard());
    }

    @Then("^user will retype emailid as \"(.*?)\"$")
    public void user_will_retype_emailid_as(String arg1) throws Throwable {
        onView(withId(R.id.reEmailId)).perform(typeText(arg1)).perform(closeSoftKeyboard());
    }

    @Then("^user will input password as \"(.*?)\"$")
    public void user_will_input_password_as(String arg1) throws Throwable {
        onView(withId(R.id.password)).perform(typeText(arg1)).perform(closeSoftKeyboard());
    }

    @Then("^user will retype password as \"(.*?)\"$")
    public void user_will_retype_password_as(String arg1) throws Throwable {
        onView(withId(R.id.rePassword)).perform(typeText(arg1)).perform(closeSoftKeyboard());
    }

    @Then("^user will input firstName as \"(.*?)\"$")
    public void user_will_input_firstName_as(String arg1) throws Throwable {
        onView(withId(R.id.firstName)).perform(typeText(arg1)).perform(closeSoftKeyboard());
    }

    @Then("^user will input lastName as \"(.*?)\"$")
    public void user_will_input_lastName_as(String arg1) throws Throwable {
        onView(withId(R.id.lastName)).perform(typeText(arg1)).perform(closeSoftKeyboard());
        Thread.sleep(2000);
    }
    @Then("^user will click continue$")
    public void user_will_click_continue() throws Throwable {
        onView(withId(R.id.continueMenu)).perform(click());
    }

    @Then("^user will get Phone and Location verify screen$")
    public void user_will_get_Phone_and_Location_verify_screen() throws Throwable {

    }


    @When("^user will click on alow permition for location$")
    public void user_will_click_on_alow_permition_for_location() throws Throwable {
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        UiObject allowPermissions = device.findObject(new UiSelector().text("ALLOW"));
        if (allowPermissions.exists()) {
            try {
                allowPermissions.click();
            } catch (UiObjectNotFoundException e) {

            }
        }
    }

    @Then("^user will get the current location$")
    public void user_will_get_the_current_location() throws Throwable {
        Thread.sleep(3000);
    }

    @Then("^user will click on phoneNumber filed$")
    public void user_will_click_on_phoneNumber_filed() throws Throwable {
        onView(withId(R.id.phoneNumberField)).perform(click());
        Thread.sleep(800);
    }


    @Then("^User will input phone number as \"(.*?)\"$")
    public void user_will_input_phone_number_as(String arg1) throws Throwable {
        char[] numbers = arg1.toCharArray();
        for(char number : numbers) {
            switch (number) {
                case '0':
                    onView(withId(R.id.key_0)).perform(click());
                    break;
                case '1':
                    onView(withId(R.id.key_1)).perform(click());
                    break;
                case '2':
                    onView(withId(R.id.key_2)).perform(click());
                    break;
                case '3':
                    onView(withId(R.id.key_3)).perform(click());
                    break;
                case '4':
                    onView(withId(R.id.key_4)).perform(click());
                    break;
                case '5':
                    onView(withId(R.id.key_5)).perform(click());
                    break;
                case '6':
                    onView(withId(R.id.key_6)).perform(click());
                    break;
                case '7':
                    onView(withId(R.id.key_7)).perform(click());
                    break;
                case '8':
                    onView(withId(R.id.key_8)).perform(click());
                    break;
                case '9':
                    onView(withId(R.id.key_9)).perform(click());
                    break;
            }
        }

    }


    @Then("^user will click continue to register$")
    public void user_will_click_continue_to_register() throws Throwable {
        onView(withId(R.id.keyboard_done)).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.continueMenu)).perform(click());
        Thread.sleep(1000);
    }


    @Then("^user will get One Time Password page$")
    public void user_will_get_One_Time_Password_page() throws Throwable {

    }


    @When("^user will type the One Time Password as \"(.*?)\"$")
    public void user_will_type_the_One_Time_Password_as(String arg1) throws Throwable {
        char[] numbers = arg1.toCharArray();
        int i=0;
        for(char number : numbers) {
            switch (i){
                case 0:
                    onView(withId(R.id.otp1)).perform(typeText(String.valueOf(number)));
                    break;
                case 1:
                    onView(withId(R.id.otp2)).perform(typeText(String.valueOf(number)));
                    break;
                case 2:
                    onView(withId(R.id.otp3)).perform(typeText(String.valueOf(number)));
                    break;
                case 3:
                    onView(withId(R.id.otp4)).perform(typeText(String.valueOf(number)));
                    break;
                case 4:
                    onView(withId(R.id.otp5)).perform(typeText(String.valueOf(number)));
                    break;
            }
            i++;
        }
        Thread.sleep(2000);
    }

    @When("^click on Confirm OTP$")
    public void click_on_Confirm_OTP() throws Throwable {
        onView(withId(R.id.submitOTP)).perform(click());
        Thread.sleep(1000);
    }
}
