package org.example;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)
@interface ImportantMethod {
    String level() default "HIGH";
}
public class MethodMarker {
    @ImportantMethod
    public void criticalProcess() {
        System.out.println("Critical process running...");
    }
    @ImportantMethod(level = "MEDIUM")
    public void dataBackup() {
        System.out.println("Data backup in progress...");
    }
    public void nonCriticalProcess() {
        System.out.println("Non-critical process running...");
    }
    public static void main(String[] args) {
        Method[] methods = MethodMarker.class.getDeclaredMethods();
        System.out.println("Important Methods:");
        for (Method method : methods) {
            if (method.isAnnotationPresent(ImportantMethod.class)) {
                ImportantMethod annotation = method.getAnnotation(ImportantMethod.class);
                System.out.println("Method: " + method.getName() + " | Level: " + annotation.level());
            }
        }
    }
}