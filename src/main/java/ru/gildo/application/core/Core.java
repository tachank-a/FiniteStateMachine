package ru.gildo.application.core;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.gildo.application.action.ActionHandler;
import ru.gildo.application.core.exception.FullComparisonException;
import ru.gildo.application.core.exception.InvalidSymbolException;
import ru.gildo.application.graph.ParsingMethod;
import ru.gildo.application.graph.node.Node;

import java.util.*;


@RequiredArgsConstructor
public class Core {
    @NonNull
    private final List<Map<Integer, String>> lexemesWithPosition;
    @NonNull
    private final ActionHandler actionHandler;

    @NonNull
    private final Map<ParsingMethod, List<Node>> actionTypeToNodes;

    private int currentState = 0;


    public void start() {
        System.out.println("States: ");
        printCurrentState(currentState);
        try {
            int stringCount = 1;
            for (Map<Integer, String> lexemeWithPosition : lexemesWithPosition) {

                int finalStringCount = stringCount;
                lexemeWithPosition.forEach((position, lexeme) -> lexemeAnalyse(finalStringCount, position, lexeme));
                stringCount++;
            }
            System.out.println("\nANALYSE COMPLETE");
        } catch (InvalidSymbolException ex) {
            ex.printStackTrace();
        }
    }

    private void lexemeAnalyse(int stringNumber, int position, String lexeme) {

        //возвращать ноды


        try {
            currentState = fullComparisonOfLexeme(stringNumber, position, lexeme);
            printCurrentState(currentState);
        } catch (FullComparisonException e) {
            lexeme = actionHandler.analyseLexeme(lexeme);
            String[] symbols = lexeme.split("");
            for (String symbol : symbols) {

                currentState = characterComparisonOfLexeme(stringNumber, position, symbol);
                if (!symbol.equals("\1"))
                    position++;
                printCurrentState(currentState);
            }
        }
    }

    private int fullComparisonOfLexeme(int stringNumber, int position, String lexeme) {
        // сделать членом класса
        Set<String> modifiers = Set.of("int", "double", "long", "char", "short", "float");
        List<Node> compareLexeme = actionTypeToNodes.get(ParsingMethod.COMPARE_LEXEME);



        Node nextNode = compareLexeme.stream().filter((node) -> node.getFromState() == currentState && lexeme.equals(node.getAlphabetSubset())).findAny().orElse(null);
        if (nextNode == null && modifiers.contains(lexeme)) {
            throw new InvalidSymbolException(stringNumber, position, lexeme);
        } else if (nextNode == null) {
            throw new FullComparisonException();
        }
        return nextNode.getToState();
    }

    private int characterComparisonOfLexeme(int stringNumber, int finalPosition, String symbol) {
        // сделать членом класа
        List<Node> symbolsLexeme = actionTypeToNodes.get(ParsingMethod.CHARACTER_COMPARISON);
        Node nextNode = symbolsLexeme.stream().filter((node) -> node.getFromState() == currentState && node.getAlphabetSubset().contains(symbol)).findAny().orElseThrow(() -> new InvalidSymbolException(stringNumber, finalPosition, symbol));
        return nextNode.getToState();
    }

    private void printCurrentState(int state) {
        System.out.print(" " + state);
    }

}
