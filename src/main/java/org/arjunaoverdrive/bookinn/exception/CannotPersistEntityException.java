package org.arjunaoverdrive.bookinn.exception;

public class CannotPersistEntityException extends RuntimeException{
    public CannotPersistEntityException(String message) {
        super(message);
    }
}
