import java.io.*;
import java.util.*;
public class SearchCSV {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter employee name to search: ");
        String searchName = scanner.nextLine().trim();
        BufferedReader reader = new BufferedReader(new FileReader("employee.csv"));
        String line;
        boolean found = false;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 4) {
                String name = data[1].trim();
                if (name.equalsIgnoreCase(searchName)) {
                    String department = data[2].trim();
                    String salary = data[3].trim();
                    System.out.println("Department: " + department);
                    System.out.println("Salary: " + salary);
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("Employee not found.");
        }
        reader.close();
    }
}