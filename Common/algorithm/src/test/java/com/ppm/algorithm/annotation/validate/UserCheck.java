package com.ppm.algorithm.annotation.validate;

import java.lang.reflect.Field;

public class UserCheck {

    public static boolean check(User user) {
        if (user == null) {
            System.out.println("校验对象为null");
            return false;
        }

        Field[] fields = User.class.getFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Validate.class)) {
                Validate validate = field.getAnnotation(Validate.class);
                if (field.getName().equals("age")) {
                    if (user.getAge() == null) {
                        if (validate.isNotNull()) {
                            System.out.println("age不可为空");
                            return false;
                        } else {
                            System.out.println("age可为空");
                        }
                    }
                }
            }
        }
        return true;
    }

}
