package com.personal.amacloneserver.exception;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException() {
        super("Invalid Login Credentials");
    }
}
