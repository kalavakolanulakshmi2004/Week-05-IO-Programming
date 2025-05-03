package org.example;
import java.util.ArrayList;
import java.util.Scanner;
public class UncheckedWarningExample {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList list = new ArrayList();
        System.out.println("Enter the number of elements you want to add to the list:");
        int n = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the elements to add to the list:");
        for (int i = 0; i < n; i++) {
            String element = sc.nextLine();
            list.add(element);
        }
        System.out.println("Elements in the list:");
        for (Object item : list) {
            System.out.println(item);
        }
        sc.close();
    }
}