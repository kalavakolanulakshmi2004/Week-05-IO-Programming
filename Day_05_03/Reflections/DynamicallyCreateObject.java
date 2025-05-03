import java.lang.reflect.Constructor;
import java.util.Scanner;
class Student {
    private String name;
    private int rollNo;
    public Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }
    @Override
    public String toString() {
        return "Student{name='" + name + "', rollNo=" + rollNo + '}';
    }
}
public class DynamicallyCreateObject {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter student's name: ");
            String name = scanner.nextLine();
            System.out.print("Enter student's roll number: ");
            int rollNo = scanner.nextInt();
            Class<?> studentClass = Class.forName("Student");
            Constructor<?> constructor = studentClass.getConstructor(String.class, int.class);
            Object student = constructor.newInstance(name, rollNo);
            System.out.println(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}