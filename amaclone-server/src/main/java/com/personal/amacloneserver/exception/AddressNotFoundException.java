package com.personal.amacloneserver.exception;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException() {
        super("Address not found!");
    }
}
