package com.app.Reflection;

import com.app.HibernateModel.User;

import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by User on 03.07.2017.
 */
public class TestReflectApi {
    public static void main(String[] args) {
        User user = new User();
        save(null, user);
    }

    public static void save(Connection connection, Object user) {
        Class<?> clazz = user.getClass();
        String tableName = clazz.getName();
        if (clazz.isAnnotationPresent(Table.class)) {
            Table table = clazz.getAnnotation(Table.class);
            tableName = table.name();
        }
        List<String> fields = new ArrayList<>();
        for (Field f : clazz.getDeclaredFields()) {
            boolean isCollection = f
                    .getType()
                    .isAssignableFrom(List.class);
            if (isCollection) {
                continue;
            }
            fields.add(f.getName());

        }

        String format = "insert into %1$s(%2$s) values (%3$s)";
        String ffs = String.join(",", fields);
        String qest = fields
                .stream()
                .map(v -> "?")
                .collect(Collectors.joining(","));
        String insert = String.format(format,
                tableName, ffs, qest);
        System.out.println(insert);

    }
}
