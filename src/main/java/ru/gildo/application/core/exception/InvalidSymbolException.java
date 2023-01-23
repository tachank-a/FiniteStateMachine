package ru.gildo.application.core.exception;

public class InvalidSymbolException extends RuntimeException{
    public InvalidSymbolException(int stringNumber, int index, String value){
    super("Error at line " + stringNumber + " and " + index + " position " + "invalid value: " + value);
    }
}
