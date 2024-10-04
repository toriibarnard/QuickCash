package com.example.quickcash;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JobPostingTest {
    JobPosting jobPosting;

    @Before
    public void setup() {
        jobPosting = new JobPosting();
    }

    @Test
    public void testValidJobLocation() {
        String validLocation = "Montreal, Quebec, Canada";
        assertTrue(jobPosting.isJobLocationValid(validLocation));
    }

    @Test
    public void testInvalidJobLocationWithoutCountry() {
        String invalidLocation = "Montreal, Quebec";
        assertFalse(jobPosting.isJobLocationValid(invalidLocation));
    }

    @Test
    public void testInvalidJobLocationWithoutProvince() {
        String invalidLocation = "Montreal, , Canada";
        assertFalse(jobPosting.isJobLocationValid(invalidLocation));
    }

    @Test
    public void testInvalidJobLocationWithoutCity() {
        String invalidLocation = "Quebec, Canada";
        assertFalse(jobPosting.isJobLocationValid(invalidLocation));
    }

    @Test
    public void testInvalidJobLocationWithExtraCommas() {
        String invalidLocation = "Montreal, Quebec,, Canada";
        assertFalse(jobPosting.isJobLocationValid(invalidLocation));
    }

    @Test
    public void testValidJobLocationWithExtraSpaces() {
        String validLocation = "Montreal,     Quebec, Canada";
        assertTrue(jobPosting.isJobLocationValid(validLocation));
    }

    @Test
    public void testEmptyJobLocation() {
        String emptyLocation = "";
        assertFalse(jobPosting.isJobLocationValid(emptyLocation));
    }
}
