package ru.gildo.application.graph;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import ru.gildo.application.action.type.ActionType;
import ru.gildo.application.graph.node.Node;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class GraphForC implements Graph {
    private Map<ParsingMethod, List<Node>> actionTypeToNodes;

    public void createGraph() {
        try {
            String inPath = "src/main/resources/graph.json";
            ObjectMapper objectMapper = new ObjectMapper();
            List<Node> nodes = objectMapper.readValue(new File(inPath), new TypeReference<>() {
            });
//            nodes.add(new Node(ParsingMethod.CHARACTER_COMPARISON,
//                    ActionType.CHECK_BRACKETS, 10, 11, "\0"));
           actionTypeToNodes = nodes.stream().collect(Collectors.groupingBy(Node::getParsingMethod));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
