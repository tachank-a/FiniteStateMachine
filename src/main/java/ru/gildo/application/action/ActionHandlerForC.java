package ru.gildo.application.action;

import ru.gildo.application.action.type.ActionType;
import ru.gildo.application.graph.node.Node;

import java.util.ArrayList;
import java.util.List;


public class ActionHandlerForC implements ActionHandler {

    private List<String> symbolsVariableBuffer = new ArrayList<>();

    @Override
    public void actionCall(ActionType actionType, String lexeme,int position, int stringNumber) {
        switch (actionType) {



        }
    }



//    private void addSymbolToBuffer(String symbol){
//        if(symbol.equals("\1") && symbolsVariableBuffer.get(symbolsVariableBuffer.size() - 1).equals(",")){
//            isBufferHasComma
//        }
//    }

    public String analyseLexeme(String lexeme){
        char lexemeLastSymbol = lexeme.charAt(lexeme.length() - 1);
        if(!(lexemeLastSymbol == ';' || lexemeLastSymbol == ',' || lexemeLastSymbol == '[' || lexemeLastSymbol == ']' || lexemeLastSymbol == '\0')){
            return lexeme + "\1";
        }else return lexeme;
    }

}
