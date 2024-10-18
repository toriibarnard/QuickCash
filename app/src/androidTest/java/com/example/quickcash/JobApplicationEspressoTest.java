package com.example.quickcash;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class JobApplicationEspressoTest {

    @Rule
    public ActivityScenarioRule<RegistrationActivity> activityScenarioRule = new ActivityScenarioRule<>(RegistrationActivity.class);

    @Test
    public void checkInvalidFile() throws InterruptedException {
        onView(withId(R.id.applicationNameBox)).perform(typeText("John Doe"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.applicationPhoneBox)).perform(typeText("1234567890"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.applicationEmailBox)).perform(typeText("johndoe@example.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.applicationSubmitButton)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.applicationStatusLabel)).check(matches(withText(R.string.INVALID_FILE)));
    }

    @Test
    public void checkValidFile() {
        onView(withId(R.id.applicationNameBox)).perform(typeText("John Doe"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.applicationPhoneBox)).perform(typeText("1234567890"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.applicationEmailBox)).perform(typeText("johndoe@example.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.fileNameTextView)).perform(replaceText("resume.pdf"));
        onView(withId(R.id.applicationSubmitButton)).perform(click());
        onView(withId(R.id.applicationSubmitButton)).check(matches(withText(R.string.EMPTY_STRING)));
    }
}
