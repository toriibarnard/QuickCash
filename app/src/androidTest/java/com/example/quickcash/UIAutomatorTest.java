package com.example.quickcash;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4; // specifies that the test will run with AndroidJUnit4
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice; // used to simulate user interactions with the device
import androidx.test.uiautomator.UiObject; // acts as a UI element in the app that can be interacted with
import androidx.test.uiautomator.UiObjectNotFoundException; // thrown when a UI element cannot be found
import androidx.test.uiautomator.UiSelector; // used to locate UI elements
import androidx.test.platform.app.InstrumentationRegistry; // provides instrumentation context accessibility
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation; // accesses instrumentation context
import static org.junit.Assert.assertTrue; // used for assertions in the test

import android.content.Context;
import android.content.Intent;

// This class is used to test how the whole application behaves
@RunWith(AndroidJUnit4.class)
public class UIAutomatorTest {
    
    private static final int LAUNCH_TIMEOUT = 10000;
    final String launcherPackage = "com.example.quickcash";
    private UiDevice device;

    // Setup before every tests
    @Before
    public void setup() {
        // Get an instance of UiDevice for interacting with the device
        device = UiDevice.getInstance(getInstrumentation());

        // Get the application context
        Context context = ApplicationProvider.getApplicationContext();

        // Retrieve the launch intent for the specified package
        final Intent appIntent = context.getPackageManager().getLaunchIntentForPackage(launcherPackage);

        // Add the flag to clear the task before launching the application
        appIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Start the application using the launch intent
        context.startActivity(appIntent);

        // Wait for the launcher package to be in the foreground
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);
    }

    // Test when login is successful for an Employee
    @Test
    public void testLoginEmployee() {
        Espresso.onView(withId(R.id.roleSelectionSpinner)).perform(click());
        Espresso.onView(withText("Employee")).perform(click());

        Espresso.onView(withId(R.id.emailAddressEditField)).perform(typeText("Torii.Barnard@dal.ca"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.passwordEditField)).perform(typeText("CSCI3130"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.loginButton)).perform(click());
        device.wait(Until.hasObject(By.textContains("This will be Employee UI")), 5000);

        UiObject employeeText = new UiObject(new UiSelector().textContains("This will be Employee UI"));
        assertTrue("Employee label exists", employeeText.exists());
    }

    // Test when login is successful for an Employer
    @Test
    public void testLoginEmployer() {
        Espresso.onView(withId(R.id.roleSelectionSpinner)).perform(click());
        Espresso.onView(withText("Employer")).perform(click());

        Espresso.onView(withId(R.id.emailAddressEditField)).perform(typeText("Shivam.Shah@dal.ca"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.passwordEditField)).perform(typeText("Iteration1"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.loginButton)).perform(click());
        device.wait(Until.hasObject(By.textContains("This will be Employer UI")), 5000);

        UiObject employerText = new UiObject(new UiSelector().textContains("This will be Employer UI"));
        assertTrue("Employer label exists", employerText.exists());
    }

    // Test when a user clicks on "Forgot Password?" button
    @Test
    public void testResetPassword() {
        Espresso.onView(withId(R.id.forgotPasswordButton)).perform(click());
        device.wait(Until.hasObject(By.textContains("This will be Employee UI")), 5000);

        UiObject resetText = new UiObject(new UiSelector().textContains("This will be Reset Password Ui"));
        assertTrue("Reset label exists", resetText.exists());
    }
}