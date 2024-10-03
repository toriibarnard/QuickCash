package com.example.quickcash;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// This class is used to validate login credentials
public class LoginValidator {

    // This method checks if a valid email address is entered
    public boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z]+[0-9.\\-_a-zA-Z]*@[a-zA-Z]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        // email field should not be empty and it should match the specified pattern
        return !email.isEmpty() && matcher.matches();
    }

    // This method checks if the password field is not empty
    public boolean isValidPassword(String password) {
        return password != null && !password.trim().isEmpty();
    }

    // This method checks if a valid role is selected
    public boolean haveSelectedRole(String role) {
        return (role.equals("Employee") || role.equals("Employer"));
    }
}
