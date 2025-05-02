package org.example;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
public class MergeJSONFiles {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the absolute path of the first JSON file: ");
        String firstFilePath = sc.nextLine();
        System.out.print("Enter the absolute path of the second JSON file: ");
        String secondFilePath = sc.nextLine();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode firstJson = objectMapper.readTree(new File(firstFilePath));
            JsonNode secondJson = objectMapper.readTree(new File(secondFilePath));
            if (firstJson instanceof ObjectNode) {
                ObjectNode mergedJson = (ObjectNode) firstJson;
                Iterator<Map.Entry<String, JsonNode>> fields = secondJson.fields();
                while (fields.hasNext()) {
                    Map.Entry<String, JsonNode> field = fields.next();
                    mergedJson.set(field.getKey(), field.getValue());
                }
                objectMapper.writeValue(new File("src/main/resources/merged.json"), mergedJson);
                System.out.println("Merged JSON saved to src/main/resources/merged.json");
            } else {
                System.out.println("First JSON is not an ObjectNode, cannot merge.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}