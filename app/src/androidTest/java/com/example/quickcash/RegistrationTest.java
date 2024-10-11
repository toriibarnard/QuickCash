package com.example.quickcash;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class RegistrationTest {

    @Test
    public void fillOutRegistrationForm() {
        // Launch the Registration activity
        Intent intent = new Intent();
        ActivityScenario.launch(Registration.class);

        onView(withId(R.id.phoneBox)).perform(ViewActions.typeText("1234567890"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.nameBox)).perform(ViewActions.typeText("John Doe"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.emailBox)).perform(ViewActions.typeText("johndoe@example.com"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.passwordBox)).perform(ViewActions.typeText("password123"), ViewActions.closeSoftKeyboard());

        // Select a role from the Spinner (e.g., "employee")
        onView(withId(R.id.roleSpinner)).perform(click());
        onData(anything()).atPosition(2).perform(click());

        // Click the Register button
        onView(withId(R.id.registerButton)).perform(click());

        // Verify that a TextView displays the status message (if any)
        onView(withId(R.id.statusLabel)).check(matches(isDisplayed()));
    }
}
