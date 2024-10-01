package com.example.quickcash;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginValidator {

    public LoginValidator() {}

    public boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z]+[0-9.\\-_a-zA-Z]*@[a-zA-Z]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return !email.isEmpty() && matcher.matches();
    }

    public boolean isValidPassword(String password) {
        return password != null && !password.trim().isEmpty();
    }

    public boolean haveSelectedRole(String role) {
        return (role.equals("Employee") || role.equals("Employer"));
    }
}
