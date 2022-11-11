package com.makespace.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {
    private Request request;
    private Room room;
    private Request INCORRECT_REQ;
    private Request BOOK_REQ;
    private Request VACANCY_REQ;
    private List<Room> rooms;
    private final String NO_VACANT_ROOM = "NO_VACANT_ROOM";
    private final String INCORRECT_INPUT = "INCORRECT_INPUT";

    @BeforeEach
    void setUp() throws ParseException {
        room = new Room("TEST-ROOM", 20);
        INCORRECT_REQ = new Request("BOOK 10:02 10:32 2");
        BOOK_REQ = new Request("BOOK 10:00 10:30 3");
        VACANCY_REQ = new Request("VACANCY 10:00 10:30");
        rooms = new ArrayList<>();
        rooms.add(room);
    }

    @AfterEach
    void tearDown() {
        this.room = null;
    }

    @Test
    @DisplayName("Success")
    void bookRoom() {
        this.request = BOOK_REQ;
        assertEquals(this.room.getName(), this.request.bookRoom(rooms));
    }

    @Test
    @DisplayName("Not possible")
    void bookRoomFail() throws ParseException {
        this.request = new Request("BOOK 10:00 10:30 100");
        assertEquals(NO_VACANT_ROOM, this.request.bookRoom(rooms));
    }

    @Test
    @DisplayName("Available")
    void queryVacancy() {
        this.request = VACANCY_REQ;
        assertEquals(this.room.getName(), this.request.queryVacancy(this.rooms));
    }

    @Test
    @DisplayName("Period Validity - Not Valid")
    void periodIsValid() {
        this.request = INCORRECT_REQ;
        assertEquals(false, this.request.periodIsValid());
    }

    @Test
    @DisplayName("View Request Name (BOOK or VACANCY)")
    void getTask() {
        this.request = VACANCY_REQ;
        assertEquals("VACANCY", this.request.getTask());
    }
}