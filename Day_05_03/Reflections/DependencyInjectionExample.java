import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Inject {}

class DatabaseService {
    public void connect() {
        System.out.println("Connecting to the database...");
    }
}

class UserService {
    @Inject
    private DatabaseService databaseService;

    public void performService() {
        databaseService.connect();
        System.out.println("Performing user service...");
    }
}

class DIContainer {
    private final Map<Class<?>, Object> instances = new HashMap<>();

    public void register(Class<?> clazz) throws Exception {
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object instance = constructor.newInstance();
        instances.put(clazz, instance);
        injectDependencies(instance);
    }

    public <T> T getInstance(Class<T> clazz) {
        return (T) instances.get(clazz);
    }

    private void injectDependencies(Object instance) throws Exception {
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                Object dependency = getInstance(field.getType());
                field.set(instance, dependency);
            }
        }
    }
}

public class DependencyInjectionExample {
    public static void main(String[] args) throws Exception {
        DIContainer container = new DIContainer();
        container.register(DatabaseService.class);
        container.register(UserService.class);

        UserService userService = container.getInstance(UserService.class);
        userService.performService();
    }
}
