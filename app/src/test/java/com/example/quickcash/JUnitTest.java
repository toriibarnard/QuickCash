package com.example.quickcash;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JUnitTest {
    CredentialValidator validator;

    @Before
    public void setup() { validator = new CredentialValidator(); }

    @Test
    public void checkIsValidEmail() { assertTrue(validator.isValidEmail("abc123@gmail.com")); }
}