package org.example;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.Scanner;
@Repeatable(BugTracker.BugReports.class)
@Retention(RetentionPolicy.RUNTIME)
@interface BugReport {
    String description();
}
public class BugTracker {
    @Retention(RetentionPolicy.RUNTIME)
    @interface BugReports {
        BugReport[] value();
    }
    @BugReport(description = "Null pointer exception when input is null")
    @BugReport(description = "Index out of bounds when list is empty")
    public void buggyMethod() {}
    public static void main(String[] args) {
        try {
            Method method = BugTracker.class.getMethod("buggyMethod");
            if (method.isAnnotationPresent(BugReports.class)) {
                BugReports bugReports = method.getAnnotation(BugReports.class);
                System.out.println("Bug Reports:");
                for (BugReport bug : bugReports.value()) {
                    System.out.println("- " + bug.description());
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}