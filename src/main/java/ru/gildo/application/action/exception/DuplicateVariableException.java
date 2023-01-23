package ru.gildo.application.action.exception;

public class DuplicateVariableException extends RuntimeException{
    public DuplicateVariableException(String variable, int index, int stringNumber) {
        super("\nError: redefinition of '" + variable + "' "
                + "at line " + stringNumber + " and " + index + " index");
    }
}
