package org.example;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class StudentJSON {
    @JsonProperty("name")
    private String name;
    @JsonProperty("age")
    private int age;
    @JsonProperty("subjects")
    private List<String> subjects;
    public StudentJSON(String name, int age, List<String> subjects) {
        this.name = name;
        this.age = age;
        this.subjects = subjects;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public List<String> getSubjects() {
        return subjects;
    }
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter age: ");
            int age = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter number of subjects: ");
            int subjectCount = sc.nextInt();
            sc.nextLine();
            List<String> subjects = new ArrayList<>();
            for (int i = 0; i < subjectCount; i++) {
                System.out.print("Enter subject " + (i + 1) + ": ");
                subjects.add(sc.nextLine());
            }
            StudentJSON student = new StudentJSON(name, age, subjects);
            ObjectMapper mapper = new ObjectMapper();
            File resourceDir = new File("src/main/resources");
            if (!resourceDir.exists()) {
                resourceDir.mkdirs();
            }
            File outputFile = new File(resourceDir, "student.json");
            mapper.writeValue(outputFile, student);
            System.out.println("Student JSON created successfully at: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("An error occurred while creating the JSON file: " + e.getMessage());
        }
    }
}