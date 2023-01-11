package ru.gildo.application.filemanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LexemeHandler {
    private static final String inPath = "src/main/resources/input.txt";

    private static int positionInInput = 1;

    public static List<Map<Integer, String>> createListOfLexemes() {

        List<String> inputArray = getInputArrayFromFile();

        List<Map<Integer, String>> lexemeMaps = new ArrayList<>();

        Map<Integer, String> lexemesWithPosition;

        for (String string : inputArray) {
            lexemesWithPosition = getLexemesWithPosition(string);
            lexemeMaps.add(lexemesWithPosition);
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
        input.set(input.size() - 1, input.get(input.size() - 1) + "\0");

        return input;
    }

    private static Map<Integer, String> getLexemesWithPosition(String stringOfLexemes) {
        int spaceCount = 0, numberOfLexeme = 0;

        char[] symbolsWithSpaces = stringOfLexemes.toCharArray();

        String[] lexemesWithoutSpaces = getLexemesWithoutSpaces(stringOfLexemes);

        Map<Integer, String> result = new LinkedHashMap<>();

        for (int i = 0; i < symbolsWithSpaces.length; i++) {

            if (symbolsWithSpaces[i] != ' ') {
                positionInInput += spaceCount;
                result.put(positionInInput, lexemesWithoutSpaces[numberOfLexeme]);
                positionInInput += lexemesWithoutSpaces[numberOfLexeme].length() + 1;

                i += lexemesWithoutSpaces[numberOfLexeme].length();
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
        return stringOfLexemes.replaceAll("\\s+", " ").trim().split(" ");
    }

}
