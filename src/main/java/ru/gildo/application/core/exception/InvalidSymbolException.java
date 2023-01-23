package ru.gildo.application.core.exception;

public class InvalidSymbolException extends RuntimeException{
    public InvalidSymbolException(int stringNumber, int index, String value){
    super("\nError: invalid value: " + value+ " at line "  + stringNumber + " and " + index + " index");
    }
}
