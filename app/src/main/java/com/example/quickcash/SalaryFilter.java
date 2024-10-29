package com.example.quickcash;

public class SalaryFilter implements IJobPostFilter {
    private final double salaryMin;
    private final double salaryMax;

    public SalaryFilter(double salaryMin, double salaryMax) {
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
    }

    @Override
    public boolean satisfy(JobPost jobPost) {
        return jobPost.getSalary() >= salaryMin && jobPost.getSalary() <= salaryMax;
    }
}
