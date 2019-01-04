package com.generator.util;

import java.util.Date;

public class Reflect2Utils {
    public static Object getDefaultValue(Class c) {
        if (c.isPrimitive()) {
            String t = c.getName();
            if ("boolean".equals(t)) {
                return false;
            }
            if ("byte".equals(t)) {
                return 0;
            }
            if ("char".equals(t)) {
                return '\u0000';
            }
            if ("double".equals(t)) {
                return 0;
            }
            if ("float".equals(t)) {
                return 0;
            }
            if ("int".equals(t)) {
                return 0;
            }
            if ("long".equals(t)) {
                return 0;
            }
            if ("short".equals(t)) {
                return 0;
            }
        }
        return null;
    }

    public static boolean isPrimitives(Class<?> cls) {
        if (cls.isArray()) {
            return isPrimitive(cls.getComponentType());
        }
        return isPrimitive(cls);
    }

    public static boolean isPrimitive(Class<?> cls) {
        return cls.isPrimitive() || cls == String.class || cls == Boolean.class || cls == Character.class
                || Number.class.isAssignableFrom(cls) || Date.class.isAssignableFrom(cls);
    }

    public static Class<?> getBoxedClass(Class<?> c) {
        if (c == int.class) {
            c = Integer.class;
        } else if (c == boolean.class) {
            c = Boolean.class;
        } else if (c == long.class) {
            c = Long.class;
        } else if (c == float.class) {
            c = Float.class;
        } else if (c == double.class) {
            c = Double.class;
        } else if (c == char.class) {
            c = Character.class;
        } else if (c == byte.class) {
            c = Byte.class;
        } else if (c == short.class) {
            c = Short.class;
        }
        return c;
    }



}
