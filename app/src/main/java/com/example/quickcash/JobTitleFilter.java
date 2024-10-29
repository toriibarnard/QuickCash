package com.example.quickcash;

public class JobTitleFilter implements IJobPostFilter {
    private String jobTitle;

    public JobTitleFilter(String jobName) {
        this.jobTitle = jobName;
    }

    @Override
    public boolean satisfy(JobPost jobPost) {
        return jobPost.getJobTitle().contains(jobTitle);
    }
}
