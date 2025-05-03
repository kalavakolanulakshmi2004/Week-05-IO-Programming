package org.example;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
@Retention(RetentionPolicy.RUNTIME)
@interface CacheResult {
}
class ExpensiveCalculator {
    private final Map<Integer, Integer> cache = new HashMap<>();
    @CacheResult
    public int expensiveOperation(int number) {
        if (cache.containsKey(number)) {
            return cache.get(number);
        }
        int result = number * number;
        cache.put(number, result);
        return result;
    }
}
public class CacheExample {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ExpensiveCalculator calculator = new ExpensiveCalculator();
        Method method = ExpensiveCalculator.class.getMethod("expensiveOperation", int.class);
        while (true) {
            System.out.print("Enter a number to calculate its square (or type 'exit' to quit): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                int number = Integer.parseInt(input);
                if (method.isAnnotationPresent(CacheResult.class)) {
                    System.out.println(calculator.expensiveOperation(number));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        scanner.close();
    }
}