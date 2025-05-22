package org.example.autoschool.utils.exception;

public class VerificationException extends RuntimeException {
    public VerificationException() {
        super("Please verify your email");
    }
}
