package com.example.quickcash;

import org.junit.Test;
import static org.junit.Assert.*;

public class JobPostValidatorTest {

    @Test
    public void testValidateJobPost_ValidInput() {
        JobPost jobPost = new JobPost(
                JobPost.generateJobID(),
                "employer@example.com",
                "Software Engineer",
                "Halifax, Nova Scotia",
                "Full-time",
                "October 12, 2024",
                "QuickCash Inc.",
                "Develop and maintain software applications.",
                "Junior-Level",
                "Technology"
        );

        assertTrue(JobPostValidator.validateJobPost(jobPost));
    }

    @Test
    public void testValidateJobPost_InvalidJobTitle() {
        JobPost jobPost = new JobPost(
                JobPost.generateJobID(),
                "employer@example.com",
                "",
                "Halifax, Nova Scotia",
                "Full-time",
                "October 12, 2024",
                "QuickCash Inc.",
                "Develop and maintain software applications.",
                "Junior-Level",
                "Technology"
        );

        assertFalse(JobPostValidator.validateJobPost(jobPost));
    }

    @Test
    public void testValidateJobPost_InvalidJobType() {
        JobPost jobPost = new JobPost(
                JobPost.generateJobID(),
                "employer@example.com",
                "Software Engineer",
                "Halifax, Nova Scotia",
                "Temporary", // Invalid job type.
                "October 12, 2024",
                "QuickCash Inc.",
                "Develop and maintain software applications.",
                "Junior-Level",
                "Technology"
        );

        assertFalse(JobPostValidator.validateJobPost(jobPost));
    }

    @Test
    public void testValidateJobPost_NullFields() {
        JobPost jobPost = new JobPost(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        assertFalse(JobPostValidator.validateJobPost(jobPost));
    }

    @Test
    public void testValidateJobPost_InvalidExperienceLevel() {
        JobPost jobPost = new JobPost(
                JobPost.generateJobID(),
                "employer@example.com",
                "Software Engineer",
                "Halifax, Nova Scotia",
                "Full-time",
                "October 12, 2024",
                "QuickCash Inc.",
                "Develop and maintain software applications.",
                "Expert", // Invalid experience level.
                "Technology"
        );

        assertFalse(JobPostValidator.validateJobPost(jobPost));
    }

    @Test
    public void testValidateJobPost_EmptyCompanyName() {
        JobPost jobPost = new JobPost(
                JobPost.generateJobID(),
                "employer@example.com",
                "Software Engineer",
                "Halifax, Nova Scotia",
                "Full-time",
                "October 12, 2024",
                "", // Empty company name.
                "Develop and maintain software applications.",
                "Junior-Level",
                "Technology"
        );

        assertFalse(JobPostValidator.validateJobPost(jobPost));
    }
}
