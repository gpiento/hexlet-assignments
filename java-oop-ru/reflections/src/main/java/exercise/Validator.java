package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validator {

    public static void main(String[] args) {
        Address address = new Address(null, "London", "1-st street", "7", "2");
        List<String> notValidFields = Validator.validate(address);
        System.out.println(notValidFields); // => [country]

        Address address2 = new Address("England", null, null, "7", "2");
        List<String> notValidFields2 = Validator.validate(address2);
        System.out.println(notValidFields2); // => [city, street]
    }

    public static List<String> validate(Object object) {
        List<String> notValidFields = new ArrayList<>();
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.get(object) == null) {
                    notValidFields.add(field.getName());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return notValidFields;
    }
}
