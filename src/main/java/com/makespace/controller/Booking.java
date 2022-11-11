package com.makespace.controller;

import com.makespace.models.Request;
import com.makespace.models.Room;
import com.makespace.views.ScreenListPrinter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Booking {
    // Constants
    private final String BOOK = "BOOK";
    private final String VACANCY = "VACANCY";
    private final Room C_CAVE = new Room("C-Cave", 3);
    private final Room D_TOWER = new Room("D-Tower", 7);
    private final Room G_MANSION = new Room("G-Mansion", 20);
    private final List<Room> rooms;
    private final List<String> results = new ArrayList<>();

    // initialize requests and rooms in constructor
    public Booking() throws  ParseException {
        // Constants (used only once so local)
        this.rooms = Arrays.asList(C_CAVE, D_TOWER, G_MANSION);
    }

    public void execute(String inputPath) throws IOException, ParseException {
        List<String> requests = loadFileAsList(inputPath);
        for(String req: requests)
            generateResults(req);
        // Print the results
        new ScreenListPrinter(getResults()).print();
    }

    public void generateResults(String requestString) throws ParseException {
        String INCORRECT_INPUT = "INCORRECT_INPUT";
        Request request = new Request(requestString);
        if (!request.periodIsValid())
            results.add(INCORRECT_INPUT);
        else {
            switch (request.getTask()) {
                case BOOK: results.add(request.bookRoom(rooms));
                    break;
                case VACANCY: results.add(request.queryVacancy(rooms));
                    break;
                default: results.add(INCORRECT_INPUT);
                    break;
            }
        }
    }

    private ArrayList<String> loadFileAsList(String pathToInputFile) throws IOException {
        File inputFile = new File(pathToInputFile);
        FileReader fr = new FileReader(inputFile);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<String> lines = new ArrayList<>();
        String line;
        while((line = br.readLine()) != null) { lines.add(line); }
        fr.close();
        return lines;
    }

    public List<String> getResults() {
        return this.results;
    }
}
