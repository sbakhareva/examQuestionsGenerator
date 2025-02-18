package org.skypro.generator.exceptions;

public class IncorrectValueException extends RuntimeException {
    public IncorrectValueException() {
        super();
    }

    public IncorrectValueException(String message) {
        super(message);
    }

    public IncorrectValueException(Throwable t) {
        super(t);
    }
    public IncorrectValueException(String message, Throwable t) {
        super(message, t);
    }
}
