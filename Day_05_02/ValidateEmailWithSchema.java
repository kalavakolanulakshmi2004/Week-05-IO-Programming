package org.example;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
public class ValidateEmailWithSchema {
    public static void main(String[] args) {
        try {
            InputStream schemaInputStream = ValidateEmailWithSchema.class.getClassLoader().getResourceAsStream("schema2.json");
            if (schemaInputStream == null) {
                throw new IllegalArgumentException("Schema file not found in resources folder.");
            }
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode schemaNode = objectMapper.readTree(schemaInputStream);
            System.out.println("Schema: " + schemaNode.toString());
            schemaInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}