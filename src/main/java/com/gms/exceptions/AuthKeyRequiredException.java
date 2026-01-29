package com.gms.exceptions;

public class AuthKeyRequiredException extends RuntimeException {
    public AuthKeyRequiredException(String message) {
        super(message);
    }
}
