package ru.gildo.application.action.exception;

public class BracketsException extends RuntimeException{
    public BracketsException(int position,int stringNumber) {
        super("\nError: expected ']' at line " + stringNumber + " and " + position + " index");
    }
}
