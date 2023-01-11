package ru.gildo.application.machine.exception;

public class InvalidSymbol extends RuntimeException{
    public InvalidSymbol(int stringNumber, int index, char value){
    super("Error at line " + stringNumber + " and " + index + " position " + "invalid value: " + value);
    }
}
