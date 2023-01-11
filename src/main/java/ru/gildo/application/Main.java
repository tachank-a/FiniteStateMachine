package ru.gildo.application;

import ru.gildo.application.handler.LexemeHandler;
import ru.gildo.application.core.Core;


public class Main {
    public static void main(String[] args) {

        Core machine = new Core(LexemeHandler.createListOfLexemes());

        machine.startAnalyse();

    }
}
