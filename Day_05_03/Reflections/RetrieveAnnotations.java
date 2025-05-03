import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.AnnotatedElement;
import java.util.Scanner;

@Retention(RetentionPolicy.RUNTIME)
@interface Author {
    String name();
}

@Author(name = "John Doe")
class Book {}

public class RetrieveAnnotations {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter author's name: ");
            String authorName = scanner.nextLine();

            Class<?> bookClass = Book.class;
            Author authorAnnotation = bookClass.getAnnotation(Author.class);

            if (authorAnnotation != null) {
                System.out.println("Author: " + authorAnnotation.name());
            } else {
                System.out.println("No author annotation found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}