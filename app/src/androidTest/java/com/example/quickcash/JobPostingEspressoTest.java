package com.example.quickcash;

import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import static org.hamcrest.Matchers.*;

import com.example.quickcash.util.jobPost.JobPostingActivity;

@RunWith(AndroidJUnit4.class)
public class JobPostingEspressoTest {

    @Rule
    public ActivityScenarioRule<JobPostingActivity> activityScenarioRule =
            new ActivityScenarioRule<>(new Intent(ApplicationProvider.getApplicationContext(), JobPostingActivity.class)
                    .putExtra("jobPosterID", "roni@dal.ca"));

    @Test
    public void checkWithInvalidDetails() {
        onView(withId(R.id.jobCompanyBox))
                .perform(typeText("Dash Hudson"), closeSoftKeyboard());

        onView(withId(R.id.jobTitleBox))
                .perform(typeText("Software Engineer"), closeSoftKeyboard());

        onView(withId(R.id.jobDescriptionBox))
                .perform(typeText("We are looking for a talented and motivated Software Engineer..."), closeSoftKeyboard());

        // Select an item from the job type spinner.
        onView(withId(R.id.jobTypeSpinner))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Full-time"))).perform(click());

        // Select an item from the experience level spinner.
        onView(withId(R.id.experienceSpinner))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Junior-Level"))).perform(click());

        onView(withId(R.id.jobIndustryBox))
                .perform(typeText("Information Technology"), closeSoftKeyboard());

        // Leaving the job location field empty.

        onView(withId(R.id.submitButton))
                .perform(click());

        // Verify that the status label shows success message
        onView(withId(R.id.statusLabel))
                .check(ViewAssertions.matches(ViewMatchers.withText(R.string.UNSUCCESSFUL_JOB_POST_FEEDBACK)));
    }
}
