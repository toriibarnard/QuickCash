package com.example.quickcash;

import java.io.Serializable;
import java.util.UUID;

/**
 * Represents a job posting with relevant details such as title, location, job type, and more.
 */
public class Applicant implements Serializable {
    private String applicantID;
    private String applicantJobID;
    private String applicantName;
    private String applicantEmail;
    private String applicantPhone;
    private String employerStatus;
    private Boolean employeeStatus;

    // default constructor.
    public Applicant() {
    }

    // constructor.
    public Applicant(String applicantID, String applicantJobID, String applicantName, String applicantEmail,
                     String applicantPhone, String employerStatus, Boolean employeeStatus) {
        this.applicantID = applicantID;
        this.applicantJobID = applicantJobID;
        this.applicantName = applicantName;
        this.applicantEmail = applicantEmail;
        this.applicantPhone = applicantPhone;
        this.employerStatus = employerStatus;
        this.employeeStatus = employeeStatus;
    }

    // getters and setters
    public String generateApplicantID(){
        return UUID.randomUUID().toString(); // generates a unique id for applicant
    }
    public void setApplicantID(String applicantJobID){
        this.applicantID = applicantID;
    }
    public String getApplicantID(){
        return applicantID;
    }

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

    public void setApplicantPhone(String applicantPhone){
        this.applicantPhone = applicantPhone;
    }
    public String getApplicantPhone(){
        return applicantPhone;
    }

    public void setEmployerStatus(String employerStatus){
        this.employerStatus = employerStatus;
    }
    public String getEmployerStatus(){
        return employerStatus;
    }

    public void setEmployeeStatus(Boolean employeeStatus) {
        this.employeeStatus = employeeStatus;
    }
    public Boolean getEmployeeStatus() {
        return employeeStatus;
    }
}