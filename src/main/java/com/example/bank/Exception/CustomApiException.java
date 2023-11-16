package com.example.bank.Exception;

import org.springframework.http.HttpStatus;

public class CustomApiException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public CustomApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public CustomApiException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public CustomApiException(String message, Throwable cause, HttpStatus status, String message1) {
        super(message, cause);
        this.status = status;
        this.message = message1;
    }

    public CustomApiException(Throwable cause, HttpStatus status, String message) {
        super(cause);
        this.status = status;
        this.message = message;
    }

    public CustomApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus status, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
