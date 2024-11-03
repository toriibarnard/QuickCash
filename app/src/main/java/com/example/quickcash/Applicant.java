package com.example.quickcash;

import java.io.Serializable;

public class Applicant implements Serializable {
    private String jobId;
    private String applicantName;
    private String applicantEmail;
    private String applicantPhone;
    private String applicantStatus;
    private String salary;       // new field for salary
    private String startDate;    // new field for start date

    // Default constructor.
    public Applicant() {
    }

    // Constructor with all fields.
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

    // Getter methods
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

    // Setter methods (optional, if you need to set values after instantiation)
    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}