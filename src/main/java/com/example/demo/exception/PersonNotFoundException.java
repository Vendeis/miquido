package com.example.demo.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String name) {
        super("Person with name " + name + " was not found in database");
    }
}
