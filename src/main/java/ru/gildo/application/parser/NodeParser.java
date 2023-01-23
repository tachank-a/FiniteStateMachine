package ru.gildo.application.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import ru.gildo.application.graph.node.Node;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class NodeParser {

    private String inPath = "src/main/resources/graph.json";

    public static List<Node> parseNodesFromFile(){
        try {
            String inPath = "src/main/resources/graph.json";
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(inPath), new TypeReference<>() {});
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
