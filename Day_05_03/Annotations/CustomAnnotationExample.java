package org.example;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)
@interface TaskInfo {
    String priority();
    String assignedTo();
}
class TaskManager {
    public void completeTask() {
        System.out.println("Task Completed.");
    }
}
public class CustomAnnotationExample {
    public static void main(String[] args) throws Exception {
        TaskManager taskManager = new TaskManager();
        Method method = taskManager.getClass().getMethod("completeTask");
        if (method.isAnnotationPresent(TaskInfo.class)) {
            TaskInfo taskInfo = method.getAnnotation(TaskInfo.class);
            System.out.println("Priority: " + taskInfo.priority());
            System.out.println("Assigned To: " + taskInfo.assignedTo());
        }
        taskManager.completeTask();
    }
}