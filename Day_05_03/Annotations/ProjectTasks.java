package org.example;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)
@interface Todo {
    String task();
    String assignedTo();
    String priority() default "MEDIUM";
}
public class ProjectTasks {
    @Todo(task = "Implement user login", assignedTo = "Alice", priority = "HIGH")
    public void userLoginFeature() {
        System.out.println("User login feature is under development.");
    }
    @Todo(task = "Integrate payment gateway", assignedTo = "Bob")
    public void paymentIntegration() {
        System.out.println("Payment gateway integration is under development.");
    }
    @Todo(task = "Database optimization", assignedTo = "Charlie", priority = "LOW")
    public void optimizeDatabase() {
        System.out.println("Database optimization is under development.");
    }
    public static void main(String[] args) {
        Method[] methods = ProjectTasks.class.getDeclaredMethods();
        System.out.println("Pending Tasks:");
        for (Method method : methods) {
            if (method.isAnnotationPresent(Todo.class)) {
                Todo annotation = method.getAnnotation(Todo.class);
                System.out.println("Task: " + annotation.task());
                System.out.println("Assigned To: " + annotation.assignedTo());
                System.out.println("Priority: " + annotation.priority());
                System.out.println("Method: " + method.getName());
                System.out.println("---------------------------");
            }
        }
    }
}