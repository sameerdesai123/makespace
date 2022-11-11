package com.makespace.controller;

import com.makespace.Exceptions.InputMissingException;

import java.io.*;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws InputMissingException, IOException, ParseException {
        String inputFilePath = args[0];
        if(inputFilePath == null)
            throw new InputMissingException();
        // To execute all requests
        new Booking().execute(inputFilePath);
    }
}
