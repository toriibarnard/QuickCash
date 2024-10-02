package com.example.quickcash;

import androidx.test.ext.junit.runners.AndroidJUnit4; // specifies that the test will run with AndroidJUnit4
import androidx.test.uiautomator.UiDevice; // used to simulate user interactions with the device
import androidx.test.uiautomator.UiObject; // acts as a UI element in the app that can be interacted with
import androidx.test.uiautomator.UiObjectNotFoundException; // thrown when a UI element cannot be found
import androidx.test.uiautomator.UiSelector; // used to locate UI elements
import androidx.test.platform.app.InstrumentationRegistry; // provides instrumentation context accessibility

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation; // accesses instrumentation context
import static org.junit.Assert.assertTrue; // used for assertions in the test

@RunWith(AndroidJUnit4.class)
public class UIAutomatorTest {
    private UiDevice uiDevice; // to interact with the UI

    // to run before each test case
    @Before
    public void setUp() {
        // initialize UiDevice instance
        uiDevice = UiDevice.getInstance(getInstrumentation());
    }

    @Test
    public void TestLoginEmployer() throws UiObjectNotFoundException {
        // find the role selection menu by its resource ID and simulate a click
        UiObject roleSpinner = uiDevice.findObject(new UiSelector().resourceId("com.example.quickcash:id/roleSelectionSpinner"));
        roleSpinner.click();

        // find the employer role button and simulate a click
        UiObject employerOption = uiDevice.findObject(new UiSelector().text("Employer"));
        employerOption.click();

        // find the email input field by its resource ID and simulate entering a valid email address
        UiObject emailField = uiDevice.findObject(new UiSelector().resourceId("com.example.quickcash:id/emailAddressEditField"));
        emailField.setText("Torii.Barnard@dal.ca");

        // find the password input field by its resource ID and simulate entering a valid password
        UiObject passwordField = uiDevice.findObject(new UiSelector().resourceId("com.example.quickcash:id/passwordEditField"));
        passwordField.setText("CSCI3130");

        // find the login button by its resource ID and simulate a click
        UiObject loginButton = uiDevice.findObject(new UiSelector().resourceId("com.example.quickcash:id/loginButton"));
        loginButton.click();

        // wait for the EmployerActivity to open and verify
        UiObject employerPage = uiDevice.findObject(new UiSelector().text("This will be Employer UI"));
        // assert that the employer page is displayed
        assertTrue("Employer page is displayed", employerPage.exists());
    }

    @Test
    public void TestLoginEmployee() throws UiObjectNotFoundException {
        // find the role selection menu by its resource ID and simulate a click
        UiObject roleSpinner = uiDevice.findObject(new UiSelector().resourceId("com.example.quickcash:id/roleSelectionSpinner"));
        roleSpinner.click();

        // find the employee role button and simulate a click
        UiObject employeeOption = uiDevice.findObject(new UiSelector().text("Employee"));
        employeeOption.click();

        // find the email input field by its resource ID and simulate entering a valid email address
        UiObject emailField = uiDevice.findObject(new UiSelector().resourceId("com.example.quickcash:id/emailAddressEditField"));
        emailField.setText("Torii.Barnard@dal.ca");

        // find the password input field by its resource ID and simulate entering a valid password
        UiObject passwordField = uiDevice.findObject(new UiSelector().resourceId("com.example.quickcash:id/passwordEditField"));
        passwordField.setText("CSCI3130");

        // find the login button by its resource ID and simulate a click
        UiObject loginButton = uiDevice.findObject(new UiSelector().resourceId("com.example.quickcash:id/loginButton"));
        loginButton.click();

        // wait for the EmployeeActivity to open and verify
        UiObject employeePage = uiDevice.findObject(new UiSelector().text("This will be Employee UI"));
        // assert that the employee page is displayed
        assertTrue("Employer page is displayed", employeePage.exists());

    }
}