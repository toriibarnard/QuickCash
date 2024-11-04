package com.example.quickcash.util.jobFilter;

import com.example.quickcash.util.jobPost.JobPost;

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
