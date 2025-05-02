package org.example;
import org.json.JSONArray;
import org.json.JSONObject;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.*;
import java.util.*;
public class IPLData {
    public static void main(String[] args) throws Exception {
        String jsonInputFile = "ipl_data.json";
        String csvInputFile = "ipl_data.csv";
        String jsonOutputFile = "sanitized_ipl_data.json";
        String csvOutputFile = "sanitized_ipl_data.csv";

        JSONArray jsonData = readJSONFile(jsonInputFile);
        JSONArray sanitizedJsonData = applyCensorshipToJSON(jsonData);
        writeJSONFile(jsonOutputFile, sanitizedJsonData);

        List<String[]> csvData = readCSVFile(csvInputFile);
        List<String[]> sanitizedCsvData = applyCensorshipToCSV(csvData);
        writeCSVFile(csvOutputFile, sanitizedCsvData);
    }

    public static JSONArray readJSONFile(String filePath) throws IOException {
        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
        }
        return new JSONArray(jsonContent.toString());
    }

    public static void writeJSONFile(String filePath, JSONArray jsonData) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(jsonData.toString(4));
        }
    }

    public static JSONArray applyCensorshipToJSON(JSONArray data) {
        JSONArray sanitizedData = new JSONArray();
        for (int i = 0; i < data.length(); i++) {
            JSONObject match = data.getJSONObject(i);
            match.put("team1", maskTeamName(match.getString("team1")));
            match.put("team2", maskTeamName(match.getString("team2")));
            match.put("player_of_match", "REDACTED");
            sanitizedData.put(match);
        }
        return sanitizedData;
    }

    public static String maskTeamName(String teamName) {
        if (teamName.contains(" ")) {
            int spaceIndex = teamName.indexOf(" ");
            return teamName.substring(0, spaceIndex) + " ***";
        }
        return teamName;
    }

    public static List<String[]> readCSVFile(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                data.add(line);
            }
        } catch (CsvValidationException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());

            return Collections.emptyList();

        }
        return data;
    }

    public static void writeCSVFile(String filePath, List<String[]> data) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeAll(data);
        }
    }
    public static List<String[]> applyCensorshipToCSV(List<String[]> data) {
        List<String[]> sanitizedData = new ArrayList<>();
        for (String[] row : data) {
            row[1] = maskTeamName(row[1]);
            row[2] = maskTeamName(row[2]);
            row[6] = "REDACTED";
            sanitizedData.add(row);
        }
        return sanitizedData;
    }
}