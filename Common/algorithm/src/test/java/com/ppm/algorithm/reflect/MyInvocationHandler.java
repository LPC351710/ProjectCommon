package com.ppm.algorithm.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("check permission");
        method.invoke(target, args);
        System.out.println("log record");
        return null;
    }


    public static void main(String[] args) {
        Student student1 = new StudentImpl();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(student1);
        Student student = (Student) Proxy.newProxyInstance(student1.getClass().getClassLoader(),
                student1.getClass().getInterfaces(), myInvocationHandler);
        student.login();
        student.submit();
    }
}
