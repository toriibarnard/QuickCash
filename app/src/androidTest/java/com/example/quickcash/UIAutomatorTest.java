package com.example.quickcash;

import static androidx.test.uiautomator.Until.hasObject;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;
import static org.junit.Assert.assertNotNull;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
        UiObject jobTitleBox = device.findObject(new UiSelector().text("Job Title"));
        jobTitleBox.setText("Software Engineer");

        UiObject jobDescriptionBox = device.findObject(new UiSelector().text("Job Description"));
        jobDescriptionBox.setText("We are looking for a talented and motivated " +
                "Software Engineer to join our growing team.");

        UiObject jobLocationBox = device.findObject(new UiSelector().text("Job Location"));
        jobLocationBox.setText("Halifax, Nova Scotia, Canada");

        UiObject typeSpinner = device.findObject(new UiSelector().textContains("Select your job type"));
        typeSpinner.click();
        List<UiObject2> types = device.findObjects(By.res("android:id/jobTypeSpinner"));
        types.get(1).click();

        UiObject experienceSpinner = device.findObject(new UiSelector().textContains("Select your experience level"));
        experienceSpinner.click();
        List<UiObject2> experiences = device.findObjects(By.res("android:id/experienceSpinner"));
        experiences.get(1).click();

        UiObject jobIndustryBox = device.findObject(new UiSelector().text("Job Industry"));
        jobIndustryBox.setText("Technology");

        UiObject submitButton = device.findObject(new UiSelector().text("SUBMIT"));
        submitButton.clickAndWaitForNewWindow();
        UiObject jobSearchResults = device.findObject(new UiSelector().textContains("Postings"));
        assertTrue(jobSearchResults.exists());

    }
}
