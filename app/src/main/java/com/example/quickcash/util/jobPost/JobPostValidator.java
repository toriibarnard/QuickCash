package com.example.quickcash.util.jobPost;

/**
 * Provides validation methods for JobPost objects.
 */
public class JobPostValidator {

    /**
     * Validates a JobPost object by checking all its fields.
     *
     * @param jobPost the JobPost object to validate.
     * @return true if all fields are valid; false otherwise.
     */
    public static boolean validateJobPost(JobPost jobPost) {
        return jobIDValidator(jobPost.getJobID()) &&
                jobTitleValidator(jobPost.getJobTitle()) &&
                locationValidator(jobPost.getLocation()) &&
                jobTypeValidator(jobPost.getJobType()) &&
                postedDateValidator(jobPost.getPostedDate()) &&
                companyNameValidator(jobPost.getCompanyName()) &&
                jobDescriptionValidator(jobPost.getJobDescription()) &&
                experienceLevelValidator(jobPost.getExperienceLevel()) &&
                industryValidator(jobPost.getIndustry());
    }

    // Private validator methods for each instance variable.

    private static boolean jobIDValidator(String jobID) {

        // Validate that jobID is not null or empty.
        return jobID != null && !jobID.trim().isEmpty();
    }

    private static boolean jobTitleValidator(String jobTitle) {

        // Validate that jobTitle is not null or empty.
        return jobTitle != null && !jobTitle.trim().isEmpty();
    }

    private static boolean locationValidator(String location) {

        // Validate that location is not null or empty.
        return location != null && !location.trim().isEmpty();
    }

    private static boolean jobTypeValidator(String jobType) {

        // Validate that jobType is one of the expected values.
        if (jobType == null) return false;
        String[] validJobTypes = {"Full-time", "Part-time", "Contract"};
        for (String type : validJobTypes) {
            if (jobType.equals(type)) {
                return true;
            }
        }
        return false;
    }

    private static boolean postedDateValidator(String postedDate) {

        // Validate that postedDate is not null or empty
        // TODO: Add date format validation.
        return postedDate != null && !postedDate.trim().isEmpty();
    }

    private static boolean companyNameValidator(String companyName) {
        // Validate that companyName is not null or empty
        return companyName != null && !companyName.trim().isEmpty();
    }

    private static boolean jobDescriptionValidator(String jobDescription) {

        // Validate that jobDescription is not null or empty.
        return jobDescription != null && !jobDescription.trim().isEmpty();
    }

    private static boolean experienceLevelValidator(String experienceLevel) {

        // Validate that experienceLevel is one of the expected values.
        if (experienceLevel == null) return false;
        String[] validExperienceLevels = {"Intern", "Entry-Level", "Junior-Level", "Mid-Senior-Level", "Senior-Level", "Manager"};
        for (String level : validExperienceLevels) {
            if (experienceLevel.equals(level)) {
                return true;
            }
        }
        return false;
    }

    private static boolean industryValidator(String industry) {

        // Validate that industry is not null or empty.
        return industry != null && !industry.trim().isEmpty();
    }
}
