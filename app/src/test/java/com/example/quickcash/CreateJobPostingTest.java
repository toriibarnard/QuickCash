package com.example.quickcash;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CreateJobPostingTest {
    CreateJobPosting createJobPosting;

    @Before
    public void setup() {
        createJobPosting = new CreateJobPosting();
    }

    @Test
    public void testValidJobLocation() {
        String validLocation = "Montreal, Quebec, Canada";
        assertTrue(createJobPosting.isJobLocationValid(validLocation));
    }

    @Test
    public void testInvalidJobLocationWithoutCountry() {
        String invalidLocation = "Montreal, Quebec";
        assertFalse(createJobPosting.isJobLocationValid(invalidLocation));
    }

    @Test
    public void testInvalidJobLocationWithoutProvince() {
        String invalidLocation = "Montreal, , Canada";
        assertFalse(createJobPosting.isJobLocationValid(invalidLocation));
    }

    @Test
    public void testInvalidJobLocationWithoutCity() {
        String invalidLocation = "Quebec, Canada";
        assertFalse(createJobPosting.isJobLocationValid(invalidLocation));
    }

    @Test
    public void testInvalidJobLocationWithExtraCommas() {
        String invalidLocation = "Montreal, Quebec,, Canada";
        assertFalse(createJobPosting.isJobLocationValid(invalidLocation));
    }

    @Test
    public void testValidJobLocationWithExtraSpaces() {
        String validLocation = "Montreal,     Quebec, Canada";
        assertTrue(createJobPosting.isJobLocationValid(validLocation));
    }

    @Test
    public void testEmptyJobLocation() {
        String emptyLocation = "";
        assertFalse(createJobPosting.isJobLocationValid(emptyLocation));
    }
}
