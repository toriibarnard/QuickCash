package com.example.quickcash.util.jobFilter;

import com.example.quickcash.util.jobPost.JobPost;

public class JobTypeFilter implements IJobPostFilter {
    private String jobType;

    public JobTypeFilter(String jobType) {
        this.jobType = jobType;
    }

    @Override
    public boolean satisfy(JobPost jobPost) {
        return jobPost.getJobType().equals(jobType);
    }
}
