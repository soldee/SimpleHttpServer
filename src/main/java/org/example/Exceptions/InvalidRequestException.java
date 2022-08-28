package org.example.Exceptions;

public class InvalidRequestException extends Exception {
    public InvalidRequestException(String message, Exception e) {
        super(message, e);
    }
}
