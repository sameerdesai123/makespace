package com.makespace.Exceptions;

public class InputMissingException extends Throwable {
    @Override
    public String toString() {
        return "Input File destination not provided";
    }
}
