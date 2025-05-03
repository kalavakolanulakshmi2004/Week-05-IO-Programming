import java.lang.reflect.*;
import java.util.*;
interface Greeting {
    void sayHello();
}

class GreetingImpl implements Greeting {
    public void sayHello() {
        System.out.println("Hello, World!");
    }
}

class LoggingHandler implements InvocationHandler {
    private final Object target;

    public LoggingHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Method " + method.getName() + " is called");
        return method.invoke(target, args);
    }
}

public class CustomLoggingProxy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        Greeting greeting = new GreetingImpl();
        LoggingHandler loggingHandler = new LoggingHandler(greeting);

        Greeting proxy = (Greeting) Proxy.newProxyInstance(
                Greeting.class.getClassLoader(),
                new Class[]{Greeting.class},
                loggingHandler
        );

        System.out.println("Calling sayHello method:");
        proxy.sayHello();
    }
}
