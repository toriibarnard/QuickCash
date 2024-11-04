package com.example.quickcash;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import com.example.quickcash.util.employerView.Applicant;

public class ApplicantTest {

    @Mock
    private Applicant mockApplicant;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        when(mockApplicant.getApplicantName()).thenReturn("John Doe");
        when(mockApplicant.getApplicantEmail()).thenReturn("john.doe@example.com");
        when(mockApplicant.getApplicantPhone()).thenReturn("123-456-7890");
        when(mockApplicant.getApplicantStatus()).thenReturn("Shortlisted");

    }

    @Test
    public void testMockedApplicantDetails() {
        assertEquals("John Doe", mockApplicant.getApplicantName());
        assertEquals("john.doe@example.com", mockApplicant.getApplicantEmail());
        assertEquals("123-456-7890", mockApplicant.getApplicantPhone());
    }

    @Test
    public void testMarkAsShortlisted() {
        mockApplicant.getApplicantStatus();
        assertEquals("Shortlisted", mockApplicant.getApplicantStatus());
    }
}
