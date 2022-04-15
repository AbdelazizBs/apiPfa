package com.azzouz.apipfa.entities;

public class AuthenticationBean {
    private String message;

    public AuthenticationBean(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("Bienvenue dans backend [message=%s]", message);
    }
}