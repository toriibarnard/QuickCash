package com.example.quickcash;

import java.util.UUID;

/**
 * Represents a job posting with relevant details such as title, location, job type, and more.
 */
public class JobPost {
    private String jobPosterID;
    private String jobID;
    private String jobTitle;
    private String location;
    private String jobType;
    private String postedDate;
    private String companyName;
    private String jobDescription;
    private String experienceLevel;
    private String industry;

    // Default constructor. (Required for use in DatabaseReference.setValue() method in FirebaseCRUD)
    public JobPost() {}

    // Constructor.
    public JobPost(String jobID, String jobPosterID, String jobTitle, String location, String jobType, String postedDate, String companyName,
                   String jobDescription, String experienceLevel, String industry) {
        this.jobID = jobID;
        this.jobPosterID = jobPosterID;
        this.jobTitle = jobTitle;
        this.location = location;
        this.jobType = jobType;
        this.postedDate = postedDate;
        this.companyName = companyName;
        this.jobDescription = jobDescription;
        this.experienceLevel = experienceLevel;
        this.industry = industry;
    }

    /**
     * Generates a unique job ID using a randomly generated UUID.
     *
     * @return a string representing the generated unique job ID.
     */
    public static String generateJobID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Gets the job ID.
     * @return the job ID.
     */
    public String getJobID() {
        return jobID;
    }

    /**
     * Sets the job ID.
     * @param jobID the job ID to set.
     */
    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    /**
     * Gets the job poster's ID.
     * @return the job poster's ID.
     */
    public String getJobPosterID() {
        return jobPosterID;
    }

    /**
     * Sets the job poster's ID.
     * @param jobPosterID the job poster's ID to set.
     */
    public void setJobPosterID(String jobPosterID) {
        this.jobPosterID = jobPosterID;
    }

    /**
     * Gets the job title.
     * @return the job title.
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Sets the job title.
     * @param jobTitle the job title to set.
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * Gets the location of the job.
     * @return the location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the job.
     * @param location the location to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the job type (e.g., full-time, part-time).
     * @return the job type.
     */
    public String getJobType() {
        return jobType;
    }

    /**
     * Sets the job type (e.g., full-time, part-time).
     * @param jobType the job type to set.
     */
    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    /**
     * Gets the date when the job was posted.
     * @return the posted date.
     */
    public String getPostedDate() {
        return postedDate;
    }

    /**
     * Sets the date when the job was posted.
     * @param postedDate the posted date to set.
     */
    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    /**
     * Gets the company name.
     * @return the company name.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the company name.
     * @param companyName the company name to set.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Gets the job description.
     * @return the job description.
     */
    public String getJobDescription() {
        return jobDescription;
    }

    /**
     * Sets the job description.
     * @param jobDescription the job description to set.
     */
    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    /**
     * Gets the experience level (e.g., intern, junior, senior).
     * @return the experience level.
     */
    public String getExperienceLevel() {
        return experienceLevel;
    }

    /**
     * Sets the experience level (e.g., intern, junior, senior).
     * @param experienceLevel the experience level to set.
     */
    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    /**
     * Gets the industry related to the job.
     * @return the industry.
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * Sets the industry related to the job.
     * @param industry the industry to set.
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
