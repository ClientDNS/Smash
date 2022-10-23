package de.clientdns.smash.exceptions.setup;

import java.io.Serial;

public class SetupException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2224353870335021622L;

    public SetupException() {
        super();
    }

    public SetupException(String message) {
        super(message);
    }

    public SetupException(String message, Throwable cause) {
        super(message, cause);
    }

    public SetupException(Throwable cause) {
        super(cause);
    }

    protected SetupException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
