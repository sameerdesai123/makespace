package com.makespace.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {
    private Booking booking;
    private final String[] requests = {"VACANCY 23:30 23:30" ,
            "VACANCY 10:00 12:00" ,
            "BOOK 11:00 11:45 2" ,};
    private final String[] expectedResults = {"INCORRECT_INPUT",
            "C-Cave D-Tower G-Mansion",
            "C-Cave"};

    @BeforeEach
    void setUp() throws IOException, ParserConfigurationException, ParseException, SAXException {
        this.booking = new Booking();
    }

    @AfterEach
    void tearDown() {
        this.booking = null;
    }

    @Test
    @DisplayName("Fail if start equal to end time")
    void generateResultsEqualTimes() throws ParseException {
        this.booking.generateResults(requests[0]);
        assertEquals(Collections.singletonList(expectedResults[0]), this.booking.getResults());
    }

    @Test
    @DisplayName("Success Vacancy")
    void generateResults() throws ParseException {
        this.booking.generateResults(requests[1]);
        assertEquals(Collections.singletonList(expectedResults[1]), this.booking.getResults());
    }

    @Test
    @DisplayName("Success Booking")
    void generateResultsBooking() throws ParseException {
        this.booking.generateResults(requests[2]);
        assertEquals(Collections.singletonList(expectedResults[2]), this.booking.getResults());
    }

    @Test
    @DisplayName("Show Results")
    void getResults() throws ParseException {
        this.booking.generateResults(requests[0]);
        this.booking.generateResults(requests[1]);
        this.booking.generateResults(requests[2]);
        assertEquals(Arrays.asList(expectedResults), this.booking.getResults());
    }
}