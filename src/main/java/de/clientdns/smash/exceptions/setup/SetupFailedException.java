package de.clientdns.smash.exceptions.setup;

import java.io.Serial;

public class SetupFailedException extends SetupException {

    @Serial
    private static final long serialVersionUID = 9079657993037196984L;

    public SetupFailedException() {
        super();
    }

    public SetupFailedException(String message) {
        super(message);
    }

    public SetupFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SetupFailedException(Throwable cause) {
        super(cause);
    }

    protected SetupFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}