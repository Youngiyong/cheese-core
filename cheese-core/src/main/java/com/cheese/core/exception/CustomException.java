package com.cheese.core.exception;

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private CheeseCode cheeseCode;

    public CustomException(CheeseCode cheeseCode) {
        super(cheeseCode.getMessage());
        this.cheeseCode = cheeseCode;
    }

    public CheeseCode getErrorCode() {
        return cheeseCode;
    }
}