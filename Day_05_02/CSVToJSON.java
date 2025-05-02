package org.example;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class CSVToJSON {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the path of the CSV file: ");
        String csvFile = sc.nextLine();
        List<Map<String, String>> records = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] headers = reader.readNext();
            if (headers == null) {
                System.out.println("The CSV file is empty or malformed.");
                return;
            }
            String[] row;
            while ((row = reader.readNext()) != null) {
                Map<String, String> record = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    record.put(headers[i], row[i]);
                }
                records.add(record);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonOutput = objectMapper.writeValueAsString(records);
            System.out.println("JSON Output: " + jsonOutput);
        } catch (CsvValidationException e) {
            System.out.println("CSV validation error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
    }
}