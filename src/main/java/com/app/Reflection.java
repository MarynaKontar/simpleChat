package com.app;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * Created by User on 19.06.2017.
 */
public class Reflection {
    private static class A{
        public A(){
            System.out.println("Hello from construction");
        }
    }

    private static class Generator<T>{
        private Constructor<T> constructor;

        public Generator(Constructor<T> co){}
    }
    public static void main(String[] args) {
        Class<A> clazz = A.class;
        Constructor<?>[] constructors = clazz.getConstructors();
        System.out.println(Arrays.toString(constructors));
        Constructor<?> constructor = constructors[0];
        try {
            A a =(A) constructor.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
