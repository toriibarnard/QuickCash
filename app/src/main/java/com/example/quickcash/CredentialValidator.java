package com.example.quickcash;

public class CredentialValidator {

    protected boolean isValidEmail(String email){
        return email.matches("^[A-Za-z0-9+.]+@[A-Za-z0-9]+.[A-Za-z][A-Za-z]+$");
    }
}
