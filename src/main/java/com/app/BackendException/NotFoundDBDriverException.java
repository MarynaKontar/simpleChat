package com.app.BackendException;

/**
 * Created by User on 10.06.2017.
 */
public class NotFoundDBDriverException extends RuntimeException {
    public NotFoundDBDriverException(Exception cause) {
        super(cause);
    }
}
