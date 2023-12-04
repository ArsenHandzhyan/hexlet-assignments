package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {
    public static List<String> validate(Address address) {
        List<String> notValidFields = new ArrayList<>();
        Field[] fields = address.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    if (field.get(address) == null) {
                        notValidFields.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(notValidFields);
        return notValidFields;
    }

    public static <T> Map<String, List<String>> advancedValidate(T object) {
        Map<String, List<String>> errors = new HashMap<>();

        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(MinLength.class)) {
                MinLength minLengthAnnotation = field.getAnnotation(MinLength.class);
                int minLengthValue = minLengthAnnotation.minLength();

                try {
                    field.setAccessible(true);
                    var value = field.get(object);
                    int valueLength = value.toString().length() - 1;
                    if (valueLength < minLengthValue) {
                        errors.computeIfAbsent(field.getName(), k -> new ArrayList<>())
                                .add("length less than 4");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace(); // Handle the exception according to your needs
                }
            }

            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(object);

                    if (value == null) {
                        errors.computeIfAbsent(field.getName(), k -> new ArrayList<>())
                                .add("can not be null");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace(); // Handle the exception according to your needs
                }
            }
        }

        return errors;
    }
}
