package ru.gildo.application.core;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.gildo.application.action.ActionHandler;
import ru.gildo.application.core.exception.InvalidSymbolException;

import java.util.*;

@RequiredArgsConstructor
public class Core {
    @NonNull
    private final List<Map<Integer, String>> lexemesWithPosition;
    @NonNull
    private final ActionHandler actionHandler;
    private int activeState = 0;

    public void startAnalyse() {
        try {
            int stringCount = 1;
            for (Map<Integer, String> lexemeWithPosition :
                    lexemesWithPosition) {

                int finalStringCount = stringCount;
                lexemeWithPosition.forEach((position, lexeme) -> TEMPcheckValue(finalStringCount, position, lexeme.toCharArray()));
                stringCount++;
            }
        } catch (InvalidSymbolException ex) {
            ex.printStackTrace();
        }
    }

    public void TEMPcheckValue(int stringNumber, Integer position, char[] lexeme) {
//        for (int i = 0; i < lexeme.length; i++) {
//            if (lexeme[i] == 'm') {
//                throw new InvalidSymbolException(stringNumber, position, lexeme[i]);
//            }
//            position++;
//        }
        actionHandler.actionCall(1);
    }

}