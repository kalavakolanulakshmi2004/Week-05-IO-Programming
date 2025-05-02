package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DatabaseToJSON {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/your_database";
        String user = "root";
        String password = "password";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, name, age, email FROM users";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                users.add(new User(id, name, age, email));
            }
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(users);
            System.out.println(json);
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            System.out.println("Error processing JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
class User {
    private int id;
    private String name;
    private int age;
    private String email;
    public User(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getEmail() {
        return email;
    }
}