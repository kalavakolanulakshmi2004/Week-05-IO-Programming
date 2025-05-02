import java.io.*;
import java.util.*;
public class FilterCSV {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter CSV file name: ");
        String fileName = sc.nextLine();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        System.out.println(line);
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 4) {
                int marks = Integer.parseInt(data[3].trim());
                if (marks > 80) {
                    System.out.println(line);
                }
            }
        }
        reader.close();
    }
}