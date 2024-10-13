package com.example.quickcash;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegistrationValidatorTest {
    RegistrationValidator validator;

    @Before
    public void setup() { validator = new RegistrationValidator(); }

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
        assertTrue(validator.isValidName("william"));
    }

    @Test
    public void checkIsValidRole() {
        assertTrue(validator.isValidRole("employee"));
        assertTrue(validator.isValidRole("employer"));
    }

    @Test
    public void checkIsInvalidEmail() {
        assertFalse(validator.isValidEmail("abc123.gmail.com"));
    }

    @Test
    public void checkIsInvalidPhone() {
        assertFalse(validator.isValidPhone("4295235"));
    }

    @Test
    public void checkIsInvalidName() {
        assertFalse(validator.isValidName("will09"));
    }

    @Test
    public void checkIsInvalidRole() {
        assertFalse(validator.isValidRole("Select Role"));
    }
}