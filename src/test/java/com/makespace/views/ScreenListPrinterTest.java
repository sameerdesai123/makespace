package com.makespace.views;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class ScreenListPrinterTest {
    ScreenListPrinter printer = null;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    final List<String> EMPTY_LIST = new ArrayList<>();
    final List<String> NULL_LIST = null;
    List<String> results = new ArrayList<>();
    {
        results.add("C-Cave D-Tower G-Mansion");
        results.add("NO_VACANT_ROOM");
        results.add("INCORRECT_INPUT");
    }
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        this.printer = new ScreenListPrinter(this.NULL_LIST);
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
        this.printer = null;
        outputStreamCaptor.reset();
    }

    @Test
    @DisplayName("Printing null Results")
    void printNull() {
        this.printer.print();
        assertEquals("", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    @DisplayName("Printing Results")
    void print() {
        this.printer = new ScreenListPrinter(this.results);
        this.printer.print();
        assertEquals("C-Cave D-Tower G-Mansion" + System.lineSeparator() +
                "NO_VACANT_ROOM" + System.lineSeparator() +
                "INCORRECT_INPUT", outputStreamCaptor.toString()
                .trim());

    }

    @Test
    @DisplayName("Printing Empty Results")
    void printEmpty() {
        this.printer = new ScreenListPrinter(this.EMPTY_LIST);
        this.printer.print();
        assertEquals("", outputStreamCaptor.toString()
                .trim());
    }
}