package com.java.basics.exception;

public class JWTSecurityException extends RuntimeException
{

    public JWTSecurityException(String token, String message) {
        super(String.format("Failed for [%s]: %s", token, message));
    }
}