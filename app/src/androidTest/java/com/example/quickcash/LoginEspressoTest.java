package com.example.quickcash;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.quickcash.ui.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginEspressoTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityScenarioRule = new ActivityScenarioRule<>(LoginActivity.class);

    // Test when the role selection is invalid
    @Test
    public void checkInvalidRole() {
        onView(withId(R.id.roleSelectionSpinner)).perform(click());
        onView(withText("Select Role")).perform(click());
        onView(withId(R.id.emailAddressEditField)).perform(typeText("furqaan.malik@dal.ca"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.passwordEditField)).perform(typeText("QuickCash"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_ROLE)));
    }

    // Test when the Email entered is invalid
    @Test
    public void checkInvalidEmail() {
        onView(withId(R.id.roleSelectionSpinner)).perform(click());
        onView(withText("Employee")).perform(click());
        onView(withId(R.id.emailAddressEditField)).perform(typeText("furqaan.malik.dal.ca"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.passwordEditField)).perform(typeText("QuickCash"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_EMAIL)));
    }

    // Test when the password field is empty
    @Test
    public void checkEmptyPassword() {
        onView(withId(R.id.roleSelectionSpinner)).perform(click());
        onView(withText("Employee")).perform(click());
        onView(withId(R.id.emailAddressEditField)).perform(typeText("furqaan.malik@dal.ca"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.passwordEditField)).perform(typeText("    "));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_PASSWORD)));
    }

    // Test when the password entered is incorrect
    @Test
    public void checkIncorrectPassword() throws InterruptedException {
        onView(withId(R.id.roleSelectionSpinner)).perform(click());
        onView(withText("Employee")).perform(click());
        onView(withId(R.id.emailAddressEditField)).perform(typeText("furqaan.malik@dal.ca"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.passwordEditField)).perform(typeText("incorrect"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INCORRECT_PASSWORD)));
    }

    // Test when the user does not have a registered account in database
    @Test
    public void checkUserDNEInFirebase() throws InterruptedException {
        onView(withId(R.id.roleSelectionSpinner)).perform(click());
        onView(withText("Employee")).perform(click());
        onView(withId(R.id.emailAddressEditField)).perform(typeText("user@dal.ca"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.passwordEditField)).perform(typeText("QuickCash"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.loginButton)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.USER_NOT_FOUND)));
    }
}
