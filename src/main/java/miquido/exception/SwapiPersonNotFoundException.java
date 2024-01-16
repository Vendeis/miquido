package miquido.exception;

public class SwapiPersonNotFoundException extends RuntimeException {
    public SwapiPersonNotFoundException(Integer id) {
        super("Person with Id " + id + " was not found in Swapi database");
    }
}
