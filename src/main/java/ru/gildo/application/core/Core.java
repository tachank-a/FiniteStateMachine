package ru.gildo.application.core;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.gildo.application.action.ActionHandler;
import ru.gildo.application.action.ActionHandlerForC;
import ru.gildo.application.action.type.ActionType;
import ru.gildo.application.core.exception.InvalidSymbolException;
import ru.gildo.application.graph.ParsingMethod;
import ru.gildo.application.graph.node.Node;

import java.util.*;


@RequiredArgsConstructor
public class Core {
    @NonNull
    private final List<Map<Integer, String>> lexemesWithPosition;
    @NonNull
    //todo actionhandler type
    private final ActionHandlerForC actionHandler;

    @NonNull
    private final Map<ParsingMethod, List<Node>> actionTypeToNodes;

    private int currentState = 0;


    public void start() {
        System.out.println(currentState);
        try {
            int stringCount = 1;
            for (Map<Integer, String> lexemeWithPosition : lexemesWithPosition) {

                int finalStringCount = stringCount;
                lexemeWithPosition.forEach((position, lexeme) -> lexemeAnalyse(finalStringCount, position, lexeme));
                stringCount++;
            }
            System.out.println("ANALYSE COMPLETE");
        } catch (InvalidSymbolException ex) {
            ex.printStackTrace();
        }
    }

    private void lexemeAnalyse(int stringNumber, int position, String lexeme) {
        Set<String> modifiers = Set.of("int","double","long","char","short","float");

        List<Node> compareLexeme = actionTypeToNodes.get(ParsingMethod.COMPARE_LEXEME);

        //костыль
        String finalLexeme = lexeme;
        Node nextNode = compareLexeme.stream().filter((node) -> node.getFromState() == currentState && finalLexeme.equals(node.getAlphabetSubset()))
                .findAny().orElse(null);
        if(nextNode == null && modifiers.contains(lexeme)){
            throw new InvalidSymbolException(stringNumber,position,lexeme);
        }
        if(nextNode != null){
            currentState = nextNode.getToState();
            System.out.println(currentState);
        }
        else{

            lexeme = actionHandler.analyseLexeme(lexeme);

            String[] symbols = lexeme.split("");
            for (String symbol :
                    symbols) {
                currentState = characterComparison(stringNumber,position,symbol);
                if(!symbol.equals("\1"))
                    position++;
                System.out.println(currentState);
            }
        }

    }
    private int characterComparison(int stringNumber,int finalPosition, String symbol){
        List<Node> symbolsLexeme = actionTypeToNodes.get(ParsingMethod.CHARACTER_COMPARISON);
       Node  nextNode = symbolsLexeme.stream()
                .filter((node) -> node.getFromState() == currentState && node.getAlphabetSubset()
                        .contains(symbol)).findAny()
                .orElseThrow(() -> new InvalidSymbolException(stringNumber, finalPosition, symbol));
       return nextNode.getToState();
    }

}
//        for (int i = 0; i < lexeme.length; i++) {
//        if (lexeme[i] == '\0') {
//        throw new InvalidSymbolException(stringNumber, position, lexeme[i]);
//        }
//        position++;
//        }
//        actionHandler.actionCall(1);