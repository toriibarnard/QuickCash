package com.example.quickcash;

import org.junit.Test;
import static org.junit.Assert.*;

public class JobPostTest {

    @Test
    public void testJobPostConstructorAndGetters() {
        String jobID = "12345";
        String jobPosterID = "employer@example.com";
        String jobTitle = "Software Engineer";
        String location = "Halifax, Nova Scotia";
        String jobType = "Full-time";
        String postedDate = "October 12, 2024";
        String companyName = "QuickCash Inc.";
        String jobDescription = "Develop and maintain software applications.";
        String experienceLevel = "Junior-Level";
        String industry = "Technology";

        JobPost jobPost = new JobPost(
                jobID,
                jobPosterID,
                jobTitle,
                location,
                jobType,
                postedDate,
                companyName,
                jobDescription,
                experienceLevel,
                industry
        );

        assertEquals(jobID, jobPost.getJobID());
        assertEquals(jobPosterID, jobPost.getJobPosterID());
        assertEquals(jobTitle, jobPost.getJobTitle());
        assertEquals(location, jobPost.getLocation());
        assertEquals(jobType, jobPost.getJobType());
        assertEquals(postedDate, jobPost.getPostedDate());
        assertEquals(companyName, jobPost.getCompanyName());
        assertEquals(jobDescription, jobPost.getJobDescription());
        assertEquals(experienceLevel, jobPost.getExperienceLevel());
        assertEquals(industry, jobPost.getIndustry());
    }

    @Test
    public void testJobPostSetters() {
        JobPost jobPost = new JobPost();

        String jobID = "54321";
        String jobPosterID = "another_employer@example.com";
        String jobTitle = "Data Analyst";
        String location = "Toronto, Ontario";
        String jobType = "Part-time";
        String postedDate = "November 1, 2024";
        String companyName = "DataCorp Inc.";
        String jobDescription = "Analyze and interpret complex data sets.";
        String experienceLevel = "Mid-Senior-Level";
        String industry = "Finance";

        jobPost.setJobID(jobID);
        jobPost.setJobPosterID(jobPosterID);
        jobPost.setJobTitle(jobTitle);
        jobPost.setLocation(location);
        jobPost.setJobType(jobType);
        jobPost.setPostedDate(postedDate);
        jobPost.setCompanyName(companyName);
        jobPost.setJobDescription(jobDescription);
        jobPost.setExperienceLevel(experienceLevel);
        jobPost.setIndustry(industry);

        assertEquals(jobID, jobPost.getJobID());
        assertEquals(jobPosterID, jobPost.getJobPosterID());
        assertEquals(jobTitle, jobPost.getJobTitle());
        assertEquals(location, jobPost.getLocation());
        assertEquals(jobType, jobPost.getJobType());
        assertEquals(postedDate, jobPost.getPostedDate());
        assertEquals(companyName, jobPost.getCompanyName());
        assertEquals(jobDescription, jobPost.getJobDescription());
        assertEquals(experienceLevel, jobPost.getExperienceLevel());
        assertEquals(industry, jobPost.getIndustry());
    }

    @Test
    public void testGenerateJobID() {
        String jobID1 = JobPost.generateJobID();
        String jobID2 = JobPost.generateJobID();

        assertNotNull(jobID1);
        assertNotNull(jobID2);
        assertNotEquals(jobID1, jobID2);
    }
}
