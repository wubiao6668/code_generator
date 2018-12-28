
package com.generator.util;


/**
 * ReflectUtils
 */
public final class ReflectUtils {

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

    static char s = '\u0000';
    static char s2;

    public static void main(String[] args) {

        System.out.println(s);
        System.out.println(s2);
    }

}