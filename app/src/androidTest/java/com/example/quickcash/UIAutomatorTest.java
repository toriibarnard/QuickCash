package com.example.quickcash;

import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;


import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class UIAutomatorTest {

    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String launcherPackageName = "com.example.quickcash"; // Update with your actual package name
    private UiDevice device;

    @Before
    public void setup() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        Context context = ApplicationProvider.getApplicationContext();
        Intent launcherIntent = context.getPackageManager().getLaunchIntentForPackage(launcherPackageName);
        assert launcherIntent != null;
        launcherIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(launcherIntent);
        device.wait(Until.hasObject(By.pkg(launcherPackageName).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void checkIfNavigatesToJobSearchResultsAfterSubmission() throws UiObjectNotFoundException {
        JobPosting jobPosting = new JobPosting();

        UiObject jobTitleBox = device.findObject(new UiSelector().text("Job Title"));
        String jobTitle = "Software Engineer";
        jobTitleBox.setText(jobTitle);
        jobPosting.setTitle(jobTitle);

        UiObject jobCompanyBox = device.findObject(new UiSelector().text("Job Company"));
        String jobCompany = "Software Engineer";
        jobCompanyBox.setText(jobCompany);
        jobPosting.setJobCompany(jobCompany);

        UiObject jobDescriptionBox = device.findObject(new UiSelector().text("Job Description"));
        String jobDescription = "We are looking for a talented and motivated Software Engineer to join our growing team.";
        jobDescriptionBox.setText(jobDescription);
        jobPosting.setJobDescription(jobDescription);

        UiObject jobLocationBox = device.findObject(new UiSelector().text("Job Location"));
        String jobLocation = "Halifax, Nova Scotia, Canada";
        jobLocationBox.setText(jobLocation);
        jobPosting.setJobLocation(jobLocation);

        UiObject typeSpinner = device.findObject(new UiSelector().textContains("Select your job type"));
        typeSpinner.click();
        List<UiObject2> types = device.findObjects(By.res("android:id/jobTypeSpinner"));
        String jobType = "Full-Time";
        types.get(1).click();
        jobPosting.setJobType(jobType);

        UiObject experienceSpinner = device.findObject(new UiSelector().textContains("Select your experience level"));
        experienceSpinner.click();
        List<UiObject2> experiences = device.findObjects(By.res("android:id/experienceSpinner"));
        String experienceLevel = "Junior";
        experiences.get(1).click();
        jobPosting.setExperienceLevel(experienceLevel);

        UiObject jobIndustryBox = device.findObject(new UiSelector().text("Job Industry"));
        String jobIndustry = "Technology";
        jobIndustryBox.setText(jobIndustry);
        jobPosting.setIndustry(jobIndustry);

        UiObject submitButton = device.findObject(new UiSelector().text("SUBMIT"));
        submitButton.clickAndWaitForNewWindow();
        UiObject jobSearchResults = device.findObject(new UiSelector().textContains("Postings"));
        assertTrue(jobSearchResults.exists());

    }

    @Test
    public void checkIfNewJobPostingIsDisplayed() throws UiObjectNotFoundException {
        JobPosting jobPosting = new JobPosting();

        UiObject jobTitleBox = device.findObject(new UiSelector().text("Job Title"));
        String jobTitle = "Software Engineer";
        jobTitleBox.setText(jobTitle);
        jobPosting.setTitle(jobTitle);

        UiObject jobCompanyBox = device.findObject(new UiSelector().text("Job Company"));
        String jobCompany = "Software Engineer";
        jobCompanyBox.setText(jobCompany);
        jobPosting.setJobCompany(jobCompany);

        UiObject jobDescriptionBox = device.findObject(new UiSelector().text("Job Description"));
        String jobDescription = "We are looking for a talented and motivated Software Engineer to join our growing team.";
        jobDescriptionBox.setText(jobDescription);
        jobPosting.setJobDescription(jobDescription);

        UiObject jobLocationBox = device.findObject(new UiSelector().text("Job Location"));
        String jobLocation = "Halifax, Nova Scotia, Canada";
        jobLocationBox.setText(jobLocation);
        jobPosting.setJobLocation(jobLocation);

        UiObject typeSpinner = device.findObject(new UiSelector().textContains("Select your job type"));
        typeSpinner.click();
        List<UiObject2> types = device.findObjects(By.res("android:id/jobTypeSpinner"));
        String jobType = "Full-Time";
        types.get(1).click();
        jobPosting.setJobType(jobType);

        UiObject experienceSpinner = device.findObject(new UiSelector().textContains("Select your experience level"));
        experienceSpinner.click();
        List<UiObject2> experiences = device.findObjects(By.res("android:id/experienceSpinner"));
        String experienceLevel = "Junior";
        experiences.get(1).click();
        jobPosting.setExperienceLevel(experienceLevel);

        UiObject jobIndustryBox = device.findObject(new UiSelector().text("Job Industry"));
        String jobIndustry = "Technology";
        jobIndustryBox.setText(jobIndustry);
        jobPosting.setIndustry(jobIndustry);

        UiObject submitButton = device.findObject(new UiSelector().text("SUBMIT"));
        submitButton.clickAndWaitForNewWindow();

        // Get the first job posting
        UiObject firstJobPosting = device.findObject(new UiSelector().index(0));
        firstJobPosting.clickAndWaitForNewWindow();

        // Check if Job ID is displayed
        UiObject jobIdTextView = device.findObject(new UiSelector().resourceId("com.example.quickcash:id/jobIdTextView")); // Adjust resource ID as needed
        assertTrue(jobIdTextView.exists());
    }
}
