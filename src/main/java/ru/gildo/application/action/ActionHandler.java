package ru.gildo.application.action;

import ru.gildo.application.action.type.ActionType;

public interface ActionHandler {

    void actionCall(ActionType actionType, String lexeme,int position, int stringNumber);

    String setEndSymbolToTheEndOfLexeme(String lexeme);
}
