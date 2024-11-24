package com.example.quickcash.util.employeeView;

import java.io.Serializable;
import java.util.UUID;

/**
 * Represents an application
 */
public class ApplicationData implements Serializable {
    private String jobIdAndTitle;
    private String companyName;
    private String jobLocation;
    private String applicationDate;
    private String status;

    public ApplicationData(String jobIdAndTitle, String companyName, String jobLocation, String applicationDate, String status) {
        this.jobIdAndTitle = jobIdAndTitle;
        this.companyName = companyName;
        this.jobLocation = jobLocation;
        this.applicationDate = applicationDate;
        this.status = status;
    }

    public String getJobIdAndTitle() {
        return jobIdAndTitle;
    }

    public void setJobIdAndTitle(String jobIdAndTitle) {
        this.jobIdAndTitle = jobIdAndTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
