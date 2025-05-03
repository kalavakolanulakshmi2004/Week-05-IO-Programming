import java.lang.reflect.Method;
import java.util.Scanner;

class SampleClass {
    public void slowMethod() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void fastMethod() {
        System.out.println("Fast method executed!");
    }
}

public class MethodExecutionTiming {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter method name (slowMethod/fastMethod): ");
        String methodName = scanner.nextLine();

        try {
            Class<?> clazz = SampleClass.class;
            Method method = clazz.getDeclaredMethod(methodName);

            long startTime = System.nanoTime();
            method.invoke(clazz.getDeclaredConstructor().newInstance());
            long endTime = System.nanoTime();

            long duration = endTime - startTime;
            System.out.println("Execution time of " + methodName + ": " + duration + " nanoseconds");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}