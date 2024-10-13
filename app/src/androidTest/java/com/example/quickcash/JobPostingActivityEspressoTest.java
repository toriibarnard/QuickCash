package com.example.quickcash;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;

// ChatGPT logs:
// https://chatgpt.com/share/66fffba7-3430-800d-a524-d15de06cf1be

@RunWith(AndroidJUnit4.class)
public class JobPostingActivityEspressoTest {

    @Rule
    public ActivityScenario<JobPostingActivity> activityScenario;

    @Before
    public void setup() {
        activityScenario = ActivityScenario.launch(JobPostingActivity.class);
        activityScenario.onActivity(activity -> {
        });
    }

    @Test
    public void checkIfValidDetails() {
        onView(withId(R.id.jobTitleBox))
                .perform(typeText("Software Engineer"));

        onView(withId(R.id.jobCompanyBox))
                .perform(typeText("Dash Hudson"));

        onView(withId(R.id.jobDescriptionBox))
                .perform(typeText("We are looking for a talented and motivated " +
                        "Software Engineer to join our growing team. The ideal candidate will work " +
                        "closely with cross-functional teams to design, develop, and maintain " +
                        "software solutions that enhance the user experience and meet business needs. " +
                        "You will be responsible for writing clean, maintainable code and " +
                        "participating in all phases of the software development lifecycle."));

        onView(withId(R.id.jobLocationBox))
                .perform(typeText("Halifax, Nova Scotia, Canada"));

        onView(withId(R.id.jobTypeSpinner))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Full-Time"))).perform(click());

        onView(withId(R.id.experienceSpinner))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Junior"))).perform(click());

        onView(withId(R.id.jobIndustryBox))
                .perform(typeText("Technology"));

        onView(withId(R.id.submitButton))
                .perform(click());

        onView(withId(R.id.statusLabel))
                .check(matches(withText(R.string.EMPTY_STRING)));
    }

    @Test
    public void checkIfInvalidExperienceLevel() {
        onView(withId(R.id.jobTitleBox))
                .perform(typeText("Software Engineer"));

        onView(withId(R.id.jobCompanyBox))
                .perform(typeText("Dash Hudson"));

        onView(withId(R.id.jobDescriptionBox))
                .perform(typeText("We are looking for a talented and motivated " +
                        "Software Engineer to join our growing team"));

        onView(withId(R.id.jobLocationBox))
                .perform(typeText("Halifax, Nova Scotia, Canada"));

        onView(withId(R.id.jobTypeSpinner))
                .perform(click());

        onData(allOf(is(instanceOf(String.class)), is("Full-Time"))).perform(click());

        onView(withId(R.id.submitButton))
                .perform(click());

        onView(withId(R.id.statusLabel))
                .check(matches(withText(R.string.INVALID_EXPERIENCE_LEVEL)));
    }

    @Test
    public void checkIfInvalidJobType() {
        onView(withId(R.id.jobTitleBox))
                .perform(typeText("Software Engineer"));

        onView(withId(R.id.jobCompanyBox))
                .perform(typeText("Dash Hudson"));

        onView(withId(R.id.jobDescriptionBox))
                .perform(typeText("We are looking for a talented and motivated " +
                        "Software Engineer to join our growing team."));

        onView(withId(R.id.jobLocationBox))
                .perform(typeText("Halifax, Nova Scotia, Canada"));

        onView(withId(R.id.experienceSpinner))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Junior"))).perform(click());

        onView(withId(R.id.submitButton))
                .perform(click());

        onView(withId(R.id.statusLabel))
                .check(matches(withText(R.string.INVALID_JOB_TYPE)));
    }

    @Test
    public void checkIfEmptyJobTitle() {

        onView(withId(R.id.jobCompanyBox))
                .perform(typeText("Dash Hudson"));

        onView(withId(R.id.jobDescriptionBox))
                .perform(typeText("We are looking for a talented and motivated " +
                        "Software Engineer to join our growing team."));

        onView(withId(R.id.jobLocationBox))
                .perform(typeText("Halifax, Nova Scotia, Canada"));

        onView(withId(R.id.jobTypeSpinner))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Full-Time"))).perform(click());

        onView(withId(R.id.experienceSpinner))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Junior"))).perform(click());

        onView(withId(R.id.jobIndustryBox))
                .perform(typeText("Technology"));

        onView(withId(R.id.submitButton))
                .perform(click());

        onView(withId(R.id.statusLabel))
                .check(matches(withText(R.string.EMPTY_JOB_TITLE)));
    }

