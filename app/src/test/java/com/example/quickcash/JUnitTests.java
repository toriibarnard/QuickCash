package com.example.quickcash;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JUnitTests {
    CredentialValidator validator;

    @Before
    public void setup() { validator = new CredentialValidator(); }

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
        assertFalse(validator.isValidRole("select your role"));
    }
}