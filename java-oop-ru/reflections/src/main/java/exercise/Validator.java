package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {

    public static void main(final String[] args) {

        Address address = new Address(null, "London", "1-st street", "7", "2");
        List<String> notValidFields = Validator.validate(address);
        System.out.println(notValidFields); // => [country]

        Address address2 = new Address("England", null, null, "7", "2");
        List<String> notValidFields2 = Validator.validate(address2);
        System.out.println(notValidFields2); // => [city, street]

        Address address3 = new Address("USA", "Texas", null, "7", "2");
        Map<String, List<String>> notValidFields3 = Validator.advancedValidate(address3);
        System.out.println(notValidFields3); // =>  {country=[length less than 4], street=[can not be null]}
    }

    public static List<String> validate(final Object object) {

        List<String> stringList = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        Object value;

        for (Field f : fields) {
            if (f.isAnnotationPresent(NotNull.class)) {
                try {
                    f.setAccessible(true);
                    value = f.get(object);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                if (value == null) {
                    stringList.add(f.getName());
                }
            }
        }
        return stringList;
    }

    public static Map<String, List<String>> advancedValidate(final Object object) {

        Map<String, List<String>> stringListMap = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        Object value;

        for (Field f : fields) {

        }

        return stringListMap;
    }
}
