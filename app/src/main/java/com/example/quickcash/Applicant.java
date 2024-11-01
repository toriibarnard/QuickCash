package com.example.quickcash;

import java.io.Serializable;
import java.util.UUID;

import kotlin.text.UStringsKt;

/**
 * Represents a job posting with relevant details such as title, location, job type, and more.
 */
public class Applicant implements Serializable {
    private String applicantJobID;
    private String applicantName;
    private String applicantEmail;
    private String applicantExperience;
    private String userId;

    // default constructor.
    public Applicant() {
    }

    // constructor.
    public Applicant(String applicantJobID, String applicantName, String applicantEmail, String applicantExperience, String userId) {
        this.applicantJobID = applicantJobID;
        this.applicantName = applicantName;
        this.applicantEmail = applicantEmail;
        this.applicantExperience = applicantExperience;
        this.userId = userId;
    }

    // getters and setters
    public void setApplicantJobID(String applicantJobID){
        this.applicantJobID = applicantJobID;
    }
    public String getApplicantJobID(){
        return applicantJobID;
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

    public void setApplicantExperience(String applicantExperience){
        this.applicantExperience = applicantExperience;
    }
    public String getApplicantExperience(){
        return applicantExperience;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }


}