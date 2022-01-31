package com.scube.dev.UserService.exception;

import org.springframework.http.HttpStatus;

public class UserServiceException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public UserServiceException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
