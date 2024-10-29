package com.example.quickcash;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class ApplicantTest {

    @Mock
    private Applicant mockApplicant;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        when(mockApplicant.getApplicantID()).thenReturn("12345");
        when(mockApplicant.getApplicantName()).thenReturn("John Doe");
        when(mockApplicant.getApplicantEmail()).thenReturn("john.doe@example.com");
        when(mockApplicant.getApplicantPhone()).thenReturn("123-456-7890");
    }

    @Test
    public void testMockedApplicantDetails() {
        assertEquals("12345", mockApplicant.getApplicantID());
        assertEquals("John Doe", mockApplicant.getApplicantName());
        assertEquals("john.doe@example.com", mockApplicant.getApplicantEmail());
        assertEquals("123-456-7890", mockApplicant.getApplicantPhone());
    }

    @Test
    public void testMarkAsShortlisted() {
        mockApplicant.markAsShortlisted();
        assertEquals("Shortlisted", mockApplicant.getEmployerStatus());
    }

    @Test
    public void testMarkAsRejected() {
        mockApplicant.markAsRejected();
        assertEquals("Rejected", mockApplicant.getEmployerStatus());
    }
}


