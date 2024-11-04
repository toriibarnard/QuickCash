package com.example.quickcash;

import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import com.example.quickcash.util.employeeView.EmployeeApplicationsActivity;
import com.example.quickcash.util.employeeView.EmployeeOffersActivity;

@RunWith(AndroidJUnit4.class)
public class EmployeeApplicationsEspressoTest {

    @Rule
    public ActivityScenarioRule<EmployeeApplicationsActivity> activityScenarioRule =
            new ActivityScenarioRule<>(new Intent(ApplicationProvider.getApplicationContext(), EmployeeApplicationsActivity.class));

    @Before
    public void setUp() {
        // Initialize Espresso Intents to capture and verify intent-based navigation
        Intents.init();
    }

    @After
    public void tearDown() {
        // Release Espresso Intents to avoid memory leaks
        Intents.release();
    }

    @Test
    public void testOffersButtonNavigatesToEmployeeOffersActivity() {
        // Click the "Offers" button in EmployeeApplicationsActivity
        onView(withId(R.id.offersButton))  // Make sure this ID matches your "Offers" button in the layout
                .perform(click());

        // Verify that an intent was sent to open EmployeeOffersActivity
        Intents.intended(IntentMatchers.hasComponent(EmployeeOffersActivity.class.getName()));

        // Verify that the RecyclerView in EmployeeOffersActivity is displayed, confirming navigation
        onView(withId(R.id.offersRecyclerView))
                .check(matches(isDisplayed()));
    }
}