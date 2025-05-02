package org.example;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
public class FilterJSONByAge {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("people.json");
            if (!file.exists()) {
                System.out.println("File not found at the specified path.");
                return;
            }
            JsonNode rootNode = objectMapper.readTree(file);
            Iterator<JsonNode> elements = rootNode.elements();
            while (elements.hasNext()) {
                JsonNode person = elements.next();
                int age = person.get("age").asInt();
                if (age > 25) {
                    String name = person.get("name").asText();
                    String email = person.get("email").asText();
                    System.out.println("Name: " + name);
                    System.out.println("Age: " + age);
                    System.out.println("Email: " + email);
                    System.out.println("-------------");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
        }
    }
}