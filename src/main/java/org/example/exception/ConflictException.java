package org.example.exception;

public class ConflictException extends RuntimeException {

    private final int currentVersion;

    public ConflictException(String message, int currentVersion) {
        super(message);
        this.currentVersion = currentVersion;
    }

    public int getCurrentVersion() {
        return currentVersion;
    }
}
