package com.makespace.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Room implements Comparable<Room>{

    private final int capacity;
    private final String name;
    private final List<List<Date>> bookings;
    private final List<List<Date>> buffers;


    public Room(String name, int capacity) throws ParseException {
        this.capacity = capacity;
        this.name = name;
        this.bookings = new ArrayList<>();

        // Constants
        String DATE_FORMAT = "HH:mm";
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String[][] BUFFER_TIMES = {{"9:00", "9:15"}, {"13:15", "13:45"}, {"18:45", "19:00"}};

        // Buffer initializations
        List<Date> bufferOne = Arrays.asList(dateFormat.parse(BUFFER_TIMES[0][0]), dateFormat.parse(BUFFER_TIMES[0][1]));
        List<Date> bufferTwo = Arrays.asList(dateFormat.parse(BUFFER_TIMES[1][0]), dateFormat.parse(BUFFER_TIMES[1][1]));
        List<Date> bufferThree = Arrays.asList(dateFormat.parse(BUFFER_TIMES[2][0]), dateFormat.parse(BUFFER_TIMES[2][1]));
        this.buffers = Arrays.asList(bufferOne, bufferTwo, bufferThree);
    }

    public boolean bookRoom(Date start, Date end, int capacity) {
        if(!isAvailable(start, end) || capacity > this.capacity)
            return false;
        bookings.add(Arrays.asList(start, end));
        return true;
    }

    public boolean isAvailable(Date start, Date end) {
        if (this.overlapsBufferTime(start, end))
            return false;
        for(List<Date> booking: bookings) {
            if (this.overlaps(booking, start, end))
                return false;
        }
        return true;
    }

    private boolean overlaps(List<Date> booking, Date start, Date end) {
        Date bookingStart = booking.get(0);
        Date bookingEnd = booking.get(1);
        return !(end.getTime() <= bookingStart.getTime() || start.getTime() >= bookingEnd.getTime());
    }

    private boolean overlapsBufferTime(Date start, Date end) {
        for(List<Date> buffer: this.buffers) {
            Date bufferStart = buffer.get(0);
            Date bufferEnd = buffer.get(1);
            if (!(end.getTime() <= bufferStart.getTime() || start.getTime() >= bufferEnd.getTime()))
                return true;
        }
        return false;
    }

    @Override
    public int compareTo(Room other) {
        return this.getCapacity() - other.getCapacity();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }
}
