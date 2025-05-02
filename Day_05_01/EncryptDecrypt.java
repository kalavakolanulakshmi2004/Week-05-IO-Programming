import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.*;
import java.util.Base64;
public class EncryptDecrypt {
    private static final String SECRET_KEY = "1234567890123456"; // 16-char key for AES
    public static String encrypt(String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }
    public static String decrypt(String encryptedText) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decrypted);
    }
    public static void writeEncryptedCSV(String filePath) {
        List<String[]> students = Arrays.asList(
                new String[]{"101", "Alice", "alice@example.com", "85000"},
                new String[]{"102", "Bob", "bob@example.com", "92000"}
        );
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("ID,Name,Email,Salary\n");
            for (String[] student : students) {
                String encryptedEmail = encrypt(student[2]);
                String encryptedSalary = encrypt(student[3]);
                writer.write(student[0] + "," + student[1] + "," + encryptedEmail + "," + encryptedSalary + "\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void readDecryptedCSV(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // Header
            System.out.println(line);
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String name = parts[1];
                String email = decrypt(parts[2]);
                String salary = decrypt(parts[3]);
                System.out.println(id + "," + name + "," + email + "," + salary);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter CSV file path to save encrypted data: ");
        String filePath = sc.nextLine();
        writeEncryptedCSV(filePath);
        System.out.println("Encrypted CSV written successfully.");
        System.out.println("\nReading and decrypting the CSV:");
        readDecryptedCSV(filePath);
    }
}