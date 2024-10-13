package com.example.quickcash;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RegistrationEspressoTest {

    @Rule
    public ActivityScenarioRule<RegistrationActivity> activityScenarioRule = new ActivityScenarioRule<>(RegistrationActivity.class);

    @Test
    public void testValidCredentials() {

        onView(withId(R.id.nameBox)).perform(typeText("John Doe"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.phoneBox)).perform(typeText("1234567890"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.emailBox)).perform(typeText("johndoe@example.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.passwordBox)).perform(typeText("password123"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.roleSpinner)).perform(click());
        onView(withText("Employee")).perform(click());

        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_STRING)));
    }

    @Test
    public void testInvalidName() throws InterruptedException {

        onView(withId(R.id.nameBox)).perform(typeText("12John"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.phoneBox)).perform(typeText("1234567890"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.emailBox)).perform(typeText("johndoe@example.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.passwordBox)).perform(typeText("password123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.roleSpinner)).perform(click());
        onView(withText("Employee")).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_NAME)));
    }

    @Test
    public void testInvalidPhone() throws InterruptedException {

        onView(withId(R.id.nameBox)).perform(typeText("John Doe"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.phoneBox)).perform(typeText("12345678"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.emailBox)).perform(typeText("johndoe@example.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.passwordBox)).perform(typeText("password123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.roleSpinner)).perform(click());
        onView(withText("Employee")).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_PHONE_NUMBER)));
    }

    @Test
    public void testInvalidEmail() throws InterruptedException {

        onView(withId(R.id.nameBox)).perform(typeText("John Doe"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.phoneBox)).perform(typeText("1234567890"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.emailBox)).perform(typeText("johndoe.example.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.passwordBox)).perform(typeText("password123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.roleSpinner)).perform(click());
        onView(withText("Employee")).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_EMAIL)));
    }

    @Test
    public void testInvalidPassword() throws InterruptedException {

        onView(withId(R.id.nameBox)).perform(typeText("John Doe"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.phoneBox)).perform(typeText("1234567890"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.emailBox)).perform(typeText("johndoe@example.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.passwordBox)).perform(typeText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.roleSpinner)).perform(click());
        onView(withText("Employee")).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_PASSWORD)));
    }

    @Test
    public void testInvalidRole() throws InterruptedException {

        onView(withId(R.id.nameBox)).perform(typeText("John Doe"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.phoneBox)).perform(typeText("1234567890"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.emailBox)).perform(typeText("johndoe@example.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.passwordBox)).perform(typeText("password123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.roleSpinner)).perform(click());
        onView(withText("Select Role")).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_ROLE)));
    }
}
