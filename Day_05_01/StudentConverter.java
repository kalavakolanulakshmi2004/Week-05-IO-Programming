package org.example;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.List;
public class StudentConverter {
    public static void jsonToCsv(String jsonFilePath, String csvFilePath) {
        try (FileReader reader = new FileReader(jsonFilePath)) {
            JSONParser jsonParser = new JSONParser();
            JSONArray studentList = (JSONArray) jsonParser.parse(reader);
            try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))) {
                writer.writeNext(new String[]{"ID", "Name", "Marks"});
                for (Object obj : studentList) {
                    JSONObject student = (JSONObject) obj;
                    writer.writeNext(new String[]{
                            (String) student.get("ID"),
                            (String) student.get("Name"),
                            (String) student.get("Marks")
                    });
                }
                System.out.println("CSV file created successfully.");
            }
        } catch (IOException | ParseException e) {
            System.out.println("Error during conversion: " + e.getMessage());
        }
    }
    public static void csvToJson(String csvFilePath, String jsonFilePath) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            List<String[]> rows = reader.readAll();
            JSONArray studentList = new JSONArray();
            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                JSONObject student = new JSONObject();
                student.put("ID", row[0]);
                student.put("Name", row[1]);
                student.put("Marks", row[2]);
                studentList.add(student);
            }
            try (FileWriter file = new FileWriter(jsonFilePath)) {
                file.write(studentList.toJSONString());
                System.out.println("JSON file created successfully.");
            }
        } catch (IOException | CsvException e) {
            System.out.println("Error during conversion: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        String jsonFilePath = "src/students.json";
        String csvFilePath = "src/students.csv";
        String outputJsonFilePath = "src/output.json";
        System.out.println("-- Converting JSON to CSV --");
        jsonToCsv(jsonFilePath, csvFilePath);
        System.out.println("-- Converting CSV back to JSON --");
        csvToJson(csvFilePath, outputJsonFilePath);
    }
}