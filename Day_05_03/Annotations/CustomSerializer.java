package org.example;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.Scanner;
@Retention(RetentionPolicy.RUNTIME)
@interface JsonField {
    String name();
}
class User {
    @JsonField(name = "user_name")
    private String name;
    @JsonField(name = "user_age")
    private int age;
    @JsonField(name = "user_email")
    private String email;
    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
public class CustomSerializer {
    public static String serializeToJson(Object obj) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (field.isAnnotationPresent(JsonField.class)) {
                field.setAccessible(true);
                JsonField annotation = field.getAnnotation(JsonField.class);
                try {
                    jsonBuilder.append("\"").append(annotation.name()).append("\": \"")
                            .append(field.get(obj)).append("\"");
                    if (i < fields.length - 1) {
                        jsonBuilder.append(", ");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        User user = new User(name, age, email);
        String json = serializeToJson(user);
        System.out.println("Serialized JSON: " + json);
    }
}