package com.personal.amacloneserver.exception;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException() {
        super("Customer not found!");
    }
}
