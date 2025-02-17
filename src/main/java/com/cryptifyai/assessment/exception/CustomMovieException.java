package com.cryptifyai.assessment.exception;

public class CustomMovieException extends Exception{
    public CustomMovieException(String message) {
        super(message);
    }

    public CustomMovieException() {
        super();
    }

    public CustomMovieException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomMovieException(Throwable cause) {
        super(cause);
    }

    protected CustomMovieException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
