package org.example.autoschool.utils.exception;

public class NoTokenProvided extends RuntimeException {
    public NoTokenProvided() {
        super("No token provided");
    }
}
