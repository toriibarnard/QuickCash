package com.example.quickcash;

import java.io.Serializable;

public class Applicant implements Serializable {
    private String jobId;
    private String applicantName;
    private String applicantEmail;
    private String applicantPhone;
    private String applicantStatus;

    // default constructor.
    public Applicant() {
    }

    // constructor.
    public Applicant(String jobId, String applicantName, String applicantEmail,
                     String applicantPhone, String applicantStatus) {
        this.jobId = jobId;
        this.applicantName = applicantName;
        this.applicantEmail = applicantEmail;
        this.applicantPhone = applicantPhone;
        this.applicantStatus = applicantStatus;
    }

    public String getjobId(){
        return jobId;
    }

    public String getApplicantName(){
        return applicantName;
    }

    public String getApplicantEmail(){
        return applicantEmail;
    }

    public String getApplicantPhone(){
        return applicantPhone;
    }

    public String getApplicantStatus(){
        return applicantStatus;
    }
}