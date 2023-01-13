package de.clientdns.smash.api.exceptions;

import java.io.Serial;

public class SetupFailedException extends Exception {

    @Serial
    private static final long serialVersionUID = 7953975321791470922L;

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
