package com.example.quickcash;

import java.io.Serializable;
import java.util.UUID;

/**
 * Represents a job posting with relevant details such as title, location, job type, and more.
 */
public class Applicant implements Serializable {
    private String jobId;
    private String applicantName;
    private String applicantEmail;
    private String applicantPhone;
    private String applicantStatus;
    private String resumeUri;

    // default constructor.
    public Applicant() {
    }

    // constructor.
    public Applicant(String jobId, String applicantName, String applicantEmail,
                     String applicantPhone, String applicantStatus, String resumeUri) {
        this.jobId = jobId;
        this.applicantName = applicantName;
        this.applicantEmail = applicantEmail;
        this.applicantPhone = applicantPhone;
        this.applicantStatus = applicantStatus;
        this.resumeUri = resumeUri;
    }

    public String getjobId(){
        return jobId;
    }

    public void setApplicantName(String applicantName){
        this.applicantName = applicantName;
    }
    public String getApplicantName(){
        return applicantName;
    }

    public void setApplicantEmail(String applicantEmail){
        this.applicantEmail = applicantEmail;
    }
    public String getApplicantEmail(){
        return applicantEmail;
    }

    public void setApplicantPhone(String applicantPhone){
        this.applicantPhone = applicantPhone;
    }
    public String getApplicantPhone(){
        return applicantPhone;
    }

    public void setApplicantStatus(String applicantStatus){
        this.applicantStatus = applicantStatus;
    }
    public String getApplicantStatus(){
        return applicantStatus;
    }

    public void setResumeUri(String resumeUri) {
        this.resumeUri= resumeUri;
    }
    public String getResumeUri() {
        return resumeUri;
    }
}