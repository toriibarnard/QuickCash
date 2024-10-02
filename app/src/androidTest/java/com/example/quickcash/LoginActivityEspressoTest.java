package com.example.quickcash;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.RootMatchers.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginActivityEspressoTest {

    // launch LoginActivity for testing
    @Rule
    public ActivityScenarioRule<LoginActivity> activityTestRule = new ActivityScenarioRule<>(LoginActivity.class);

    // espresso test for the LoginActivity
    @Test
    public void testValidLogin() {
        // Get the ActivityScenario from the rule
        ActivityScenario<LoginActivity> scenario = activityTestRule.getScenario();

        // find the role selection dropdown menu by its ID and simulate a click
        onView(withId(R.id.roleSelectionSpinner)).perform(click());
        // this simulates a user clicking the dropdown menu

        // find the employee role button by its string and simulate a click
        onView(allOf(is(instanceOf(String.class)), is("Employer"))).perform(click());
        // this simulates a user clicking the employee option

        // find the email input field by its ID and simulate entering a valid email address
        onView(withId(R.id.emailAddressEditField)).perform(typeText("Torii.Barnard@dal.ca"));
        // this simulates a user entering their email in the email field

        // find the password input field by its ID and simulate entering a valid email address
        onView(withId(R.id.passwordEditField)).perform(typeText("CSCI3130"));
        // this simulates a user entering their password in the password field

// not sure if this is necessary
//        // close the keyboard to avoid blocking other actions
//        closeSoftKeyboard();
//        // this is necessary in case keyboard stays open after entering text and blocks UI elements

        // find the login button by its ID and simulate a click
        onView(withId(R.id.loginButton)).perform(click());
        // this simulates a user clicking the login button

        // check if a success message is displayed
        scenario.onActivity(activity -> {
            onView(withText("Successful")).inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).check(matches(isDisplayed()));
        });
    }

}
