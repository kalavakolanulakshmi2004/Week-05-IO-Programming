import java.io.*;
import java.util.regex.*;
public class ValidateCSV{
    public static void main(String[] args) {
        Pattern emailPattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");
        Pattern phonePattern = Pattern.compile("^\\d{10}$");
        try (BufferedReader reader = new BufferedReader(new FileReader("validate.csv"))) {
            String header = reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length != 4) {
                    System.out.println("Invalid format: " + line);
                    continue;
                }
                String email = data[2].trim();
                String phone = data[3].trim();
                boolean validEmail = emailPattern.matcher(email).matches();
                boolean validPhone = phonePattern.matcher(phone).matches();
                if (!validEmail || !validPhone) {
                    System.out.println("Invalid data: " + line);
                    if (!validEmail) System.out.println(" → Invalid email: " + email);
                    if (!validPhone) System.out.println(" → Invalid phone number: " + phone);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}