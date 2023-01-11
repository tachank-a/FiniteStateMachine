package ru.gildo.application;

import ru.gildo.application.handler.LexemeHandler;
import ru.gildo.application.machine.FiniteStateMachine;


public class Main {
    public static void main(String[] args) {

        FiniteStateMachine machine = new FiniteStateMachine(LexemeHandler.createListOfLexemes());

        machine.startAnalyse();

    }
}
