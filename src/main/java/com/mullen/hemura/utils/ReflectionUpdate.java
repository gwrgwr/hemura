package com.mullen.hemura.utils;

import java.lang.reflect.Method;

public class ReflectionUpdate {
    public static void updateEntitiesFields(Object targetObject, Object sourceObject) {
        try {
            Method[] methods = sourceObject.getClass().getDeclaredMethods();

            for (Method method : methods) {
                if (method.getName().startsWith("get") && !method.getReturnType().equals(Void.TYPE)) {
                    Object value = method.invoke(sourceObject);
                    if (value != null) {
                        String setterName = "set" + method.getName().substring(3);
                        Method setter = targetObject.getClass().getMethod(setterName, method.getReturnType());
                        setter.invoke(targetObject, value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
