import java.lang.reflect.Field;

class Configuration {
    private static String API_KEY = "initial_api_key";
}

public class ModifyStaticField {
    public static void main(String[] args) {
        try {
            Class<?> configClass = Configuration.class;
            Field apiKeyField = configClass.getDeclaredField("API_KEY");
            apiKeyField.setAccessible(true);

            System.out.println("Before modification: " + apiKeyField.get(null));
            apiKeyField.set(null, "new_api_key");
            System.out.println("After modification: " + apiKeyField.get(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}