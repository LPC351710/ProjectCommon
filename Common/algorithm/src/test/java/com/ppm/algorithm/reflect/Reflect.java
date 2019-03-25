package com.ppm.algorithm.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Reflect {

    public static void main(String[] args) {
        String s = "233";

        try {
            Class class1 = Class.forName("java.lang.String");
            Class class2 = s.getClass();
            Class class3 = String.class;

            System.out.println("class1: " + class1.getSimpleName());
            System.out.println("class2 " + class2.getSimpleName());
            System.out.print("class3 " + class3.getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReflect() {
        try {
            Class testClass = Class.forName("com.ppm.algorithm.reflect.TestReflect");
//            TestReflect testReflect = (TestReflect) testClass.newInstance();

            Constructor constructor = testClass.getConstructor(String.class, int.class);
            TestReflect testReflect = (TestReflect) constructor.newInstance("12306", 1);
            System.out.println(testReflect.getTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllConstructor() {
        try {
            Class testClass = Class.forName("com.ppm.algorithm.reflect.TestReflect");
            Constructor[] constructors = testClass.getConstructors();
            for (int i = 0; i < constructors.length; i++) {
                Class[] paramsTypes = constructors[i].getParameterTypes();
                System.out.println("第" + (i + 1) + "个构造函数");
                for (int i1 = 0; i1 < paramsTypes.length; i1++) {
                    System.out.println("第" + (i1 + 1) + "个参数的类型为:" + paramsTypes[i].getSimpleName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getField() {
        try {
            Class testClass = Class.forName("com.ppm.algorithm.reflect.TestReflect");
            TestReflect testReflect = (TestReflect) testClass.newInstance();
            Field field = testClass.getDeclaredField("test");
            field.setAccessible(true);
            field.set(testReflect, "123654");
            System.out.println(testReflect.getTest());


            TestReflect testReflect1 = (TestReflect) testClass.newInstance();
            testReflect1.setTest("test11111");
            testReflect1.setTest2(122);
            Field[] fields = testClass.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                System.out.println(fields[i].get(testReflect1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMethod() {
        try {
            Class testClass = Class.forName("com.ppm.algorithm.reflect.TestReflect");
            TestReflect test = (TestReflect) testClass.newInstance();
            Method method = testClass.getMethod("reflectMethod", String.class);
            method.invoke(test, "method");

            Method method1 = testClass.getDeclaredMethod("privateMethod", String.class);
            method1.setAccessible(true);
            method1.invoke(test, "privateMethod");

            printAllMethod(testClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printAllMethod(Class classz) {
        Method[] methods = classz.getMethods();
        for (Method method : methods) {
            System.out.print(method.getName() + "(");
            method.setAccessible(true);
            Class[] paramsTypes = method.getParameterTypes();
            for (int j = 0; j < paramsTypes.length; j++) {
                System.out.print(paramsTypes[j].getSimpleName());
                if (j < paramsTypes.length - 1) {
                    System.out.print(",");
                }
            }
            System.out.println(")");
        }
    }

    @Test
    public void reflectUse() {
        try {
            List<Integer> list = new ArrayList<>();
            list.add(2);
            list.add(3);
            Class classz = list.getClass();
            Method method = classz.getMethod("add", Object.class);//list 添加不同类型的数据
            method.invoke(list, "ssss");
            System.out.print(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
