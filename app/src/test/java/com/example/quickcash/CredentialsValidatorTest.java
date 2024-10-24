package com.example.quickcash;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CredentialsValidatorTest {
    CredentialsValidator validator;

    @Before
    public void setup() { validator = new CredentialsValidator(); }


    // Test when a valid email is entered
    @Test
    public void testValidEmail () {
        assertTrue(validator.isValidEmail("abc@gmail.com"));
        assertTrue(validator.isValidEmail("ab32177cd@duck.ca"));
        assertTrue(validator.isValidEmail("daniel.curie@yahoo.in"));
        assertTrue(validator.isValidEmail("Mark_Zuckerberg@hotmail.com"));
        assertTrue(validator.isValidEmail("B00942678@dal.ca"));
        assertTrue(validator.isValidEmail("ab350922@smu.ca"));
        assertTrue(validator.isValidEmail("Bill-Gates453@MICROSOFT.COM"));
    }

    // Test when an invalid email is entered
    @Test
    public void testInvalidEmail() {
        assertFalse(validator.isValidEmail("12Jeff@gmail.com"));
        assertFalse(validator.isValidEmail("@abc.com"));
        assertFalse(validator.isValidEmail("-Bill.Gates.dot.com"));
        assertFalse(validator.isValidEmail("Mark$2024@facebook.com"));
        assertFalse(validator.isValidEmail("B0094267@dal"));
        assertFalse(validator.isValidEmail("ab350922@smu.c"));
        assertFalse(validator.isValidEmail(""));
        assertFalse(validator.isValidEmail("      "));
    }

    // Test when a valid password is entered
    @Test
    public void testValidPassword() {
        assertTrue(validator.isValidPassword("mypassword"));
        assertTrue(validator.isValidPassword("1234567"));
        assertTrue(validator.isValidPassword("My@Password"));
        assertTrue(validator.isValidPassword("!mypassword#%@"));
        assertTrue(validator.isValidPassword("passwordWithNumbers0011"));
    }


    // Test when the password field is empty
    @Test
    public void testEmptyPassword() {
        assertFalse(validator.hasEnteredPassword(""));
        assertFalse(validator.hasEnteredPassword("      "));
        assertFalse(validator.hasEnteredPassword(null));
    }

    @Test
    public void testValidPhone() {
        assertTrue(validator.isValidPhone("5064295235"));
    }

    @Test
    public void testInvalidPhone() {
        assertFalse(validator.isValidPhone("4295235"));
    }

    @Test
    public void testValidName() {
        assertTrue(validator.isValidName("william"));
        assertTrue(validator.isValidName("John Doe"));
    }

    @Test
    public void testInvalidName() {
        assertFalse(validator.isValidName("will09"));
        assertFalse(validator.isValidName("76445william"));
    }

    // Test when a role is selected
    @Test
    public void testSelectedRole() {
        assertTrue(validator.isValidRole("Employee"));
        assertTrue(validator.isValidRole("Employer"));
    }

    // Test when a role is not selected
    @Test
    public void testNotSelectedRole() {
        assertFalse(validator.isValidRole("Select Role"));
    }
}