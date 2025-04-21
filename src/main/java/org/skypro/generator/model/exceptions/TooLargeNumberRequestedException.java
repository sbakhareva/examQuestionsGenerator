package org.skypro.generator.model.exceptions;

import java.io.IOException;

public class TooLargeNumberRequestedException extends RuntimeException {

    public TooLargeNumberRequestedException() {
        super();
    }

    public TooLargeNumberRequestedException(String message) {
        super(message);
    }

    public TooLargeNumberRequestedException(Throwable t) {
        super(t);
    }
    public TooLargeNumberRequestedException(String message, Throwable t) {
        super(message, t);
    }
}
