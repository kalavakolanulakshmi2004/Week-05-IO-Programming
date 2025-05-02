import java.io.*;
import java.util.*;
public class LargeCSV{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter CSV file path: ");
        String filePath = scanner.nextLine();
        System.out.print("Enter number of lines to process at a time: ");
        int chunkSize = scanner.nextInt();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line != null) System.out.println(line);
            int count = 0;
            List<String> chunk = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                chunk.add(line);
                count++;
                if (chunk.size() == chunkSize) {
                    for (String record : chunk) {
                        System.out.println(record);
                    }
                    chunk.clear();
                    System.out.println("Processed records: " + count);
                }
            }
            if (!chunk.isEmpty()) {
                for (String record : chunk) {
                    System.out.println(record);
                }
                System.out.println("Processed records: " + count);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}