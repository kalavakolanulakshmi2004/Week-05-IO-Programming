package org.test.example;
import com.opencsv.CSVWriter;
import org.example.DB;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class EmployeeDBCSV {
    public static void main(String[] args) {
        String filePath = "EmployeeReport.csv";
        try (Connection connection = DB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Employee");
             CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            String[] header = {"Employee ID", "Name", "Department", "Salary"};
            writer.writeNext(header);
            while (resultSet.next()) {
                String[] record = {
                        String.valueOf(resultSet.getInt("EmployeeID")),
                        resultSet.getString("Name"),
                        resultSet.getString("Department"),
                        String.valueOf(resultSet.getDouble("Salary"))
                };
                writer.writeNext(record);
            }
            System.out.println("CSV Report generated successfully at " + filePath);
        } catch (SQLException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}