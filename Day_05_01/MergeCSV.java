import java.io.*;
import java.util.*;
public class MergeCSV {
    public static void main(String[] args) {
        Map<String, String[]> map1 = new HashMap<>();
        Map<String, String[]> map2 = new HashMap<>();
        try (BufferedReader br1 = new BufferedReader(new FileReader("students1.csv"));
             BufferedReader br2 = new BufferedReader(new FileReader("students2.csv"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("merged_students.csv"))) {
            String line;
            br1.readLine();
            while ((line = br1.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    map1.put(data[0].trim(), new String[]{data[1].trim(), data[2].trim()});
                }
            }
            br2.readLine();
            while ((line = br2.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    map2.put(data[0].trim(), new String[]{data[1].trim(), data[2].trim()});
                }
            }
            writer.write("ID,Name,Age,Marks,Grade");
            writer.newLine();
            for (String id : map1.keySet()) {
                if (map2.containsKey(id)) {
                    String[] val1 = map1.get(id);
                    String[] val2 = map2.get(id);
                    writer.write(id + "," + val1[0] + "," + val1[1] + "," + val2[0] + "," + val2[1]);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}