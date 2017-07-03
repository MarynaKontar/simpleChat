package com.app.Reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by User on 19.06.2017.
 */
public class Reflection {

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD,
            ElementType.CONSTRUCTOR,
            ElementType.FIELD,
            ElementType.TYPE})
    private @interface B {    //@interface - описание аннотации (как  )
        int value() default 10;
        String name() default "";
    }

    @B(name = "Name with class A")
    private static class A {
        @B
        private int v;

        @B
        public A() {
            System.out.println("Hello from construction!");
        }

        @B
        public int getV() {
            return v;
        }

        @Override
        public String toString() {
            return "A{" +
                    "v=" + v +
                    '}';
        }
    }


    private static class Generator<T> {
        private Constructor<?> constructor;

        public Generator(Constructor<?> constructor) {
            this.constructor = constructor;
        }

        public Collection<T> createCollection(int n) {
            return IntStream.range(0, n).mapToObj(i -> {
                try {
                    return (T) constructor.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
        }
    }

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, NoSuchMethodException {
        Class<A> clazz = A.class;
        Constructor<?>[] constructors = clazz.getConstructors();
        System.out.println(Arrays.toString(constructors));
        Constructor<?> constructor = constructors[0];


        try {
            A a = (A) constructor.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        Constructor<?> constructor1 = constructors[0];
        Generator<A> generator = new Generator<A>(constructor);
        System.out.println(generator.createCollection(5));

        A a = (A) constructor1.newInstance();
        System.out.println(a);

        Field field = clazz.getDeclaredField("v");
        field.setAccessible(true);//ИЗМЕНЕНИЕ ЗНАЧЕНИЯ ПРИВАТНОГО ПОЛЯ КЛАССА!!!
        field.set(a, 5);
        System.out.println(a);
        System.out.println(field.get(a));

        Method method = clazz.getMethod("getV");
        System.out.println(method.invoke(a));

        B b = clazz.getAnnotation(B.class);
        System.out.println(b.name());
        System.out.println(b.value());

    }
}
