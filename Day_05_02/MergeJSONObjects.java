package org.example;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class MergeJSONObjects {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the absolute path of the first JSON file: ");
        String filePath1 = sc.nextLine();
        System.out.print("Enter the absolute path of the second JSON file: ");
        String filePath2 = sc.nextLine();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file1 = new File(filePath1);
            File file2 = new File(filePath2);
            if (!file1.exists() || !file2.exists()) {
                System.out.println("One or both files not found at the specified paths.");
                return;
            }
            JsonNode jsonNode1 = objectMapper.readTree(file1);
            JsonNode jsonNode2 = objectMapper.readTree(file2);
            ObjectNode mergedNode = objectMapper.createObjectNode();
            mergedNode.setAll((ObjectNode) jsonNode1);
            mergedNode.setAll((ObjectNode) jsonNode2);
            objectMapper.writeValue(new File("src/main/resources/merged1.json"), mergedNode);
            System.out.println("JSON objects merged successfully. Check src/main/resources/merged1.json");
        } catch (IOException e) {
            System.out.println("Error processing JSON files: " + e.getMessage());
        }
    }
}