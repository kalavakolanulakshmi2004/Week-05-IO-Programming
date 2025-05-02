package org.example;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import com.networknt.schema.SpecVersion;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
public class ValidateJSONStructure {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File("C:\\Users\\bhanu\\Desktop\\java prog\\JSON\\src\\main\\resources\\data1.json");
            if (!jsonFile.exists()) {
                System.out.println("File not found at the specified path.");
                return;
            }
            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            InputStream schemaStream = ValidateJSONStructure.class.getClassLoader()
                    .getResourceAsStream("schema.json");
            if (schemaStream == null) {
                System.out.println("Schema file not found.");
                return;
            }
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
            JsonSchema schema = factory.getSchema(schemaStream);
            Set<ValidationMessage> validationErrors = schema.validate(jsonNode);
            if (validationErrors.isEmpty()) {
                System.out.println("JSON is valid according to the schema.");
            } else {
                System.out.println("JSON is not valid. Errors:");
                validationErrors.forEach(error -> System.out.println(error.getMessage()));
            }
        } catch (IOException e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
        }
    }
}