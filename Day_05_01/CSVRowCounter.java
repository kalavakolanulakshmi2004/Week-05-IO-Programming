import java.io.*;
import java.util.Scanner;
public class CSVRowCounter {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter CSV file name: ");
        String fileName = sc.nextLine();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int count = 0;
        String line = reader.readLine();
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                count++;
            }
        }
        reader.close();
        System.out.println("Number of data records (excluding header): " + count);
    }
}