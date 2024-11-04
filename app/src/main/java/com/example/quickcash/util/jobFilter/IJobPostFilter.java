package com.example.quickcash.util.jobFilter;

import com.example.quickcash.util.jobPost.JobPost;

public interface IJobPostFilter {
    boolean satisfy(JobPost jobPost);
}
