import java.io.*;
import java.util.*;
public class ModifyCSV {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("employee.csv"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("updated_employee.csv"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 4) {
                String department = data[2].trim();
                double salary = Double.parseDouble(data[3].trim());
                if (department.equalsIgnoreCase("IT")) {
                    salary = salary * 1.10;
                    data[3] = String.valueOf(salary);
                }
                writer.write(String.join(",", data));
                writer.newLine();
            }
        }
        reader.close();
        writer.close();
    }
}