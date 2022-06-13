package com.ana1.helloworld.exception;

public class LanguageMessageNotFound extends RuntimeException {
    public LanguageMessageNotFound(String message) {
        //super(message);
        super("My own exception");
    }
}
