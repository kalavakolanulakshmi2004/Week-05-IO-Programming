import java.io.*;
import java.util.*;
public class SortCSV {
    public static void main(String[] args) {
        List<String[]> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("sample.csv"))) {
            String header = reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                employees.add(data);
            }
            employees.sort((a, b) -> Double.compare(Double.parseDouble(b[3].trim()), Double.parseDouble(a[3].trim())));
            System.out.println("Top 5 Highest Paid Employees:");
            System.out.println(header);
            for (int i = 0; i < Math.min(5, employees.size()); i++) {
                System.out.println(String.join(",", employees.get(i)));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}