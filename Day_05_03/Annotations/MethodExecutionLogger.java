package org.example;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)
@interface LogExecutionTime {
}
public class MethodExecutionLogger {
    @LogExecutionTime
    public void fastMethod() {
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += i;
        }
        System.out.println("Fast method completed.");
    }
    @LogExecutionTime
    public void slowMethod() {
        int sum = 0;
        for (int i = 0; i < 1000000; i++) {
            sum += i;
        }
        System.out.println("Slow method completed.");
    }
    public static void main(String[] args) {
        MethodExecutionLogger logger = new MethodExecutionLogger();
        for (Method method : MethodExecutionLogger.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(LogExecutionTime.class)) {
                try {
                    long startTime = System.nanoTime();
                    method.invoke(logger);
                    long endTime = System.nanoTime();
                    System.out.println("Execution Time of " + method.getName() + ": " + (endTime - startTime) + " ns");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}