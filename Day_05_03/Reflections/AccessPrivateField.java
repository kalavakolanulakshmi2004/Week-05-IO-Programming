import java.lang.reflect.Field;
import java.util.Scanner;
class Person {
    private int age;
    public Person(int age) {
        this.age = age;
    }
}
public class AccessPrivateField {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the initial age: ");
            int initialAge = scanner.nextInt();
            Person person = new Person(initialAge);
            Field field = Person.class.getDeclaredField("age");
            field.setAccessible(true);
            System.out.println("Age: " + field.get(person));
            System.out.print("Enter the updated age: ");
            int updatedAge = scanner.nextInt();
            field.set(person, updatedAge);
            System.out.println("Updated Age: " + field.get(person));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}