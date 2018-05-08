package com.smph.ar.shared.exception;

public class NotEnoughPointsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotEnoughPointsException(String msg) {
        super(msg);
    }

}
