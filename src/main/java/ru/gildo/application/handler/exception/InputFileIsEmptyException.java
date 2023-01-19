package ru.gildo.application.handler.exception;

public class InputFileIsEmptyException extends RuntimeException{
    public InputFileIsEmptyException() {
        super("Lexemes not found in input file");
    }
}
