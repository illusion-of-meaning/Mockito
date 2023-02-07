package org.asherbakov.exception;

public class UserNonUniqueException extends RuntimeException {
    public UserNonUniqueException(String message) {
        super(message);
    }
}
