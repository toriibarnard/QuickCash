package com.example.quickcash;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JobPostingActivityTest {
    JobPostingActivity jobPostingActivity;

    @Before
    public void setup() {
        jobPostingActivity = new JobPostingActivity();
    }

    @Test
    public void testValidJobLocation() {
        String validLocation = "Montreal, Quebec, Canada";
        assertTrue(jobPostingActivity.isJobLocationValid(validLocation));
    }

    @Test
    public void testInvalidJobLocationWithoutCountry() {
        String invalidLocation = "Montreal, Quebec";
        assertFalse(jobPostingActivity.isJobLocationValid(invalidLocation));
    }

    @Test
    public void testInvalidJobLocationWithoutProvince() {
        String invalidLocation = "Montreal, , Canada";
        assertFalse(jobPostingActivity.isJobLocationValid(invalidLocation));
    }

    @Test
    public void testInvalidJobLocationWithoutCity() {
        String invalidLocation = "Quebec, Canada";
        assertFalse(jobPostingActivity.isJobLocationValid(invalidLocation));
    }

    @Test
    public void testInvalidJobLocationWithExtraCommas() {
        String invalidLocation = "Montreal, Quebec,, Canada";
        assertFalse(jobPostingActivity.isJobLocationValid(invalidLocation));
    }

    @Test
    public void testValidJobLocationWithExtraSpaces() {
        String validLocation = "Montreal,     Quebec, Canada";
        assertTrue(jobPostingActivity.isJobLocationValid(validLocation));
    }

    @Test
    public void testEmptyJobLocation() {
        String emptyLocation = "";
        assertFalse(jobPostingActivity.isJobLocationValid(emptyLocation));
    }
}
