package com.example.quickcash;

import java.util.ArrayList;

public class JobPostFilter implements IJobPostFilter {
    ArrayList<IJobPostFilter> jobPostFilters;

    public JobPostFilter() {
        jobPostFilters = new ArrayList<>();
    }

    public void add(IJobPostFilter jobPostFilter) {
        if (jobPostFilter != null) {
            jobPostFilters.add(jobPostFilter);
        }
    }

    public void remove(IJobPostFilter jobPostFilter) {
        jobPostFilters.remove(jobPostFilter);
    }

    public void clear() {
        jobPostFilters.clear();
    }

    @Override
    public boolean satisfy(JobPost jobPost) {
        for (IJobPostFilter filter : jobPostFilters) {
            if (! filter.satisfy(jobPost)) {
                return false;
            }
        }

        return true;
    }
}
