package com.poolcar.test.features;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.test.ActivityInstrumentationTestCase2;

import com.poolcar.activity.LoadingActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

public class BackgroundSteps extends ActivityInstrumentationTestCase2<LoadingActivity> {

    private Activity mActivity;
    private Context mInstrumentationContext;
    private Context mAppContext;

    @Rule
    public ActivityTestRule<LoadingActivity> activityRule
            = new ActivityTestRule<>(
            LoadingActivity.class,
            true,     // initialTouchMode
            false);   // launchActivity. False to customize the intent



    public BackgroundSteps() {
        super(LoadingActivity.class);
    }


    @Before
    public void setUp() throws Exception {
        super.setUp();
        mInstrumentationContext = getInstrumentation().getContext();
        mAppContext = getInstrumentation().getTargetContext();
        mActivity = getActivity(); // Start Activity before each test scenario
        assertNotNull(mActivity);
    }


    @Given("^user lunch the app$")
    public void user_lunch_the_app() throws Throwable {
        Intent intent = new Intent();
        activityRule.launchActivity(intent);
    }

    @Given("^app is loaded with app auth$")
    public void app_is_loaded_with_app_auth() throws Throwable {
        Thread.sleep(8000);
    }


    @After
    public void tearDown() throws Exception {
        getActivity().finish();
        super.tearDown();
    }
}
