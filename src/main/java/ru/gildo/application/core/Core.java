package ru.gildo.application.core;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.gildo.application.action.ActionHandler;
import ru.gildo.application.core.exception.FullComparisonException;
import ru.gildo.application.core.exception.InvalidSymbolException;
import ru.gildo.application.graph.Graph;
import ru.gildo.application.graph.node.Node;

import java.util.*;


@RequiredArgsConstructor
public class Core {
    @NonNull
    private final List<Map<Integer, String>> lexemesWithPosition;
    @NonNull
    private final ActionHandler actionHandler;

    @NonNull
    private final Graph graph;

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
        } catch (InvalidSymbolException e) {
            e.printStackTrace();
        }
    }

    private void lexemeAnalyse(int stringNumber, int position, String lexeme) {
        Node nextNode;
        try {

            nextNode = getCorrectNodeWithCompareLexemeParsingType(stringNumber, position, lexeme);
            actionHandler.actionCall(nextNode.getActionType(),lexeme,position,stringNumber);
            currentState = nextNode.getToState();
            printCurrentState(currentState);


        } catch (FullComparisonException e) {

            lexeme = actionHandler.setEndSymbolToTheEndOfLexeme(lexeme);
            String[] symbols = lexeme.split("");

            for (String symbol : symbols) {
                nextNode = getCorrectNodeWithCharacterComparisonParsingType(stringNumber, position, symbol);
                actionHandler.actionCall(nextNode.getActionType(),symbol,position,stringNumber);
                currentState = nextNode.getToState();
                if (!symbol.equals("\1"))
                    position++;
                printCurrentState(currentState);
            }

        }
    }

    private Node getCorrectNodeWithCompareLexemeParsingType(int stringNumber, int position, String lexeme) {

        Node nextNode = graph.getCompareLexemeParsingTypeNodes().stream()
                                    .filter((node) -> node.getFromState() == currentState && lexeme.equals(node.getAlphabetSubset()))
                                    .findAny()
                                    .orElse(null);

        if (nextNode == null && graph.getModifiers().contains(lexeme)) {
            throw new InvalidSymbolException(stringNumber, position, lexeme);
        } else if (nextNode == null) {
            throw new FullComparisonException();
        }
        return nextNode;
    }

    private Node getCorrectNodeWithCharacterComparisonParsingType(int stringNumber, int finalPosition, String symbol) {

        return graph.getCharacterComparisonParsingTypeNodes().stream()
                                    .filter((node) -> node.getFromState() == currentState && node.getAlphabetSubset().contains(symbol))
                                    .findAny().orElseThrow(() -> new InvalidSymbolException(stringNumber, finalPosition, symbol));
    }

    private void printCurrentState(int state) {
        System.out.print(" " + state);
    }

}
