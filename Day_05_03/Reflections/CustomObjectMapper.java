import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Person1 {
    private String name;
    private int age;
    private boolean active;

    public Person1() {
    }

    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", active=" + active + "}";
    }
}

public class CustomObjectMapper {
    public static <T> T toObject(Class<T> clazz, Map<String, Object> properties) throws Exception {
        T obj = clazz.getDeclaredConstructor().newInstance();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            try {
                Field field = clazz.getDeclaredField(entry.getKey());
                field.setAccessible(true);
                Object value = entry.getValue();
                Class<?> fieldType = field.getType();
                if (value != null) {
                    if (fieldType.isPrimitive()) {
                        if (fieldType == int.class) {
                            field.setInt(obj, ((Number) value).intValue());
                        } else if (fieldType == long.class) {
                            field.setLong(obj, ((Number) value).longValue());
                        } else if (fieldType == double.class) {
                            field.setDouble(obj, ((Number) value).doubleValue());
                        } else if (fieldType == float.class) {
                            field.setFloat(obj, ((Number) value).floatValue());
                        } else if (fieldType == boolean.class) {
                            field.setBoolean(obj, (Boolean) value);
                        } else if (fieldType == char.class) {
                            field.setChar(obj, (Character) value);
                        } else if (fieldType == byte.class) {
                            field.setByte(obj, ((Number) value).byteValue());
                        } else if (fieldType == short.class) {
                            field.setShort(obj, ((Number) value).shortValue());
                        }
                    } else {
                        field.set(obj, value);
                    }
                } else {
                    field.set(obj, null);
                }
            } catch (NoSuchFieldException ignored) {
            }
        }
        return obj;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> properties = new HashMap<>();
        System.out.print("Enter name: ");
        properties.put("name", scanner.nextLine());
        System.out.print("Enter age: ");
        properties.put("age", Integer.parseInt(scanner.nextLine()));
        System.out.print("Is active (true/false): ");
        properties.put("active", Boolean.parseBoolean(scanner.nextLine()));
        Person person = toObject(Person.class, properties);
        System.out.println(person);
        scanner.close();
    }
}