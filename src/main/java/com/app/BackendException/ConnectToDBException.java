package com.app.BackendException;


/**
 * Created by User on 10.06.2017.
 */
public class ConnectToDBException extends RuntimeException {
    public ConnectToDBException(Exception cause) {
        super(cause);
    }
}
