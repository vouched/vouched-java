package com.vouched.sdk;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GraphQlResult {
    private Set<Class<?>> visited = new HashSet<>();

    public String getAttributes(Class<?> cls) {
        if (visited.contains(cls)) return "";
        visited.add(cls);

        return Stream.of(cls.getFields())
                .map(f -> getFieldSchema(f))
                .collect(Collectors.joining(" "))
                .trim();
    }

    private String getFieldSchema(Field f) {
        Class<?> type = f.getType();

        if (Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers())) return "";

        if (type.isPrimitive() || type == String.class) return f.getName();

        if (Map.class.isAssignableFrom(type)) {
            return f.getName() + " {name value}";
        }

        Class<?> element = type.isArray() ? type.getComponentType() : type;

        String attributes = getAttributes(element);
        if (attributes.isEmpty()) return "";

        return f.getName() + " {" + attributes + "}";
    }
}
