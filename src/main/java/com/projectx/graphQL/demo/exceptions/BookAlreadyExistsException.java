package com.projectx.graphQL.demo.exceptions;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String msg) {
        super(msg);
    }
}
