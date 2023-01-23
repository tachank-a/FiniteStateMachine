package ru.gildo.application;

import ru.gildo.application.action.ActionHandler;
import ru.gildo.application.action.ActionHandlerForC;
import ru.gildo.application.graph.Graph;
import ru.gildo.application.handler.LexemeHandler;
import ru.gildo.application.core.Core;

import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {

        Graph graph = new Graph();


//        System.out.println(graph.getActionTypeToNodes());

        ActionHandler actionHandler = new ActionHandlerForC();

        List<Map<Integer, String>> lexemes = LexemeHandler.createListOfLexemes();

//        System.out.println(lexemes);

        Core core = new Core(lexemes, actionHandler, graph);

        core.start();


    }
}
