package org.example;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class FilterUsersByAge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the absolute path of the JSON file: ");
        String filePath = sc.nextLine();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(filePath));
            System.out.println("Users older than 25:");
            for (JsonNode node : rootNode) {
                if (node.get("age").asInt() > 25) {
                    System.out.println("Name: " + node.get("name").asText());
                    System.out.println("Age: " + node.get("age").asInt());
                    System.out.println();
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
        }
    }
}