    public void checkIfEmptyJobCompany() {

        onView(withId(R.id.jobTitleBox))
                .perform(typeText("Software Engineer"));

        onView(withId(R.id.jobDescriptionBox))
                .perform(typeText("We are looking for a talented and motivated " +
                        "Software Engineer to join our growing team."));

        onView(withId(R.id.jobLocationBox))
                .perform(typeText("Halifax, Nova Scotia, Canada"));

        onView(withId(R.id.jobTypeSpinner))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Full-Time"))).perform(click());

        onView(withId(R.id.experienceSpinner))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Junior"))).perform(click());

        onView(withId(R.id.jobIndustryBox))
                .perform(typeText("Technology"));

        onView(withId(R.id.submitButton))
                .perform(click());

        onView(withId(R.id.statusLabel))
                .check(matches(withText(R.string.EMPTY_JOB_COMPANY)));
    }

    @Test
    public void checkIfEmptyJobDescription() {
        onView(withId(R.id.jobTitleBox))
                .perform(typeText("Software Engineer"));

        onView(withId(R.id.jobCompanyBox))
                .perform(typeText("Dash Hudson"));

        onView(withId(R.id.jobLocationBox))
                .perform(typeText("Halifax, Nova Scotia, Canada"));

        onView(withId(R.id.jobTypeSpinner))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Full-Time"))).perform(click());

        onView(withId(R.id.experienceSpinner))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Junior"))).perform(click());

        onView(withId(R.id.jobIndustryBox))
                .perform(typeText("Technology"));

        onView(withId(R.id.submitButton))
                .perform(click());

        onView(withId(R.id.statusLabel))
                .check(matches(withText(R.string.EMPTY_JOB_DESCRIPTION)));
    }

    @Test
    public void checkIfEmptyJobLocation() {
        onView(withId(R.id.jobTitleBox))
                .perform(typeText("Software Engineer"));

        onView(withId(R.id.jobCompanyBox))
                .perform(typeText("Dash Hudson"));

        onView(withId(R.id.jobDescriptionBox))
                .perform(typeText("We are looking for a talented and motivated " +
                        "Software Engineer to join our growing team."));

        onView(withId(R.id.jobTypeSpinner))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Full-Time"))).perform(click());

        onView(withId(R.id.experienceSpinner))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Junior"))).perform(click());

        onView(withId(R.id.jobIndustryBox))
                .perform(typeText("Technology"));

        onView(withId(R.id.submitButton))
                .perform(click());

        onView(withId(R.id.statusLabel))
                .check(matches(withText(R.string.EMPTY_JOB_LOCATION)));
    }

    @Test
    public void checkIfInvalidJobLocation() {
        onView(withId(R.id.jobTitleBox))
                .perform(typeText("Software Engineer"));

        onView(withId(R.id.jobCompanyBox))
                .perform(typeText("Dash Hudson"));

        onView(withId(R.id.jobDescriptionBox))
                .perform(typeText("We are looking for a talented and motivated " +
                        "Software Engineer to join our growing team."));

        onView(withId(R.id.jobLocationBox))
                .perform(typeText("San Francisco"));

        onView(withId(R.id.jobTypeSpinner))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Full-Time"))).perform(click());

        onView(withId(R.id.experienceSpinner))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Junior"))).perform(click());

        onView(withId(R.id.jobIndustryBox))
                .perform(typeText("Technology"));

        onView(withId(R.id.submitButton))
                .perform(click());

        onView(withId(R.id.statusLabel))
                .check(matches(withText(R.string.INVALID_JOB_LOCATION)));
    }

    @Test
    public void checkIfEmptyJobIndustry() {
        onView(withId(R.id.jobTitleBox))
                .perform(typeText("Software Engineer"));

        onView(withId(R.id.jobCompanyBox))
                .perform(typeText("Dash Hudson"));

        onView(withId(R.id.jobDescriptionBox))
                .perform(typeText("We are looking for a talented and motivated " +
                        "Software Engineer to join our growing team."));

        onView(withId(R.id.jobLocationBox))
                .perform(typeText("San Francisco"));

        onView(withId(R.id.jobTypeSpinner))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Full-Time"))).perform(click());

        onView(withId(R.id.experienceSpinner))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Junior"))).perform(click());

        onView(withId(R.id.submitButton))
                .perform(click());

        onView(withId(R.id.statusLabel))
                .check(matches(withText(R.string.EMPTY_JOB_INDUSTRY)));
    }
}
