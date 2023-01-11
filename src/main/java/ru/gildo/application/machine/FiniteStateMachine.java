package ru.gildo.application.machine;


import lombok.AllArgsConstructor;
import ru.gildo.application.machine.exception.InvalidSymbolException;

import java.util.*;


@AllArgsConstructor
public class FiniteStateMachine {

    private final List<Map<Integer, String>> lexemesWithPosition;


    public void startAnalyse() {
        try {
            int stringCount = 1;
            for (Map<Integer, String> lexemeWithPosition :
                    lexemesWithPosition) {
                System.out.println(lexemeWithPosition);

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
//                throw new InvalidSymbol(stringNumber, position, lexeme[i]);
//            }
//            position++;
//        }
    }

}
