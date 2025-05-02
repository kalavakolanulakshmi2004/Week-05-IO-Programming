import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class WriteCSV {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter file name to write (e.g., employees.csv): ");
        String fileName = sc.nextLine();
        try (FileWriter writer = new FileWriter(fileName)) {
            System.out.print("Enter number of records (minimum 5): ");
            int count = sc.nextInt();
            sc.nextLine();
            if (count < 5) {
                System.out.println("Minimum 5 records required.");
                return;
            }
            for (int i = 1; i <= count; i++) {
                System.out.println("Enter details for Employee " + i + ":");
                System.out.print("ID: ");
                String id = sc.nextLine();
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Department: ");
                String dept = sc.nextLine();
                System.out.print("Salary: ");
                String salary = sc.nextLine();
                writer.write(id + "," + name + "," + dept + "," + salary + "\n");
            }
            System.out.println("Data written successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}