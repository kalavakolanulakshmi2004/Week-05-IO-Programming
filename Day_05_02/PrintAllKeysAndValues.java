package org.example;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map.Entry;
public class PrintAllKeysAndValues {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = PrintAllKeysAndValues.class.getClassLoader().getResourceAsStream("data1.json");
            if (inputStream == null) return;
            JsonNode rootNode = objectMapper.readTree(inputStream);
            printJsonNode(rootNode, "");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void printJsonNode(JsonNode node, String parentKey) {
        Iterator<Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Entry<String, JsonNode> field = fields.next();
            String key = field.getKey();
            JsonNode value = field.getValue();
            String currentKey = parentKey.isEmpty() ? key : parentKey + "." + key;
            if (value.isObject()) {
                printJsonNode(value, currentKey);
            } else {
                System.out.println(currentKey + ": " + value.asText());
            }
        }
    }
}