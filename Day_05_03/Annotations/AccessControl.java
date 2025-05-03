package org.example;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.Scanner;
@Retention(RetentionPolicy.RUNTIME)
@interface RoleAllowed {
    String value();
}
public class AccessControl{
    @RoleAllowed("ADMIN")
    public void adminTask() {
        System.out.println("Admin task executed successfully.");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your role (ADMIN/USER): ");
        String userRole = sc.nextLine().toUpperCase();
        AccessControl ac = new AccessControl();
        try {
            Method method = AccessControl.class.getMethod("adminTask");
            if (method.isAnnotationPresent(RoleAllowed.class)) {
                RoleAllowed roleAllowed = method.getAnnotation(RoleAllowed.class);
                if (roleAllowed.value().equals(userRole)) {
                    method.invoke(ac);
                } else {
                    System.out.println("Access Denied!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}