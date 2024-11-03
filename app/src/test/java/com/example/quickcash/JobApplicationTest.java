package com.example.quickcash;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JobApplicationTest {
    CredentialsValidator validator;

    @Before
    public void setup() { validator = new CredentialsValidator(); }

    @Test
    public void checkIsValidEmail() {
        assertTrue(validator.isValidEmail("abc123@gmail.com"));
    }

    @Test
    public void checkIsValidPhone() {
        assertTrue(validator.isValidPhone("5064295235"));
    }

    @Test
    public void checkIsValidName() {
        assertTrue(validator.isValidName("Kayhan"));
    }

    @Test
    public void checkIsFileUploaded() {
        assertTrue(validator.isFileUploaded("resume.pdf"));
    }
}
