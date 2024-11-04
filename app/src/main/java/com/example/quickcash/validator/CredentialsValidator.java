package com.example.quickcash.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// This class is used to validate all credentials entered by user
public class CredentialsValidator {
    // This method checks if a valid email address is entered
    public boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z]+[0-9.\\-_a-zA-Z]*@[a-zA-Z]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return !email.isEmpty() && matcher.matches();
    }

    public boolean isValidPhone(String phone) {
        return phone.matches("^[0-9]{10,}$");
    }

    public boolean isValidName(String name) {
        return name.matches("^[A-Za-z\\s]+$");
    }

    public boolean isValidPassword(String password) {
        return password.matches("^[A-Za-z0-9!#$%&*+.<=>?@^_~]+$");
    }

    // This method checks if the password field is not empty
    public boolean hasEnteredPassword(String password) {
        return password != null && !password.trim().isEmpty();
    }

    public boolean isValidRole(String role) {
        return !role.equals("Select Role");
    }

    public boolean isFileUploaded(String file) {
        return !file.equals("No file selected");
    }
}
