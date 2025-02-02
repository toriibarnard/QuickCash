package com.example.quickcash;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertTrue;

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

    // Test when login is successful for an Employee.
    @Test
    public void testLoginEmployee() {
        Espresso.onView(withId(R.id.roleSelectionSpinner)).perform(click());
        Espresso.onView(withText("Employee")).perform(click());

        Espresso.onView(withId(R.id.emailAddressEditField)).perform(typeText("roni.abusayeed@gmail.com"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.passwordEditField)).perform(typeText("SecretPassword"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.loginButton)).perform(click());

        device.wait(Until.hasObject(By.textContains("Available Jobs")), 5000);

        UiObject employeeActivityTitle = device.findObject(new UiSelector().text("Available Jobs"));
        Assert.assertTrue(employeeActivityTitle.exists());
    }

    // Test when login is successful for an Employer
    @Test
    public void testLoginEmployer() {
        Espresso.onView(withId(R.id.roleSelectionSpinner)).perform(click());
        Espresso.onView(withText("Employer")).perform(click());

        Espresso.onView(withId(R.id.emailAddressEditField)).perform(typeText("roni@dal.ca"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.passwordEditField)).perform(typeText("SecretPassword"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.loginButton)).perform(click());

        device.wait(Until.hasObject(By.textContains("My Postings")), 5000);

        UiObject employeeActivityTitle = device.findObject(new UiSelector().text("My Postings"));
        Assert.assertTrue(employeeActivityTitle.exists());
    }

    // Test when a user clicks on "Create Account" button
    @Test
    public void testCreateAccount() {
        Espresso.onView(withId(R.id.createAccountButton)).perform(click());
        device.wait(Until.hasObject(By.textContains("Account Registration")), 5000);

        UiObject registrationText = new UiObject(new UiSelector().textContains("Account Registration"));
        assertTrue("Registration label exists", registrationText.exists());
    }

    // Test when a user clicks on "Forgot Password?" button
    @Test
    public void testResetPassword() {
        Espresso.onView(withId(R.id.forgotPasswordButton)).perform(click());
        device.wait(Until.hasObject(By.textContains("Reset Password")), 5000);

        UiObject resetText = new UiObject(new UiSelector().textContains("Reset Password"));
        assertTrue("Reset label exists", resetText.exists());
    }

    @Test
    public void testLogoutEmployee() throws UiObjectNotFoundException {
        // Log in as Employee
        Espresso.onView(withId(R.id.roleSelectionSpinner)).perform(click());
        Espresso.onView(withText("Employee")).perform(click());

        Espresso.onView(withId(R.id.emailAddressEditField)).perform(typeText("roni.abusayeed@gmail.com"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.passwordEditField)).perform(typeText("SecretPassword"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.loginButton)).perform(click());

        // Wait for the Employee UI to appear
        device.wait(Until.hasObject(By.textContains("Available Jobs")), 5000);

        // Scroll up to reveal the logout button
        UiScrollable scrollable = new UiScrollable(new UiSelector().scrollable(true));
        scrollable.scrollIntoView(new UiSelector().resourceIdMatches(".*:id/logoutButton"));

        // Log out
        Espresso.onView(withId(R.id.logoutButton)).perform(click());

        // Wait for the Login UI to reappear
        device.wait(Until.hasObject(By.textContains("Login")), 5000);

        UiObject loginText = new UiObject(new UiSelector().textContains("Login"));
        assertTrue("Login screen appears after logout", loginText.exists());
    }

    // Test when a user logs out as an employer
    @Test
    public void testLogoutEmployer() {
        // login as employer
        Espresso.onView(withId(R.id.roleSelectionSpinner)).perform(click());
        Espresso.onView(withText("Employer")).perform(click());

        Espresso.onView(withId(R.id.emailAddressEditField)).perform(typeText("roni@dal.ca"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.passwordEditField)).perform(typeText("SecretPassword"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.loginButton)).perform(click());

        // wait for the Employer UI to appear
        device.wait(Until.hasObject(By.textContains("My Postings")), 5000);

        // log out
        Espresso.onView(withId(R.id.logoutButton)).perform(click());

        // wait for the Login UI to reappear
        device.wait(Until.hasObject(By.textContains("Login")), 5000);

        UiObject loginText = new UiObject(new UiSelector().textContains("Login"));
        assertTrue("Login screen appears after logout", loginText.exists());
    }

    // Test when a reset password email is sent to the user
    @Test
    public void testSuccessfulResetPassword() {
        Espresso.onView(withId(R.id.forgotPasswordButton)).perform(click());
        Espresso.onView(withId(R.id.resetEmailBox)).perform(typeText("abc@dal.ca"));
        Espresso.onView(withId(R.id.sendButton)).perform(click());

        device.wait(Until.hasObject(By.textContains("Quick Cash")), 5000);

        UiObject loginText = new UiObject(new UiSelector().textContains("Quick Cash"));
        assertTrue("Quick Cash label exists", loginText.exists());
    }
}