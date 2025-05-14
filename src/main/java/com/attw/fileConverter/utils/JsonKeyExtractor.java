package com.attw.fileConverter.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonKeyExtractor {
    public static List<String> extractJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);
        List<String> keys = new ArrayList<>();
        extractKeysRecursively(node,"",keys);
        return keys;
    }

    private static void extractKeysRecursively(JsonNode node, String prefix, List<String> keys) {
        if (node.isObject()) {
            node.fieldNames().forEachRemaining(field -> {
                String curentPath = prefix.isEmpty() ? field : prefix + "." + field;
                keys.add(curentPath);
                extractKeysRecursively(node.get(field),curentPath,keys);
            });
        }else if (node.isArray()) {
            for (JsonNode arrayNode : node) {
                extractKeysRecursively(arrayNode,prefix,keys);
            }
        }
    }
}
