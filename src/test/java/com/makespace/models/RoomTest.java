package com.makespace.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    Room room;
    private Room C_CAVE;
    private Room D_TOWER;
    private Room G_MANSION;

    DateFormat dateFormat = new SimpleDateFormat("HH:mm");
    String[][] BUFFER_TIMES = {{"9:00", "9:15"}, {"13:15", "13:45"}, {"18:45", "19:00"}};

    @BeforeEach
    void setup() throws ParseException {
        C_CAVE = new Room("C-Cave", 3);
        D_TOWER = new Room("D-Tower", 7);
        G_MANSION = new Room("G-Mansion", 20);
    }

    @AfterEach
    void tearDown() {
        this.room = null;
    }

    @Test
    @DisplayName("Getting Capacity of room")
    void getCapacity() {
        this.room = G_MANSION;
        assertEquals(20, this.room.getCapacity());
    }

    @Test
    @DisplayName("Getting name of a room")
    void getName() {
        this.room = G_MANSION;
        assertEquals("G-Mansion", this.room.getName());
    }

    @Test
    @DisplayName("Booking When capacity is exceeded")
    void bookRoomExceedCapacity() throws ParseException {
        this.room = C_CAVE;
        assertFalse(this.room.bookRoom(dateFormat.parse("10:00"), dateFormat.parse("10:30"), 10));
    }

    @Test
    @DisplayName("Booking When Empty")
    void bookRoomEmpty() throws ParseException {
        this.room = C_CAVE;
        assertTrue(this.room.bookRoom(dateFormat.parse("10:00"), dateFormat.parse("10:30"), 2));
    }

    @Test
    @DisplayName("Booking When Full")
    void bookRoomFull() throws ParseException {
        this.room = C_CAVE;
        this.room.bookRoom(dateFormat.parse("10:00"), dateFormat.parse("10:30"), 2);
        assertFalse(this.room.bookRoom(dateFormat.parse("10:00"), dateFormat.parse("10:30"), 3));
    }


    @Test
    @DisplayName("Availability Check")
    void isAvailable() throws ParseException {
        this.room = D_TOWER;
        assertTrue(this.room.isAvailable(dateFormat.parse("10:00"), dateFormat.parse("10:30")));
    }

    @Test
    @DisplayName("Not available")
    void isNotAvailable() throws ParseException {
        this.room = D_TOWER;
        this.room.bookRoom(dateFormat.parse("10:00"), dateFormat.parse("10:30"), 4);
        assertFalse(this.room.isAvailable(dateFormat.parse("10:00"), dateFormat.parse("10:30")));
    }


    @Test
    @DisplayName("False when time overlaps with buffer")
    void overlapsBufferTime() throws ParseException {
        this.room = D_TOWER;
        assertFalse(this.room.isAvailable(dateFormat.parse(BUFFER_TIMES[0][0]), dateFormat.parse(BUFFER_TIMES[0][1])));
    }
}