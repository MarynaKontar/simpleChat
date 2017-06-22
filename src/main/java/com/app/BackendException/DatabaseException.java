package com.app.BackendException;

/**
 * Created by User on 13.06.2017.
 */
public class DatabaseException extends RuntimeException {
    public DatabaseException(Exception cause) {
        super(cause);
    }

    public DatabaseException(String message, Exception cause) {
        super(message, cause);
    }
}
