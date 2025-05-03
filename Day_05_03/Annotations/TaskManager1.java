package org.example;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)
@interface TaskInfo1 {
    String priority();
    String assignedTo();
}
public class TaskManager1 {
    @TaskInfo(priority = "High", assignedTo = "John")
    public void taskMethod() {}
    public static void main(String[] args) {
        try {
            Method method = TaskManager1.class.getMethod("taskMethod");
            if (method.isAnnotationPresent(TaskInfo.class)) {
                TaskInfo taskInfo = method.getAnnotation(TaskInfo.class);
                System.out.println("Priority: " + taskInfo.priority());
                System.out.println("Assigned to: " + taskInfo.assignedTo());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}