package com.app.Reflection;

import com.app.HibernateModel.Chat;
import com.app.HibernateModel.User;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by User on 03.07.2017.
 */
public class MyReflection {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

        Class<User> clazz = User.class;
//        Class<?> clazz = Chat.class;
//        Class<?> clazz = Message.class;
//        Class<?> clazz = Group.class;


        Annotation[] annotations = clazz.getAnnotations();

        Constructor<?>[] constructors = clazz.getConstructors();
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();

        Method[] methods = clazz.getMethods();
        Method[] declaredMethods = clazz.getDeclaredMethods();

        Field[] fields = clazz.getFields();
        Field[] declaredFields = clazz.getDeclaredFields();



        // Изменение значения параметра (login) объекта (user) через clazz.getMethod/invoke
        User user = (User) constructors[1].newInstance("login__","p","n");//создается экземпляр
        // User по второму конструктору (public User(String login, String password, String userName))
        // с параметрами "login__","p","n"

        Method method1 = clazz.getMethod("getLogin");
        System.out.println(method1.invoke(user));
        Method method = clazz.getMethod("setLogin",String.class );
       method.invoke(user,"newLogin");//вызывается метод setLogin применительно к экземпляру user;
        System.out.println(method1.invoke(user));
        //для статических методов : If the underlying method is static, then the specified obj argument is ignored. It may be null.
//        Class klass = ...;
//        Method m = klass.getDeclaredMethod(methodName, paramtypes);
//        m.invoke(null, args)
//        Method method = clazz.getMethod("methodName", String.class);
//        Object o = method.invoke(null, "whatever");
        //для Superclass
//        Method mthd = classAInstance.getClass().getSuperclass().getDeclaredMethod("XYZ");
//        mthd.invoke(classAInstance)



        System.out.println("Annotations: " + Arrays.toString(annotations));
//        System.out.println("constructors: " + Arrays.toString(constructors));
//        System.out.println("declaredConstructors: " + Arrays.toString(declaredConstructors));
//        System.out.println("methods: " + Arrays.toString(methods));
//        System.out.println("declaredMethods: " + Arrays.toString(declaredMethods));
//        System.out.println("fields: " + Arrays.toString(fields));
//        System.out.println("declaredFields: " + Arrays.toString(declaredFields));

//        System.out.println(declaredFields[1].isAnnotationPresent(Annotation.class));// надо писать что-то другое, а не Annotation.class

        Entity entity = (Entity) annotations[0];
        System.out.println(entity.name());

        Table table = (Table) annotations[1];
        System.out.println(table.name());
        System.out.println(table.schema());//TODO почему не показывает название БД?

        NamedQueries namedQueries = (NamedQueries) annotations[2];
        System.out.println(namedQueries.value());
        NamedQuery namedQuery1 = namedQueries.value()[0];
        NamedQuery namedQuery2 = namedQueries.value()[1];
        String query1 = namedQuery1.query();
        String query2 = namedQuery2.query();
        System.out.println(query1);
        System.out.println(query2);
    }
}
