package com.example.quickcash.util.employerView;

import java.io.Serializable;

public class Applicant implements Serializable {
    private String jobId;
    private String applicantName;
    private String applicantEmail;
    private String applicantPhone;
    private String applicantStatus;
    private String salary;
    private String startDate;
    private String applicationId; // field for Firebase key (unique ID)

    // default constructor.
    public Applicant() {
    }

    // constructor with all fields.
    public Applicant(String jobId, String applicantName, String applicantEmail,
                     String applicantPhone, String applicantStatus, String salary, String startDate) {
        this.jobId = jobId;
        this.applicantName = applicantName;
        this.applicantEmail = applicantEmail;
        this.applicantPhone = applicantPhone;
        this.applicantStatus = applicantStatus;
        this.salary = salary;
        this.startDate = startDate;
    }

    // getters
    public String getJobId() {
        return jobId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public String getApplicantPhone() {
        return applicantPhone;
    }

    public String getApplicantStatus() {
        return applicantStatus;
    }

    public String getSalary() {
        return salary;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getApplicationId() {
        return applicationId;
    }

    // setters
    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}