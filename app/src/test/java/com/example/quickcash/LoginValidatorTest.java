package com.example.quickcash;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginValidatorTest {

    private LoginValidator lv;

    @Before
    public void setup() {
        lv = new LoginValidator();
    }


    @Test
    public void testValidEmail () {
        assertTrue(lv.isValidEmail("abc@gmail.com"));
        assertTrue(lv.isValidEmail("ab32177cd@duck.ca"));
        assertTrue(lv.isValidEmail("daniel.curie@yahoo.in"));
        assertTrue(lv.isValidEmail("Mark_Zuckerberg@hotmail.com"));
        assertTrue(lv.isValidEmail("B00942678@dal.ca"));
        assertTrue(lv.isValidEmail("ab350922@smu.ca"));
        assertTrue(lv.isValidEmail("Bill-Gates453@MICROSOFT.COM"));
    }

    @Test
    public void testInvalidEmail() {
        assertFalse(lv.isValidEmail("12Jeff@gmail.com"));
        assertFalse(lv.isValidEmail("@abc.com"));
        assertFalse(lv.isValidEmail("-Bill.Gates.dot.com"));
        assertFalse(lv.isValidEmail("Mark$2024@facebook.com"));
        assertFalse(lv.isValidEmail("B0094267@dal"));
        assertFalse(lv.isValidEmail("ab350922@smu.c"));
        assertFalse(lv.isValidEmail(""));
        assertFalse(lv.isValidEmail("      "));
    }

    @Test
    public void testSelectedRole() {
        assertTrue(lv.haveSelectedRole("Employee"));
        assertTrue(lv.haveSelectedRole("Employer"));
    }

    @Test
    public void testNotSelectedRole() {
        assertFalse(lv.haveSelectedRole("Select Role"));
    }

    @Test
    public void testValidPassword() {
        assertTrue(lv.isValidPassword("mypassword"));
        assertTrue(lv.isValidPassword("1234567"));
        assertTrue(lv.isValidPassword("My@Password"));
        assertTrue(lv.isValidPassword("!mypassword#%@"));
        assertTrue(lv.isValidPassword("passwordWithNumbers0011"));
    }


    @Test
    public void testEmptyPassword() {
        assertFalse(lv.isValidPassword(""));
        assertFalse(lv.isValidPassword("      "));
        assertFalse(lv.isValidPassword(null));
    }

}
