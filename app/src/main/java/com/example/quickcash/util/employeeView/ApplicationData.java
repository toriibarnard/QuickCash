package com.example.quickcash.util.employeeView;

import java.io.Serializable;
import java.util.UUID;

/**
 * Represents an application
 */
public class ApplicationData implements Serializable {
    private String applicationID;
    private String jobID;
    private String applicantEmail;
    private String applicantPhone;
    private String applicantName;
    private String applicationStatus;

    public ApplicationData() {}

    public ApplicationData(String applicationID,
                           String applicantEmail,
                           String applicantPhone,
                           String applicantName,
                           String applicationStatus,
                           String jobID) {
        this.applicationID = applicationID;
        this.applicantEmail = applicantEmail;
        this.applicantPhone = applicantPhone;
        this.applicantName = applicantName;
        this.applicationStatus = applicationStatus;
        this.jobID = jobID;
    }

    public static String generateApplicationID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Get application ID
     * @return application ID
     */
    public String getApplicationID() {
        return applicationID;
    }

    /**
     * Set application ID
     * @param applicationID application ID
     */
    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    /**
     * Get job ID
     * @return job ID
     */
    public String getJobID() {
        return jobID;
    }

    /**
     * Set job ID
     * @param jobID job ID
     */
    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    /**
     * Get applicant email
     * @return applicant email
     */
    public String getApplicantEmail() {
        return applicantEmail;
    }

    /**
     * Set applicant email
     * @param applicantEmail applicant email
     */
    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    /**
     * Get applicant phone
     * @return applicant phone
     */
    public String getApplicantPhone() {
        return applicantPhone;
    }

    /**
     * Set applicant phone
     * @param applicantPhone applicant phone
     */
    public void setApplicantPhone(String applicantPhone) {
        this.applicantPhone = applicantPhone;
    }

    /**
     * Get applicant name
     * @return applicant name
     */
    public String getApplicantName() {
        return applicantName;
    }

    /**
     * Set applicant name
     * @param applicantName applicant name
     */
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    /**
     * Get application status
     * @return application status
     */
    public String getApplicationStatus() {
        return applicationStatus;
    }

    /**
     * Set application status
     * @param applicationStatus application status
     */
    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
