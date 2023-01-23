package ru.gildo.application.action;


import ru.gildo.application.action.type.ActionType;
import ru.gildo.application.core.exception.ActionException;

import java.util.ArrayList;
import java.util.List;


public class ActionHandlerForC implements ActionHandler {

    private final List<String> variableBuffer = new ArrayList<>();
    private StringBuilder symbolBuffer = new StringBuilder();

    private boolean isOpenBracketPresent = false;

    @Override
    public void actionCall(ActionType actionType, String symbol, int position, int stringNumber) {
        switch (actionType) {
            case PUSH_SYMBOL_IN_SYMBOL_BUFFER -> pushSymbolInSymbolBuffer(symbol);
            case CHECK_VARIABLE_BUFFER_FOR_DUPLICATE -> checkVariableBufferForDuplicate(position, stringNumber);
            case PUSH_OPEN_BRACKET_IN_BUFFER -> isOpenBracketPresent = true;
            case PUSH_CLOSE_BRACKET_IN_BUFFER -> isOpenBracketPresent = false;
            case CHECK_BRACKETS_BUFFER -> checkBracketsBuffer(position,stringNumber);
        }
    }


    private void pushSymbolInSymbolBuffer(String symbol) {
        if(!symbol.equals("\1"))
            symbolBuffer.append(symbol);
    }

    private void checkVariableBufferForDuplicate(int position, int stringNumber) {
        if (variableBuffer.contains(symbolBuffer.toString())) {
            throw new ActionException("Error: redefinition of '" + symbolBuffer + "' "
                    + "at line " + stringNumber + " and " + (position - symbolBuffer.length()) + " index" );
        } else {
            variableBuffer.add(symbolBuffer.toString());
            symbolBuffer = new StringBuilder();
        }
    }

    private void checkBracketsBuffer(int position, int stringNumber) {
        if (isOpenBracketPresent)
            throw new ActionException("Error: expected ']' at line " + stringNumber + " and " + position + " index");
    }

    @Override
    public String analyseLexeme(String lexeme) {
        char lexemeLastSymbol = lexeme.charAt(lexeme.length() - 1);
        if (!(lexemeLastSymbol == ';' || lexemeLastSymbol == ',' || lexemeLastSymbol == '[' || lexemeLastSymbol == ']' || lexemeLastSymbol == '\0')) {
            return lexeme + "\1";
        } else return lexeme;
    }

}
