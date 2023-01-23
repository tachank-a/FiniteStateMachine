package ru.gildo.application.graph.node;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.gildo.application.action.type.ActionType;
import ru.gildo.application.graph.ParsingMethod;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Node {
    ParsingMethod parsingMethod;
    ActionType actionType;
    int fromState;
    int toState;
    String alphabetSubset;
}
