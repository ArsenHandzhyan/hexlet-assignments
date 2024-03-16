package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        Method[] methods = Address.class.getDeclaredMethods();
        Arrays.stream(methods).filter(method -> method.isAnnotationPresent(Inspect.class))
                .forEach(method -> System.out.printf("Method %s returns a value of type %s. \n",
                        method.getName(),
                        method.getReturnType()));
    }
}
