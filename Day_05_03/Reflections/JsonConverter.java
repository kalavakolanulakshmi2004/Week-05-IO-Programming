import java.lang.reflect.Field;
import java.util.Scanner;
class Person2 {
    private String name;
    private int age;
    public Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
public class JsonConverter {
    public static String toJson(Object obj) throws IllegalAccessException {
        StringBuilder json = new StringBuilder();
        json.append("{");
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            json.append("\"").append(fields[i].getName()).append("\":");
            json.append("\"").append(fields[i].get(obj)).append("\"");
            if (i < fields.length - 1) {
                json.append(",");
            }
        }
        json.append("}");
        return json.toString();
    }
    public static void main(String[] args) throws IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        Person2 person = new Person2(name, age);
        String json = toJson(person);
        System.out.println("JSON Representation: " + json);
        scanner.close();
    }
}