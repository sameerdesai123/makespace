package com.makespace.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* @immutable
* @Uses Room class
*/
public class Request {
    private final DateFormat dateFormat = new SimpleDateFormat("HH:mm");
    private final String task;
    private final Date start, end;
    private final int meetingCapacityNeeded;

    public Request(String requestString) throws ParseException {
        String[] requestParts = requestString.split(" ");
        this.task = requestParts[0];
        this.start = dateFormat.parse(requestParts[1]);
        this.end = dateFormat.parse(requestParts[2]);
        this.meetingCapacityNeeded = requestParts.length > 3? Integer.parseInt(requestParts[3]) : -1;
    }

    public String bookRoom(List<Room> rooms) {
        String result = "NO_VACANT_ROOM";
        for(Room room: rooms) {
            if (room.bookRoom(start, end, meetingCapacityNeeded)) {
                result = room.getName();
                break;
            }
        }
        return result;
    }

    public String queryVacancy(List<Room> rooms) {
        String result = "NO_VACANT_ROOM";
        List<String> availableRooms = new ArrayList<>();
        for(Room room: rooms) {
            if (room.isAvailable(start, end))
                availableRooms.add(room.getName());
        }
        if(availableRooms.size() > 0) {
            result = String.join(" ", availableRooms);
        }
        return result;
    }

    public boolean periodIsValid() {
        if (this.end.getTime() <= this.start.getTime())
            return false;
        int startMin = Integer.parseInt(this.start.toString().split(":")[1]);
        int endMin = Integer.parseInt(this.end.toString().split(":")[1]);
        return startMin % 15 == 0 && endMin % 15 == 0;
    }

    public String getTask() { return task; }

}
