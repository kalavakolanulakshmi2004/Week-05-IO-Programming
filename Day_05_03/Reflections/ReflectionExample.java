import java.lang.reflect.*;
public class ReflectionExample {
    public static void main(String[] args) throws ClassNotFoundException {
        String className = "java.util.ArrayList";
        Class<?> clazz = Class.forName(className);
        System.out.println("Methods:");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println(method.getName());
        }
        System.out.println("Fields:");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(field.getName());
        }
        System.out.println("Constructors:");
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            System.out.println(constructor.getName());
        }
    }
}