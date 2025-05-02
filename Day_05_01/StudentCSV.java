package org.test.example;
import org.example.Student;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class StudentCSV {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the path to the CSV file: ");
        String filePath = sc.nextLine();
        List<Student> studentList = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                String id = line[0];
                String name = line[1];
                int marks = Integer.parseInt(line[2]);
                Student student = new Student(id, name, marks);
                studentList.add(student);
            }
            System.out.println("\n-- Student List --");
            studentList.forEach(System.out::println);
        } catch (IOException | CsvValidationException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}