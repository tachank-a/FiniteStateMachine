package ru.gildo.application.action;

public class ActionHandlerForC implements ActionHandler {
    @Override
    public void actionCall(int actionType) {
        switch (actionType) {
            case 1 -> pushingSymbolOntoStack();
            case 2 -> checkingForDuplicateVariables();
        }
    }

    private void pushingSymbolOntoStack() {

    }

    private void checkingForDuplicateVariables() {

    }
}
