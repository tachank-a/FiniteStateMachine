package ru.gildo.application.alphabet;

import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


// TODO delete this class and package
@Getter
@Deprecated
public class AlphabetContainer {


    private final List<Set<String>> alphabet = new ArrayList<>();

    public void createAlphabet() {
        try (Scanner alphabetScanner = new Scanner(new File("src/main/resources/alphabet.txt"))) {
            while (alphabetScanner.hasNextLine()) {
                Set<String> symbols = new HashSet<>(List.of(alphabetScanner.nextLine().split(" ")));
                alphabet.add(symbols);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        alphabet.add(Set.of("\0"));
    }

}
