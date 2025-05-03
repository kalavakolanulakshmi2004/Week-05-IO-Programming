import java.lang.reflect.Method;
import java.util.Scanner;

public class MethodExecutionTimer {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter fully qualified class name: ");
        String className = scanner.nextLine();
        Class<?> clazz = Class.forName(className);
        Object obj = clazz.getDeclaredConstructor().newInstance();
        System.out.print("Enter method name (no params): ");
        String methodName = scanner.nextLine();
        Method method = clazz.getDeclaredMethod(methodName);
        method.setAccessible(true);
        long start = System.nanoTime();
        method.invoke(obj);
        long end = System.nanoTime();
        long durationMillis = (end - start) / 1_000_000;
        System.out.println("Execution time of " + methodName + ": " + durationMillis + " ms");
        scanner.close();
    }
}
