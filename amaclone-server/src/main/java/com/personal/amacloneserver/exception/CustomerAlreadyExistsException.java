package com.personal.amacloneserver.exception;

public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException() {
        super("Customer already exists");
    }
}
