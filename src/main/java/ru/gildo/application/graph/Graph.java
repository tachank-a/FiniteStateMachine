package ru.gildo.application.graph;


import lombok.Getter;
import ru.gildo.application.graph.node.Node;
import ru.gildo.application.parser.NodeParser;
import ru.gildo.application.parser.type.ParsingMethod;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Graph {
    private final List<Node> compareLexemeParsingTypeNodes;
    private final List<Node> characterComparisonParsingTypeNodes;
    
    private final Set<String> modifiers;
    public Graph() {
        List<Node> nodes = NodeParser.parseNodesFromFile();
        Map<ParsingMethod, List<Node>> actionTypeToNodes = nodes.stream().collect(Collectors.groupingBy(Node::getParsingMethod));
        compareLexemeParsingTypeNodes = actionTypeToNodes.get(ParsingMethod.COMPARE_LEXEME);
        characterComparisonParsingTypeNodes = actionTypeToNodes.get(ParsingMethod.CHARACTER_COMPARISON);
        modifiers = compareLexemeParsingTypeNodes.stream().collect(Collectors.groupingBy(Node::getAlphabetSubset)).keySet();
    }
}
