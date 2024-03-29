package ru.gildo.application.handler;

import ru.gildo.application.handler.exception.InputFileIsEmptyException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LexemeHandler {
    private static final String inPath = "src/main/resources/input.txt";

    private static int positionInInput = 1;

    public static List<Map<Integer, String>> createListOfLexemes() {
        List<Map<Integer, String>> lexemeMaps = new ArrayList<>();
        try {
            List<String> inputArray = getInputArrayFromFile();
            Map<Integer, String> lexemesWithPosition;

            for (String stringOfLexemes : inputArray) {
                lexemesWithPosition = getLexemesWithPosition(stringOfLexemes);
                lexemeMaps.add(lexemesWithPosition);
            }

            addEndSymbol(lexemeMaps);
        } catch (InputFileIsEmptyException e) {
            e.printStackTrace();
        }
        return lexemeMaps;
    }

    private static List<String> getInputArrayFromFile() {

        List<String> input = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(inPath))) {
            while (scanner.hasNext()) {
                input.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (input.size() == 0) {
            throw new InputFileIsEmptyException();
        }
        return input;
    }

    private static Map<Integer, String> getLexemesWithPosition(String stringOfLexemes) {
        int spaceCount = 0, numberOfLexeme = 0;

        char[] symbolsWithSpaces = stringOfLexemes.toCharArray();

        String[] lexemesWithoutSpaces = getLexemesWithoutSpaces(stringOfLexemes);

        Map<Integer, String> result = new LinkedHashMap<>();
        if(symbolsWithSpaces.length == 0){
            positionInInput++;
            return result;
        }

        for (int i = 0; i < symbolsWithSpaces.length; i++) {

            if (symbolsWithSpaces[i] != ' ') {
                positionInInput += spaceCount;
                result.put(positionInInput, lexemesWithoutSpaces[numberOfLexeme]);
                positionInInput += lexemesWithoutSpaces[numberOfLexeme].length();

                i += lexemesWithoutSpaces[numberOfLexeme].length() - 1;
                numberOfLexeme++;
                spaceCount = 0;
            } else {
                spaceCount++;
            }
        }
        positionInInput += spaceCount;
        return result;
    }

    private static String[] getLexemesWithoutSpaces(String stringOfLexemes) {
        return stringOfLexemes.replaceAll(";", " ; ")
                .replaceAll(",", " , ")
                .replaceAll("\\[", " \\[ ")
                .replaceAll("]", " ] ")
                .replaceAll("\\s+", " ")
                .trim().split(" ");
    }


    private static void addEndSymbol(List<Map<Integer, String>> lexemeMaps) {
        Map<Integer, String> mapFromEnd = lexemeMaps.get(lexemeMaps.size() - 1);
        int count = 1;

        for (Map.Entry<Integer, String> map : mapFromEnd.entrySet()) {
            if (count == mapFromEnd.size()) {
                map.setValue(map.getValue() + "\0");
            }
            count++;
        }
    }


}
