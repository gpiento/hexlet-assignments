package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {

    public static void main(String[] args) {
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

    public static List<String> validate(Object object) {
        List<String> notValidFields = new ArrayList<>();
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(NotNull.class)) {
                    if (field.get(object) == null) {
                        notValidFields.add(field.getName());
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return notValidFields;
    }

    public static Map<String, List<String>> advancedValidate(Object object) {
        Map<String, List<String>> notValidFields = new HashMap<>();
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(NotNull.class)) {
                    if (field.get(object) == null) {
                        List<String> errors = notValidFields.get(field.getName());
                        if (errors == null) {
                            errors = new ArrayList<>();
                        }
                        errors.add("Field cannot be null");
                        notValidFields.put(field.getName(), errors);
                    }
                }

                if (field.isAnnotationPresent(MinLength.class)) {
                    if (field.get(object).toString().length() < field.getAnnotation(MinLength.class).minLength()) {
                        List<String> errors = notValidFields.get(field.getName());
                        if (errors == null) {
                            errors = new ArrayList<>();
                        }
                        errors.add("Field length must be at least " + field.getAnnotation(MinLength.class).minLength());
                        notValidFields.put(field.getName(), errors);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return notValidFields;
    }
}
