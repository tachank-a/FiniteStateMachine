package ru.gildo.application.action;

import ru.gildo.application.action.type.ActionType;
import ru.gildo.application.graph.node.Node;

public interface ActionHandler {

    void actionCall(ActionType actionType, String lexeme,int position, int stringNumber);

    String analyseLexeme(String lexeme);
}
