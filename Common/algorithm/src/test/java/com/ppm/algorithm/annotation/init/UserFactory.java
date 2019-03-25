package com.ppm.algorithm.annotation.init;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class UserFactory {

    public static User create() {
        User user = new User();
        Method[] methods = User.class.getMethods();
        Field[] fields = User.class.getFields();

        try {
            for (Method method : methods) {
                if (method.isAnnotationPresent(Init.class)) {
                    Init init = method.getAnnotation(Init.class);
                    method.invoke(user, init.value());
                }
            }

            for (Field field : fields) {
                if (field.isAnnotationPresent(Init.class)) {
                    Init init = field.getAnnotation(Init.class);
                    System.out.println("field: " + init.value());
                    field.set(user, init.value());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void main(String[] args) {
        User user = UserFactory.create();
        System.out.println(user.getName());
        System.out.println(user.getAge());
    }
}
